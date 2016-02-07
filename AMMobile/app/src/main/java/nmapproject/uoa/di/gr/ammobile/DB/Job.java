package nmapproject.uoa.di.gr.ammobile.DB;

import android.util.Log;

/**
 * Created by fozzip on 2/2/16.
 */
public class Job {

    public String parameters;

    public int periodic;

    public int time;

    public String saHash;

    public Job(String parameters , int periodic , int time , String saHash){
        this.parameters = parameters;
        this.periodic = periodic;
        this.time = time;
        this.saHash = saHash;
    }


    public void print(){
        Log.d("JOB","["+parameters+" , "+periodic+" , "+" , "+periodic+" , "+time+" , "+saHash+"]");
    }

}
