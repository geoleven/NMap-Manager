package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import java.util.LinkedList;
import java.util.Map;

import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * AsyncTask for getting results
 */
public class GetResults extends AsyncTask<Void , Void , LinkedList> {
    /**
     * hash of sa
     */
    private String saHash;
    /**
     * number of results
     */
    private int number;

    /**
     * Constructor with hash
     */
    public GetResults(String saHash , int number){
        this.saHash = saHash;
        this.number = number;
    }

    /**
     *Constructor for all sas
     */
    public GetResults(int number){
        saHash = "none";
        this.number = number;
    }

    /**
     * handles the request
     * @param params
     * no params
     * @return
     * no return
     */
    @Override
    protected LinkedList doInBackground(Void... params) {

        LinkedList<Map> results = NetworkRequests.getResults(saHash , number);

        return results;
    }
}
