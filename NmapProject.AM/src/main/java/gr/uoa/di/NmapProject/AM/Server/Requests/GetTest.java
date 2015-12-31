package gr.uoa.di.NmapProject.AM.Server.Requests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

/**
 * Test web resource
 */
@Path("gettest")
public class GetTest {
	/**
     *
     * Handles Test request
     *
     * @return the params given 
     */
    @SuppressWarnings("unchecked")
	@GET
    @Path("{param1}/{param2}/{param3}")
    @Produces("application/json")
    public Response getIt(
		@PathParam("param1") String param1,
		@PathParam("param2") String param2,
		@PathParam("param3") String param3
	) {
		
    	System.out.println("Got : "+param1+" , "+param2+" , "+param3);
    	
    	JSONObject obj2=new JSONObject();
    	obj2.put("its ","ok");
		return Response.status(200).entity(obj2.toJSONString()).build();
    }
}
