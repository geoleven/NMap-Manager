package gr.uoa.di.NmapProject.AM.Server.Requests;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.uoa.di.NmapProject.AM.DB.JobDAO;

/**
 * Jobs web resource
 */
@Path("periodic")
public class PeriodicJobs {
	
    /**
     * 
     * Handling requests Sending results
     * 
     */
    @SuppressWarnings("unchecked")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response result(String req) throws Exception{
    	
    	ObjectMapper mapper = new ObjectMapper();
    	LinkedHashMap res = mapper.readValue(req , LinkedHashMap.class);
    	
    	String saHash = (String) res.get("saHash");
		
    	System.out.println("saHash = "+saHash);
    	
    	LinkedList<Map> results;
    	
    	results = JobDAO.getPeriodicJobsOfSA(saHash);
    	
    	System.out.println("Returning results : "+String.valueOf(results));

    	try{
    		
    		String jsonInString = mapper.writeValueAsString(results);
    		
    		return Response.status(200).entity(jsonInString).build();
    	
    	}catch (JsonProcessingException ex){
    		
    		System.out.println(ex.getMessage());
    		
    		JSONObject resp = new JSONObject();
    		
    		resp.put("Error", ex.getMessage());
    		return Response.status(200).entity(resp.toJSONString()).build();
    	}
		
    }
}
