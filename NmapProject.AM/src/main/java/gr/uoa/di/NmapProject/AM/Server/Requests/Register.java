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

/**
 * Register web Resource
 */
@Path("register")
public class Register {
	
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
    	
    	SA reg = new SA((JSONObject)(new JSONParser()).parse(req));
    	
    	String status = "Waiting for authentication . . .";
    	if(!SADAO.exists(reg.hash)){
    		System.out.println("Got new registration request: ");
    		reg.print();
    		SADAO.add(reg);
    	}else if (SADAO.isAccepted(reg.hash)){
    		status = "ok";
    	}
    	
    	JSONObject response = new JSONObject();
    	response.put("status",status);
    	
		return Response.status(200).entity(response.toJSONString()).build();
    }

}
