package nmapproject.uoa.di.gr.ammobile;

import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/**
 * Created by fozzip on 1/21/16.
 */
public class NetworkRequests {

    public static final String baseURI = "http://10.0.2.2:8080/am/";

    public static boolean loginRequest(String email , String password){
        final String url = baseURI+"login";

        LinkedHashMap send = new LinkedHashMap();

        send.put("email" , email);
        send.put("password" , password);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        LinkedHashMap res = restTemplate.postForObject(url, send ,LinkedHashMap.class);

        Log.d("LOGIN_REQUEST", String.valueOf(res));

        return true;
    }

}
