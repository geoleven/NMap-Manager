package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

public class Terminate extends AsyncTask<String , Void , Void> {
    @Override
    protected Void doInBackground(String... params) {

        String hash = params[0];

        NetworkRequests.terminate(hash);

        return null;
    }
}
