package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;

import java.util.LinkedList;

import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * Created by fozzip on 2/4/16.
 */
public class DeleteJobs extends AsyncTask<LinkedList, Void , Void> {

    @Override
    protected Void doInBackground(LinkedList... params) {

        LinkedList jobs = params[0];

        NetworkRequests.deleteJobs(jobs);

        return null;
    }
}
