package nmapproject.uoa.di.gr.ammobile;

import  nmapproject.uoa.di.gr.ammobile.LoginActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class Authenticate extends AsyncTask<String , Void , Boolean > {

    @Override
    protected Boolean doInBackground(String... params) {

        String email = params[0];
        String password = params[1];

        return NetworkRequests.loginRequest(email , password);
    }
}
