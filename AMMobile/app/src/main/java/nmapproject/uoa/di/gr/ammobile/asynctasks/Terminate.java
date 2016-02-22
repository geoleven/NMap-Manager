package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * Async task for sa termination
 */
public class Terminate extends AsyncTask<String , Void , Void> {
    /**
     * handles the request
     * @param params
     * params[0] is sa hash
     * @return
     * dead sas dont talk
     */
    @Override
    protected Void doInBackground(String... params) {

        String hash = params[0];

        NetworkRequests.terminate(hash);

        return null;
    }
}
