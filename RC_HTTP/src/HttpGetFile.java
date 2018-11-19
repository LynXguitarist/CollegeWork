import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.URL;

public class HttpGetFile extends Thread {

	private static long startTime = 0;

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Usage: java HttpClientDemo url_to_access");
			System.exit(0);
		}
		String url = args[0];
		URL u = new URL(url);

		// Assuming URL of the form http://server-name/path ....
		int port = u.getPort() == -1 ? 80 : u.getPort();
		String path = u.getPath() == "" ? "/" : u.getPath();
		Socket sock = new Socket(u.getHost(), port);

		// absolute path of the file
		String absolutePath = new File(".").getAbsolutePath();
		int last = absolutePath.length() - 1;
		absolutePath = absolutePath.substring(0, last);
		String fileName = "Downloads/Download";

		File file = new File(absolutePath + fileName);
		File fileReceived = new File(absolutePath + path);

		@SuppressWarnings("resource")
		RandomAccessFile f = new RandomAccessFile(fileReceived, "rw");

		FileOutputStream fo = new FileOutputStream(file);
		OutputStream bw = fo;

		int mark = 0;// second position of the range (range[1])
		int requests = 0;// requests made by client to the servers

		startTime = System.currentTimeMillis();

		while (mark < fileReceived.length()) {

			int contentLength = 0;

			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();

			if (fileReceived.length() - f.getFilePointer() < HttpTrickyServer.MIN_RANGE) {
				mark = (int) fileReceived.length() - 1;
			} else if (fileReceived.length() - f.getFilePointer() < HttpTrickyServer.MAX_RANGE) {
				mark = (int) fileReceived.length() - 1;
			} else {
				mark += HttpTrickyServer.MIN_RANGE;
			}

			String sentRange = "Range: bytes=" + (int) f.getFilePointer() + "-" + mark + "\r\n";
			String request = String.format(
					"GET %s HTTP/1.0\r\n" + "Host: %s\r\n" + sentRange + "User-Agent: X-RC2018\r\n\r\n", path,
					u.getHost());
			out.write(request.getBytes());

			System.out.println("\nSent:\n\n" + request);
			System.out.println("Got:\n");
			String answerLine = Http.readLine(in);
			System.out.println(answerLine);

			answerLine = Http.readLine(in);
			while (!answerLine.equals("")) {
				System.out.println(answerLine);
				answerLine = Http.readLine(in);
				String[] an = Http.parseHttpHeader(answerLine);
				if (an[0].equals("Content-Length")) {
					contentLength = Integer.parseInt(an[1]);
				}
			}

			// Payload
			int n;
			byte[] buffer = new byte[contentLength];
			try {
				while ((n = in.read(buffer)) != -1) {
					bw.write(buffer, 0, n);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			f.seek(f.getFilePointer() + contentLength);
			mark = (int) f.getFilePointer();
			requests++;

			if (mark != fileReceived.length() - 1) {
				out.close();
				in.close();
				sock.close();// termina a conexao
				port = nextPort(port);
				sock = new Socket(u.getHost(), port);
			}
		}
		bw.close();
		sock.close();
		printStats((int) file.length(), requests);
	}

	private static int nextPort(int port) {
		if (port == 8083) {
			return 8080;
		} else {
			return port + 1;
		}
	}

	private static void printStats(int fileLength, int requests) {

		float milliSeconds = System.currentTimeMillis() - startTime;
		float seconds = milliSeconds / 1000;
		float bitRate = fileLength / seconds;

		System.out.println("\nTotal time elapsed: " + seconds + " seconds");
		System.out.println("Total number of bytes downloaded: " + fileLength + " bytes");
		System.out.println("End-to-end average bitrate: " + bitRate + " bytes per second");
		System.out.println("Number of requests performed: " + requests + " requests");

	}

}
