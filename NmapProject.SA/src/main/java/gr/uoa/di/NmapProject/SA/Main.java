package gr.uoa.di.NmapProject.SA;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONObject;

public class Main {

	public static void main(String[] args) {
		try {
			
//			Registration myr = new Registration();
			
			Client client = Client.create();
            WebResource webResource = client
                    .resource("http://localhost:8080/myapp/music/get");

            ClientResponse response = webResource
                    .accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200)
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

            String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);
			
			
//			Globals.verbose = true;
//			if (!MySystemFiles.checkProgramPathExists()) {
//				if (Globals.verbose)
//					System.err.println("The deamon cannot run without its directory '~/.myNmap'");
//				return;
//			}
//			SAProperties myProperties = new SAProperties();
//			myProperties.readThreadNum();
//			// System.out.println(myp.oneTimeJobThreadsNumber);
//
//			JobQueue jQ = new JobQueue();
//			ResultQueue rQ = new ResultQueue();
//			PeriodicJobs pj = new PeriodicJobs(rQ);
//			GetPendingJobs jf = new GetPendingJobs(jQ, pj);
//			SendResults sT = new SendResults(rQ);
//			OneTimeJobs otj = new OneTimeJobs(jQ, rQ);
//
//			Stopper stopper = new Stopper(jf, otj, pj, sT);
//			stopper.attachShutDownHook();
//			
//			otj.start();
//			jf.start();
//			sT.start();			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
