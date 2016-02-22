package nmapproject.uoa.di.gr.ammobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.Authenticate;
import nmapproject.uoa.di.gr.ammobile.asynctasks.Register;

/**
 * The register activity
 */
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText email;
    private EditText password;
    private EditText repPass;
    private Button regBtn;

    /**
     * Manages what happens when the view is created
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        email = (EditText) findViewById(R.id.regMailInp);
        password = (EditText) findViewById(R.id.regPassInp);
        repPass = (EditText) findViewById(R.id.regRepPassInp);
        regBtn = (Button) findViewById(R.id.regBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    register();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * A function to register a user to the AM.
     * @throws ExecutionException ExecutionException
     * @throws InterruptedException InterruptedException
     */
    private void register() throws ExecutionException, InterruptedException{
        regBtn.setEnabled(false);

        if (!validate()) {
            onRegisterFailed();
            return;
        }

        findViewById(R.id.regLoadingPanel).setVisibility(View.VISIBLE);
        Register a = new Register();
        a.execute(email.getText().toString(), password.getText().toString());

        String registerResult = a.get();
        findViewById(R.id.regLoadingPanel).setVisibility(View.GONE);

        if (registerResult.equals("Success")) {
            onRegisterSuccess();
        } else {
            onRegisterFailed(registerResult);
        }

    }

    /**
     * A function that validates the credentials given by the user
     * @return true or false
     */
    public boolean validate() {
        boolean valid = true;

        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        final String repPassText = repPass.getText().toString();

        if (emailText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (passwordText.isEmpty() || passwordText.length() < 4 || passwordText.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        if(repPassText.isEmpty() || !repPassText.equals(passwordText)){
            repPass.setError("passwords dont match");
            valid = false;
        }else{
            repPass.setError(null);
        }

        return valid;
    }


    /**
     * Manages what happen if register fails
     * @param message message
     */
    public void onRegisterFailed(String message) {

        Toast.makeText(getBaseContext(), "Register failed : "+message, Toast.LENGTH_LONG).show();

        regBtn.setEnabled(true);
    }

    /**
     * Manages what happen if register fails
     */
    public void onRegisterFailed() {

        Toast.makeText(getBaseContext(), "Register failed", Toast.LENGTH_LONG).show();

        regBtn.setEnabled(true);
    }

    /**
     * Manages what happen if register succeeds
     */
    public void onRegisterSuccess() {
        Toast.makeText(getBaseContext(), "Register Success", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        regBtn.setEnabled(true);
    }

}
