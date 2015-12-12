package main.java.gr.uoa.di.NmapProject.SA;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ServerRequest {
	
	private String baseUri;
	
	public ServerRequest(String uri){
		baseUri = uri;
	}
	
	public WebResource getResource(String path) {
        return Client.create().resource(baseUri+path);
	}
	
	public ClientResponse post(String path , JSONObject obj){
		WebResource resource = getResource(path);
		return resource.accept("application/json").type("application/json").post(ClientResponse.class, obj.toJSONString());
	}
	
	public ClientResponse get(String path , ArrayList<String> params){
		String pathAll = path;
		for(String param : params){
			pathAll+="/"+param;
		}
		
		WebResource resource = getResource(pathAll);
		return resource.accept("application/json").get(ClientResponse.class);
	}
	
	public ClientResponse registrationRequest() {
		
		JSONObject obj=new JSONObject();
    	obj.put("name","foo");
    	obj.put("ip","213,425,3456");
		
        ClientResponse response = post("register" , obj);
        
        if (response.getStatus() == 200){
        	String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);
        } else {
        	System.out.println("Server Response : "+response.getStatus());
        }
		        
		return null;
	}
	
	public ClientResponse getTest() {
		
		ArrayList<String> params = new ArrayList<String>();
		
		params.add("param1");
		params.add("param2");
		params.add("param33");
		System.out.println("IN getTest");	
		ClientResponse response = get("gettest" , params);
        System.out.println("IN getTest");
        if (response.getStatus() == 200){
        	String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);
        } else {
        	System.out.println("Server Response : "+response.getStatus());
        }
		
		return null;
	}
}
