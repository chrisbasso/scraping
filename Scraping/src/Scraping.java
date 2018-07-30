import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping {

	public static final String url = "https://www.garbarino.com/";

	public static void main(String[] args) {

		// Compruebo si me da un 200 al hacer la petici�n
		if (getStatusConnectionCode(url) == 200) {

			// Obtengo el HTML de la web en un objeto Document
			Document document = getHtmlDocument(url);

			String titulo = document.title();

			System.out.println(titulo + "\n");

			Elements productos = document.getElementsByClass("gb-offers-carousel-product-name");
			Elements precios = document.getElementsByClass("value-item ");

			for (int i = 0; i < productos.size(); i++) {
				System.out.println(productos.get(i).text());
				System.out.println(precios.get(i).text() + "\n");
			}

		} else
			System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));

	}

	/**
	 * Con esta m�todo compruebo el Status code de la respuesta que recibo al hacer
	 * la petici�n EJM: 200 OK 300 Multiple Choices 301 Moved Permanently 305 Use
	 * Proxy 400 Bad Request 403 Forbidden 404 Not Found 500 Internal Server Error
	 * 502 Bad Gateway 503 Service Unavailable
	 * 
	 * @param url
	 * @return Status Code
	 */
	public static int getStatusConnectionCode(String url) {

		Response response = null;

		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			System.out.println("Excepci�n al obtener el Status Code: " + ex.getMessage());
		}
		return response.statusCode();
	}

	/**
	 * Con este m�todo devuelvo un objeto de la clase Document con el contenido del
	 * HTML de la web que me permitir� parsearlo con los m�todos de la librelia
	 * JSoup
	 * 
	 * @param url
	 * @return Documento con el HTML
	 */
	public static Document getHtmlDocument(String url) {

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			System.out.println("Excepci�n al obtener el HTML de la p�gina" + ex.getMessage());
		}
		return doc;
	}

}
