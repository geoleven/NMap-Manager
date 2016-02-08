package gr.uoa.di.NmapProject.AM.Server.Requests;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.transform.TransformerException;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.uoa.di.NmapProject.AM.DB.JobDAO;
import gr.uoa.di.NmapProject.AM.DB.Result;
import gr.uoa.di.NmapProject.AM.DB.ResultDAO;

/**
 * Jobs web resource
 */
@Path("results")
public class SendResults {
	
    /**
     * 
     * Handling requests Sending results
     * 
     */
    @SuppressWarnings("unchecked")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response result(String req) throws Exception{
    	
    	ObjectMapper mapper = new ObjectMapper();
    	LinkedHashMap res = mapper.readValue(req , LinkedHashMap.class);
    	
    	int number = (int) res.get("number");
    	String saHash = (String) res.get("saHash");
		
    	LinkedList<Map> results;
    	
    	if(saHash.equals("none")){
    		results = JobDAO.getAllResults(number);
    	}else{
    		results = JobDAO.getResults(saHash , number);
    	}
    	
    	LinkedList<String> upRes = new LinkedList();
    	
    	for(Map r : results){
    		upRes.add((String) r.get("xml"));  		
    	}
    	
    	LinkedList<String> pRes = parseResultList(upRes);
    	
    	LinkedList<Map> send = new LinkedList();
    	
    	for(String s : pRes){
    		Map m = new LinkedHashMap();
    		
    		m.put("xml", s);
    		
    		send.add(m);
    	}
    	
    	//System.out.println("Returning results : "+String.valueOf(results));

    	try{
    		
    		String jsonInString = mapper.writeValueAsString(send);
    		
    		System.out.println(jsonInString);
    		
    		return Response.status(200).entity(jsonInString).build();
    	
    	}catch (JsonProcessingException ex){
    		
    		System.out.println(ex.getMessage());
    		
    		JSONObject resp = new JSONObject();
    		
    		resp.put("Error", ex.getMessage());
    		return Response.status(200).entity(resp.toJSONString()).build();
    	}
		
    }
    
	private static LinkedList<String> parseResultList(LinkedList<String> unparsed) {
		LinkedList<String> results = new LinkedList<String>();
		if (unparsed != null) {
			for(String s : unparsed) {
				StringWriter temp = new StringWriter();
				javax.xml.transform.TransformerFactory tFactory = 
		                  javax.xml.transform.TransformerFactory.newInstance();
				try {
					javax.xml.transform.Transformer transformer = tFactory.newTransformer
					        (new javax.xml.transform.stream.StreamSource("/usr/share/nmap/nmap.xsl"));
//					(new javax.xml.transform.stream.StreamSource(new URL("http://nmap.org/svn/docs/nmap.xsl").openStream()));
					transformer.transform
				    (new javax.xml.transform.stream.StreamSource(new StringReader(s)), 
				     new javax.xml.transform.stream.StreamResult(temp));
				} catch (TransformerException e) {
					e.printStackTrace();
				}
				String temps = temp.toString();
				results.add(temps);
			}
		}
		return results;
	}
}
