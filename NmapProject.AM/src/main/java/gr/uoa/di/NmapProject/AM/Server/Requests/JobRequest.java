package gr.uoa.di.NmapProject.AM.Server.Requests;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.uoa.di.NmapProject.AM.DB.Job;
import gr.uoa.di.NmapProject.AM.DB.JobDAO;
import gr.uoa.di.NmapProject.AM.DB.Result;
import gr.uoa.di.NmapProject.AM.DB.ResultDAO;
import gr.uoa.di.NmapProject.AM.DB.SADAO;

/**
 * Root resource (exposed at "register" path)
 */
@Path("job")
public class JobRequest {
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @SuppressWarnings("unchecked")
	@GET
    @Path("get/{hash}")
    @Produces("application/json")
    public Response getIt(@PathParam("hash") String curHash) {
    	
    	System.out.println("Got request for jobs from : "+curHash);
    	
    	JSONObject resp = new JSONObject();
    	int saID = SADAO.hashToId(curHash);
    	
    	if (saID == 0) {
    		resp.put("Error", "Hash not in database");
    		return Response.status(200).entity(resp.toJSONString()).build();
    	}
    	
    	LinkedList<Job> jobsToSendToSa = JobDAO.getAllSAJobs(saID);
    	for (Job j : jobsToSendToSa) {
    		j.print();
    	}
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try{
    		
    		String jsonInString = mapper.writeValueAsString(jobsToSendToSa);
    		
    		return Response.status(200).entity(jsonInString).build();
    	
    	}catch (JsonProcessingException ex){
    		
    		System.out.println(ex.getMessage());
    		
    		resp.put("Error", ex.getMessage());
    		return Response.status(200).entity(resp.toJSONString()).build();
    	}
    }
    
    @SuppressWarnings("unchecked")
	@POST
	@Path("result")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response result(String req) throws Exception{
    	
    	ObjectMapper mapper = new ObjectMapper();
    	LinkedHashMap res = mapper.readValue(req , LinkedHashMap.class);
    	
    	int jobID = (int) ((LinkedHashMap) res.get("job")).get("id");
    	int saID = JobDAO.sa(jobID);
    	String xml = (String) res.get("result");
    	
    	String status;
    	if(ResultDAO.insert(new Result(jobID , saID , xml))){
    		status = "ok";
    	}else{
    		status = "fail";
    	}
    	
    	JSONObject response = new JSONObject();
    	response.put("status",status);
    	
		return Response.status(200).entity(response.toJSONString()).build();
    }
}
