package gr.uoa.di.NmapProject.SA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ServerRequest {

	private String baseUri;

	public ServerRequest(String uri) {
		baseUri = uri;
	}

	public WebResource getResource(String path) {
		return Client.create().resource(baseUri + path);
	}

	public ClientResponse post(String path, JSONObject obj) {
		WebResource resource = getResource(path);
		return resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class,
				obj.toJSONString());
	}
	
	public ClientResponse post(String path, String json) {
		WebResource resource = getResource(path);
		return resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class,
				json);
	}

	public ClientResponse get(String path, ArrayList<String> params) {
		String pathAll = path;
		for (String param : params) {
			pathAll += "/" + param;
		}

		WebResource resource = getResource(pathAll);
		return resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
	}

	public void registrationRequest() {

		Registration reg = new Registration();

		while (true) {
			try {
				ClientResponse response = post("register", reg.toJson());

				if (response.getStatus() == 200) {

					JSONObject status = (JSONObject) (new JSONParser()).parse(response.getEntity(String.class));

					if (((String) status.get("status")).equals("ok")) {
						System.out.println("Authentication Successfull");
						break;
					} else {
						System.out.println("Waiting for an admin to accept registration request");
					}
				} else {
					System.out.println("Server Response : " + response.getStatus());
				}

				Thread.sleep(2000);

			} catch (Exception ex) {
				System.out.println("Server is not up");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public ClientResponse getTest() {

		ArrayList<String> params = new ArrayList<String>();

		params.add("param1");
		params.add("param2");
		params.add("param33");
		System.out.println("IN getTest");
		ClientResponse response = get("gettest", params);
		System.out.println("IN getTest");
		if (response.getStatus() == 200) {
			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);
		} else {
			System.out.println("Server Response : " + response.getStatus());
		}

		return null;
	}
	
	public LinkedList<NmapJob> requestJobs(Stopper stopper) {
		try{
			ArrayList<String> params = new ArrayList<String>();
			params.add(Globals.saHash);
			
			ClientResponse response = get("job/get", params);
			if (response.getStatus() == 200) {
				String jsonString  = response.getEntity(String.class);
				
				if(jsonString.contains("Kill")){
					System.out.println("Time to die");
					
					if(stopper != null){
						stopper.closeNow();
					}
					
					return null;
				}
				
				ObjectMapper mapper = new ObjectMapper();
				
				try{
					LinkedList<LinkedHashMap> jobList = mapper.readValue(jsonString, LinkedList.class);
					
					LinkedList<NmapJob> results = new LinkedList<NmapJob>(); 
					for(LinkedHashMap e : jobList){
						results.add( 
							new NmapJob(
								(int) e.get("id"),
								(String) e.get("parameters"),
								(Boolean) e.get("periodic"),
								(int) e.get("period"),
								(String) e.get("status")
							));
					}
					
					return results;
					
				}catch (Exception ex){
		    		
		    		System.out.println(ex.getMessage());
		    		
		    		return null;
		    	}
				
			}else{
				System.out.println("Job Request Server Response : " + response.getStatus());
			}
		} catch (Exception ex){
			System.out.println("Server is not Responding");
		}
		
		return null;
		
	}
	
	public void sendResult( Result res) {
		try{	
			ObjectMapper mapper = new ObjectMapper();
			
			ClientResponse response = post("job/result", mapper.writeValueAsString(res));
	
			if (response.getStatus() == 200) {
	
				JSONObject status = (JSONObject) (new JSONParser()).parse(response.getEntity(String.class));
	
				if (((String) status.get("status")).equals("ok")) {
					System.out.println("Sent result for job : "+res.job.id);
				} else {
					System.out.println("Result for job "+res.job.id+" could not be sent");
				}
			} else {
				System.out.println("Result Post Server Response : " + response.getStatus());
			}
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
