package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * Async task for deleting jobs
 */
public class DeleteJobs extends AsyncTask<LinkedList, Void , Void> {
    /**
     * Handles the request
     * @param params
     *  params[0] is a List of jbos
     * @return
     *  doesnt return anything
     */
    @Override
    protected Void doInBackground(LinkedList... params) {

        LinkedList jobs = params[0];

        NetworkRequests.deleteJobs(jobs);

        return null;
    }
}
