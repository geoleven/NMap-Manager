package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.os.AsyncTask;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;

/**
 * Async Task for Authentication
 */
public class Authenticate extends AsyncTask<String , Void , String > {
    /**
     * Handles the request
     * @param params
     *  params[0] is email params[1] is password
     * @return
     *  returns ok if success an error message if fail
     */
    @Override
    protected String doInBackground(String... params) {

        String email = params[0];
        String password = params[1];

        return NetworkRequests.loginRequest(email, password);
    }
}
