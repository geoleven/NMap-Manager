package nmapproject.uoa.di.gr.ammobile.operations;

import android.util.Log;
import android.view.View;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import nmapproject.uoa.di.gr.ammobile.DB.Job;

/**
 * class for executing network requests
 */
public class NetworkRequests {
    /**
     * the base URI
     */
    public static final String baseURI = "http://192.168.1.68:8080/am/";

    /**
     * handles register request
     */
    public static String registerRequest(String email , String password){
        final String url = baseURI+"mobileregister";

        LinkedHashMap send = new LinkedHashMap();

        send.put("email" , email);
        send.put("password", password);

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            LinkedHashMap res = restTemplate.postForObject(url, send, LinkedHashMap.class);

            Log.d("RegisterRequest", String.valueOf(res));

            return (String) res.get("status");
        } catch (Exception e){
            return "AM is offline";
        }

    }

    /**
     * handles login request
     */
    public static String loginRequest(String email , String password){
        final String url = baseURI+"login";

        LinkedHashMap send = new LinkedHashMap();

        send.put("email", email);
        send.put("password", password);

        try {
            RestTemplate restTemplate = new RestTemplate();



            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LinkedHashMap res = restTemplate.postForObject(url, send, LinkedHashMap.class);

            Log.d("LOGIN_REQUEST", String.valueOf(res));

            return (String) res.get("status");

        }catch (Exception e){
            return "AM is offline";
        }
    }

    /**
     * handles request for new jobs
     */
    public static boolean sendJobs(LinkedList<Job> jobList){
        final String url = baseURI+"newjob";

        LinkedList<LinkedHashMap> send = new LinkedList();

        for( Job j : jobList){
            LinkedHashMap jobReq = new LinkedHashMap();
            jobReq.put("parameters" , j.parameters);
            jobReq.put("periodic" , j.periodic);
            jobReq.put("time" , j.time);
            jobReq.put("saHash" , j.saHash);

            send.add(jobReq);
        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        LinkedHashMap res = restTemplate.postForObject(url, send ,LinkedHashMap.class);

        Log.d("SEND_JOBS_REQUEST", String.valueOf(res));

        if(res.get("status").equals("ok")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * handles request for sa info
     */
    public static LinkedList SAInfo(){
        final String url = baseURI+"sa";

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LinkedList<LinkedHashMap> res = restTemplate.getForObject(url, LinkedList.class);

            return res;

        }catch (Exception e) {
            Log.e("SA_INFO", e.getMessage(), e);

            return null;
        }

    }

    /**
     * handles request for status update
     */
    public static LinkedList statusUpdate() {
        final String url = baseURI+"status";

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LinkedList<LinkedHashMap> res = restTemplate.getForObject(url, LinkedList.class);

            return res;

        }catch (Exception e) {

            return null;
        }
    }

    /**
     * handles request for results
     */
    public static LinkedList getResults(String saHash , int number){

        final String url = baseURI+"results";

        Map send = new LinkedHashMap();

        send.put("saHash" , saHash);
        send.put("number" , number);


        try {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LinkedList<Map> res = restTemplate.postForObject(url, send, LinkedList.class);

            return res;

        }catch (Exception e) {

            return null;
        }

    }

    /**
     * handles the request foor periodic jobs
     */
    public static LinkedList getPeriodicJobOfSA(String saHash){
        final String url = baseURI+"recent";

        Map send = new LinkedHashMap();

        send.put("saHash" , saHash);

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LinkedList<Map> res = restTemplate.postForObject(url, send, LinkedList.class);

            return res;

        }catch (Exception e) {

            return null;
        }
    }

    /**
     * handles the request for recent jobs
     */
    public static LinkedList getRecentJobs(String saHash){
        final String url = baseURI+"periodic";

        Map send = new LinkedHashMap();

        send.put("saHash" , saHash);

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LinkedList<Map> res = restTemplate.postForObject(url, send, LinkedList.class);

            return res;

        }catch (Exception e) {

            return null;
        }
    }

    /**
     * handles the request for deleting jobs
     */
    public static void deleteJobs(LinkedList<Map> jobs){

        String url = baseURI+"delete";

        LinkedList<Map> send = new LinkedList<Map>();

        for(Map m : jobs){
            Map s = new LinkedHashMap();
            s.put("id" , (int) m.get("id"));
            send.add(s);
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LinkedList<Map> res = restTemplate.postForObject(url, send, LinkedList.class);

        }catch (Exception e) {
        }
    }

    /**
     * handles the request for sa termination
     */
    public static void terminate(String hash){
        final String url = baseURI+"terminate";

        Map send = new LinkedHashMap();

        send.put("hash" , hash);

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LinkedList<Map> res = restTemplate.postForObject(url, send, LinkedList.class);

        }catch (Exception e) {
        }
    }

}
