package gr.uoa.di.NmapProject.AM.Server.Requests;

import java.util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SA;
import gr.uoa.di.NmapProject.AM.DB.SADAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;
import gr.uoa.di.NmapProject.AM.DB.User;
import gr.uoa.di.NmapProject.AM.DB.UserDAO;
import gr.uoa.di.NmapProject.AM.Server.OnlineStatus;

/**
 * Register web Resource
 */
@Path("status")
public class StatusUpdate{
	/**
     *	
     *	Handles Login requests
     *
     * @return return status (ok if authentication is successful)
     */
	
    @SuppressWarnings("unchecked")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() throws Exception{

    	LinkedList<String> online = OnlineStatus.getInstance().getOnline();
    	
    	// Create JSON and Sent
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try{
    		
    		String jsonInString = mapper.writeValueAsString(online);
    		
    		return Response.status(200).entity(jsonInString).build();
    	
    	}catch (JsonProcessingException ex){
    		
    		System.out.println(ex.getMessage());
    		
    		JSONObject resp = new JSONObject();
    		
    		resp.put("Error", ex.getMessage());
    		return Response.status(200).entity(resp.toJSONString()).build();
    	}
    	
    }
}
