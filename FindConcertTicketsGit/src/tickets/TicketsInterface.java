/**
 * @Author Frederico Lopes 42764 Andre Pinto 41671
 */
package tickets;

import shows.Shows;

public interface TicketsInterface {

	/**
	 * Retorna o conteudo(difere entre tipos).
	 * 
	 * @return conteudo
	 */
	String conteudo();

	/**
	 * Retorna o associatedShow.
	 * 
	 * @return associatedShow
	 */
	Shows getAssociatedShow();
}
