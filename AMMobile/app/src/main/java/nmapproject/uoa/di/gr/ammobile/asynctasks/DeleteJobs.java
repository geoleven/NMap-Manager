package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

public class DeleteJobs extends AsyncTask<LinkedList, Void , Void> {

    @Override
    protected Void doInBackground(LinkedList... params) {

        LinkedList jobs = params[0];

        NetworkRequests.deleteJobs(jobs);

        return null;
    }
}
