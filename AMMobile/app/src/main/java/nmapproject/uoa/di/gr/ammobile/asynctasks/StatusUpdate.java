package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * AsyncTask for status update
 */
public class StatusUpdate extends AsyncTask<Void , Void , LinkedList> {
    /**
     * handles the request
     * @param params
     *  no params
     * @return
     * returns satus update
     */
    @Override
    protected LinkedList doInBackground(Void... params) {

        return NetworkRequests.statusUpdate();
    }
}
