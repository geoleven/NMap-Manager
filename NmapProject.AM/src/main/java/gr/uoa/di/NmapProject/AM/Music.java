package main.java.gr.uoa.di.NmapProject.AM;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;


/**
 * Root resource (exposed at "music" path)
 */
@Path("music")
public class Music {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
    	  JSONObject obj=new JSONObject();
    	  obj.put("name","foo");
    	  System.out.print(obj);
		return Response.status(200).entity(obj.toJSONString()).build();
    }
}