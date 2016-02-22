package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * AsyncTask for sa info
 */
public class SAInfo extends AsyncTask<Void , Void , LinkedList> {
    /**
     * handles the request
     * @param params
     * no params
     * @return
     * returns sa info
     */
    @Override
    protected LinkedList doInBackground(Void... params) {

        return NetworkRequests.SAInfo();
    }

}
