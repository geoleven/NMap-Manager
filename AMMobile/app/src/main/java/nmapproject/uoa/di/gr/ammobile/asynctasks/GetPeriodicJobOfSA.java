package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

public class GetPeriodicJobOfSA extends AsyncTask<String , Void , LinkedList> {
    @Override
    protected LinkedList doInBackground(String... params) {

        String saHash = params[0];

        Log.d("ASD", saHash);

        return NetworkRequests.getPeriodicJobOfSA(saHash);

    }
}
