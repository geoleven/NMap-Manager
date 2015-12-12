package main.java.gr.uoa.di.NmapProject.AM;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

/**
 * Root resource (exposed at "register" path)
 */
@Path("gettest")
public class GetTest {
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
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
