package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;

import java.util.LinkedList;

import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * Created by fozzip on 2/8/16.
 */
public class RecentJobs extends AsyncTask<Void , Void , LinkedList> {

    String hash;

    public RecentJobs(String saHash){
        hash = saHash;
    }

    RecentJobs(){
        hash = "none";
    }

    @Override
    protected LinkedList doInBackground(Void... params) {

        return NetworkRequests.getRecentJobs(hash);
    }
}
