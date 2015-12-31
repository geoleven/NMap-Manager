package gr.uoa.di.NmapProject.AM.Server;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
/**
 * Server 
 * @author George
 *
 */
public class Server {
	/**
	 * Base Url of the Server
	 */
	private String BASE_URI;
	/**
	 * Server instance
	 */
	private HttpServer server;
	/**
	 * Constructor
	 * @param uri
	 * 		takes the base_uri
	 */
	public Server(String uri){
		BASE_URI = uri;
	}
	/**
	 * Start the Server
	 */
	public void start(){
		final ResourceConfig rc = new ResourceConfig().packages("gr.uoa.di.NmapProject.AM.Server.Requests");
		server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),rc);
		
		System.out.println(String.format("Server started at "+BASE_URI+" ..."));
	}
	/**
	 * Stop the Server
	 */
	public void stop(){
		//server.stop();
		server.shutdown();
		System.out.println("Server closed.");
	}
	
}
