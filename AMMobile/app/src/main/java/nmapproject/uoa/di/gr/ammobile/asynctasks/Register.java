package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

public class Register extends AsyncTask<String , Void , String>{

    @Override
    protected String doInBackground(String... params) {

        String email = params[0];
        String pass = params[1];
        return NetworkRequests.registerRequest(email , pass);
    }
}
