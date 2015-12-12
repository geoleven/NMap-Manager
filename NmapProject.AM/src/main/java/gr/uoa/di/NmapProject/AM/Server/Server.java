package main.java.gr.uoa.di.NmapProject.AM.Server;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server {
	
	private String BASE_URI;
	private HttpServer server;
	
	public Server(String uri){
		BASE_URI = uri;
	}
	
	public void start(){
		final ResourceConfig rc = new ResourceConfig().packages("main.java.gr.uoa.di.NmapProject.AM.Server.Requests");
		server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),rc);
		
		System.out.println(String.format("Server started at "+BASE_URI));
	}
	
	public void stop(){
		server.stop();
	}
	
}
