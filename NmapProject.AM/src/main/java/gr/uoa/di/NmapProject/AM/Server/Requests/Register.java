package gr.uoa.di.NmapProject.AM.Server.Requests;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import gr.uoa.di.NmapProject.AM.DB.SA;
import gr.uoa.di.NmapProject.AM.DB.SADAO;

/**
 * Root resource (exposed at "register" path)
 */
@Path("register")
public class Register {
	
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
    @SuppressWarnings("unchecked")
	@POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response getIt(String req) throws Exception{
    	
    	SA reg = new SA((JSONObject)(new JSONParser()).parse(req));
    	
    	String status = "waiting for authentication";
    	if(!SADAO.exists(reg.hash)){
    		System.out.println("Got new registration request :");
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
