package nmapproject.uoa.di.gr.ammobile.DB;

import android.util.Log;

/**
 * helper class for jobs
 */
public class Job {
    /**
     * params
     */
    public String parameters;
    /**
     * is it periodic
     */
    public int periodic;
    /**
     * period time
     */
    public int time;
    /**
     * the sas hash
     */
    public String saHash;

    /**
     * Constructor
     */
    public Job(String parameters , int periodic , int time , String saHash){
        this.parameters = parameters;
        this.periodic = periodic;
        this.time = time;
        this.saHash = saHash;
    }

    /**
     * prints the job
     */
    public void print(){
        Log.d("JOB","["+parameters+" , "+periodic+" , "+" , "+periodic+" , "+time+" , "+saHash+"]");
    }

}
