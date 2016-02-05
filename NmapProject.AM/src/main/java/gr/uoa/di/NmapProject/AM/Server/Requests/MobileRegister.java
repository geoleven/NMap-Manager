package gr.uoa.di.NmapProject.AM.Server.Requests;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import gr.uoa.di.NmapProject.AM.DB.SA;
import gr.uoa.di.NmapProject.AM.DB.SADAO;
import gr.uoa.di.NmapProject.AM.DB.User;
import gr.uoa.di.NmapProject.AM.DB.UserDAO;

/**
 * Register web Resource
 */
@Path("mobileregister")
public class MobileRegister {
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
    	
    	User user = new User((JSONObject)(new JSONParser()).parse(req));
    	
    	String status;
    	if(!UserDAO.exists(user.email)){
    		System.out.println("Got new registration request: ");
    		user.print();
    		UserDAO.add(user);
    		status = "Success";
    	}else{
    		status = "Email is already in use";
    	}
    	
    	JSONObject response = new JSONObject();
    	response.put("status",status);
    	
		return Response.status(200).entity(response.toJSONString()).build();
    }
}
