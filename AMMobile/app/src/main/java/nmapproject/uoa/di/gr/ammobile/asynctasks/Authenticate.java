package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;

import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

public class Authenticate extends AsyncTask<String , Void , String > {

    @Override
    protected String doInBackground(String... params) {

        String email = params[0];
        String password = params[1];

        return NetworkRequests.loginRequest(email, password);
    }
}
