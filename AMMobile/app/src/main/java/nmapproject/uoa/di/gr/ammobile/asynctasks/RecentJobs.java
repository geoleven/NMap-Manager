package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;

import java.util.LinkedList;

import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * Async task for Recent Jobs
 */
public class RecentJobs extends AsyncTask<Void , Void , LinkedList> {

    /**
     *  sa Hash
     */
    String hash;

    /**
     * Constructor
     * @param saHash
     *  hash of sa
     */
    public RecentJobs(String saHash){
        hash = saHash;
    }

    /**
     * Constructor for all Sas
     */
    public RecentJobs(){
        hash = "none";
    }

    /**
     *  handles the request
     * @param params
     *  no params
     * @return
     *  no return
     */
    @Override
    protected LinkedList doInBackground(Void... params) {

        return NetworkRequests.getRecentJobs(hash);
    }
}
