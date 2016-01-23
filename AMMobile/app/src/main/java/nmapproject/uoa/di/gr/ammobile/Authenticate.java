package nmapproject.uoa.di.gr.ammobile;

import  nmapproject.uoa.di.gr.ammobile.LoginActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class Authenticate extends AsyncTask<String , Void , Boolean > {

    @Override
    protected Boolean doInBackground(String... params) {

        Boolean ret = false;

        String email = params[0];
        String password = params[1];

        JSONObject json = new JSONObject();

        try {
            json.put("email" , email);
            json.put("password" , password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(email.equals("a@h.c")){
            ret = true;
        }

        return ret;
    }
}
