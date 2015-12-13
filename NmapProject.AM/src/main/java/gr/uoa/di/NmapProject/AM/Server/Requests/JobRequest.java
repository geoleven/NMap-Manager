package gr.uoa.di.NmapProject.AM.Server.Requests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

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
    @GET
    @Path("/get")
    @Produces("application/json")
    public Response getIt() {
    	
    	JSONObject resp=new JSONObject();
    	
    	resp.put("response","partarcidiamou");
		
    	return Response.status(200).entity(resp.toJSONString()).build();
    }
}
