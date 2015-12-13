package gr.uoa.di.NmapProject.AM.Server.Requests;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

import gr.uoa.di.NmapProject.AM.DB.Job;
import gr.uoa.di.NmapProject.AM.DB.JobDAO;
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
    	
    	resp.put("response","eisavlakas");
    	return Response.status(200).entity(resp.toJSONString()).build();
    }
}
