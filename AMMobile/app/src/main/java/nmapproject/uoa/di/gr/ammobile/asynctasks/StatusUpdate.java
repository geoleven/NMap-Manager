package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

public class StatusUpdate extends AsyncTask<Void , Void , LinkedList> {
    @Override
    protected LinkedList doInBackground(Void... params) {

        return NetworkRequests.statusUpdate();
    }
}
