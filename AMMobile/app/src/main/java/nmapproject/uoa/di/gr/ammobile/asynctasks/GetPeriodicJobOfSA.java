package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * Async Task for getting periodioc jobs of SA
 */
public class GetPeriodicJobOfSA extends AsyncTask<String , Void , LinkedList> {
    /**
     * Handles the request
     * @param params
     *  params[0] is th sa hash
     * @return
     *  returns a list of jobs
     */
    @Override
    protected LinkedList doInBackground(String... params) {

        String saHash = params[0];

        Log.d("ASD", saHash);

        return NetworkRequests.getPeriodicJobOfSA(saHash);

    }
}
