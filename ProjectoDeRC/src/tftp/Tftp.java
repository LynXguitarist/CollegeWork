package tftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public class Tftp {

	private static final int BUFFER_SIZE = 512;

	public static void main(String[] args) throws Exception {
		int port;
		String fileName;
		InetAddress server;

		switch (args.length) {
		case 3:
			server = InetAddress.getByName(args[0]);
			port = Integer.valueOf(args[1]);
			fileName = args[2];
			break;
		default:
			System.out.printf("usage: java %s server port filename\n", Tftp.class.getName());
			return;
		}
		Stats stats = new Stats();
		sendFile(stats, fileName, server, port);
		stats.printReport();
	}

	// ------------------------------Metodos---------------------------------//

	// Metodo sendfile

	private static void sendFile(Stats stats, String fileName, InetAddress server, int port) throws Exception {

		// DatagramSocket
		DatagramSocket socket = new DatagramSocket();
		socket.setSoTimeout(500);

		int totalDataBlocks = 0;
		int totalAcks = 0;
		int totalBytes = 0;

		// Get the file
		File file = new File(fileName);
		File file2 = new File("file2.txt");
		FileInputStream fileStored = new FileInputStream(file);

		// sends a write request to the server
		TFtpPacketV18 write = new TFtpPacketV18(TFtpPacketV18.OpCode.OP_WRQ);
		write.putBytes(("/tmp/" + fileName).getBytes());
		write.putByte(0);
		write.putBytes("octet".getBytes());
		write.putByte(0);

		SocketAddress address = new InetSocketAddress(server, port);
		DatagramPacket writePacket = write.toDatagramPacket(address);
		socket.send(writePacket);

		// receive ack
		byte[] ackBuffer = new byte[TFtpPacketV18.MAX_TFTP_DATAGRAM_SIZE];
		DatagramPacket received = new DatagramPacket(ackBuffer, ackBuffer.length);
		socket.receive(received);
		TFtpPacketV18 ack = new TFtpPacketV18(received.getData(), received.getLength());
		address = received.getSocketAddress();
		totalAcks++;

		short sequenceNumber = 0;
		// Cycle
		for (int counter = 0; counter < (file.length() / BUFFER_SIZE) + 1; counter++) {
			boolean timedOut = true;

			while (timedOut) {
				sequenceNumber++;
				try {
					// Create a byte array for sending and receiving data
					byte[] sendData = new byte[BUFFER_SIZE];
					boolean endOfFile = false;

					// Get byte data for message
					int n = fileStored.read(sendData);
					totalBytes += n;

					if (n < 512) {
						endOfFile = true;
					}
					
					// data tftpPacketv18
					TFtpPacketV18 data = new TFtpPacketV18(TFtpPacketV18.OpCode.OP_DATA);

					// sends a datagram containing the data
					data.putShort(sequenceNumber);
					
					if (endOfFile && n==-1) {
						data.putByte(0);
						totalBytes++;
					} else
						data.putBytes(sendData, n);

					DatagramPacket sender = data.toDatagramPacket(address);
					socket.send(sender);
					totalDataBlocks++;

					// receive ack
					DatagramPacket ackRec = new DatagramPacket(ackBuffer, ackBuffer.length);
					received.setData(ackBuffer);
					socket.receive(received);

					TFtpPacketV18 ackPacket = new TFtpPacketV18(ackBuffer, ackBuffer.length);

					// If we receive an ack, stop the while loop

					if (endOfFile) {
						counter = (int) (file.length() / BUFFER_SIZE) + 1;
						socket.close();
					}

					if (ackPacket.getOpCode() == TFtpPacketV18.OpCode.OP_ACK) {
						totalAcks++;
						timedOut = false;
					}

				} catch (SocketTimeoutException exception) {
					System.out.println(exception.getMessage());
				}
			}
		}
		stats.setInfo(totalDataBlocks, totalAcks, totalBytes);

	}
}
