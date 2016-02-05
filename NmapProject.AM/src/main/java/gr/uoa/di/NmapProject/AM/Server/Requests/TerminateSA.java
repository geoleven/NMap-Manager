package gr.uoa.di.NmapProject.AM.Server.Requests;

import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.uoa.di.NmapProject.AM.Server.OnlineStatus;

@Path("terminate")
public class TerminateSA {
	
	@SuppressWarnings("unchecked")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(String req) throws Exception{
		
    	ObjectMapper mapper = new ObjectMapper();
    	Map request = mapper.readValue(req, Map.class);
		
    	String saHash = (String) request.get("hash");
    	
    	OnlineStatus.getInstance().setForExit(saHash);
    	
		JSONObject response = new JSONObject();
    	response.put("status","ok");
    	
		return Response.status(200).entity(response.toJSONString()).build();
	}
}
