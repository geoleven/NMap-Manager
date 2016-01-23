package nmapproject.uoa.di.gr.ammobile;

/**
 * Created by fozzip on 1/21/16.
 */
public class NetworkRequests {

    public static final String baseURI = "http://10.0.2.2:8080/am/";

    public static WebResource getResource(String path) {
        return Client.create().resource(baseUri + path);
    }

}
