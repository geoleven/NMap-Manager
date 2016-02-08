package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import java.util.LinkedList;
import java.util.Map;

import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;


public class GetResults extends AsyncTask<Void , Void , LinkedList> {

    private String saHash;
    private int number;

    public GetResults(String saHash , int number){
        this.saHash = saHash;
        this.number = number;
    }


    public GetResults(int number){
        saHash = "none";
        this.number = number;
    }

    @Override
    protected LinkedList doInBackground(Void... params) {

        LinkedList<Map> results = NetworkRequests.getResults(saHash , number);

        return results;
    }
}
