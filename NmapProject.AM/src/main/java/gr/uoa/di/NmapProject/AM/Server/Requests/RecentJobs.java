package gr.uoa.di.NmapProject.AM.Server.Requests;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.transform.TransformerException;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.uoa.di.NmapProject.AM.DB.JobDAO;
import gr.uoa.di.NmapProject.AM.DB.Result;
import gr.uoa.di.NmapProject.AM.DB.ResultDAO;

/**
 * Jobs web resource
 */
@Path("recent")
public class RecentJobs {
	
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
    	
    	System.out.println("in recent");
    	
    	String saHash = (String) res.get("saHash");
		
    	LinkedList<Map> results;
    	
    	if(saHash.equals("none")){
    		results = JobDAO.getRecentJobs();
    	}else{
    		results = JobDAO.getRecentJobs(saHash);
    	}
    	
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
