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

import gr.uoa.di.NmapProject.AM.DB.JobDAO;

@Path("delete")
public class DeleteJob {
	@SuppressWarnings("unchecked")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(String req) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
    	LinkedList<Map> request = mapper.readValue(req, LinkedList.class);
		
    	for(Map m : request){
    		int jobId = (int) m.get("id");
    		
    		JobDAO.setStatusToDelete(jobId);
    		
    	}
    	
		JSONObject response = new JSONObject();
    	response.put("status","ok");
    	
		return Response.status(200).entity(response.toJSONString()).build();
	}
}
