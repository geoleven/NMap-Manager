package gr.uoa.di.NmapProject.SA;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	
	public LinkedList<String> requestJobs() {
		// TODO write me
		JSONObject reqJson = new JSONObject();
		reqJson.put("Hash", Globals.saHash);
		ClientResponse response = post("getjobs", reqJson);
		return null;
		
	}
}
