package gr.uoa.di.NmapProject.AM.Server.Requests;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.uoa.di.NmapProject.AM.DB.Job;
import gr.uoa.di.NmapProject.AM.DB.JobDAO;
import gr.uoa.di.NmapProject.AM.DB.SADAO;

/**
 * Register web Resource
 */
@Path("newjob")
public class NewJob {
	
	/**
     *	
     *	Handles Register requests
     *
     * @return return status (ok if authentication is successful)
     */
	
    @SuppressWarnings("unchecked")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(String req) throws Exception{
    	
    	
    	ObjectMapper mapper = new ObjectMapper();
    	LinkedList<LinkedHashMap> jobList = mapper.readValue(req, LinkedList.class);
    	
    	System.out.println("IN new Job");
    	
		for(LinkedHashMap e : jobList){
			
			int saID = SADAO.hashToId((String) e.get("saHash"));
			
			System.out.println("sa ID = "+saID);
			
			boolean periodic;
			
			if((int) e.get("periodic") == 1){
				periodic = true;
			}else{
				periodic = false;
			}
		
			if(saID != -1){
				if(JobDAO.newJob(			
					new Job(
						(String) e.get("parameters"),
						periodic,
						(int) e.get("time"),
						saID,
						"pending"
					)
				) == false){
					JSONObject response = new JSONObject();
			    	response.put("status","wtf");
			    	
					return Response.status(200).entity(response.toJSONString()).build();
				}
			}
		}
    	
    	System.out.println(String.valueOf(jobList));
    	
    	JSONObject response = new JSONObject();
    	response.put("status","ok");
    	
		return Response.status(200).entity(response.toJSONString()).build();
    }

}
