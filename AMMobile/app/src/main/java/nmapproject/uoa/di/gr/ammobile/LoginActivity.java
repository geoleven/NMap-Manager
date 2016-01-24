package nmapproject.uoa.di.gr.ammobile;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by fozzip on 1/20/16.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText email;
    private EditText password;
    private Button loginBtn;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        //TODO rememberMe

        loginBtn = (Button) findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void login() throws ExecutionException, InterruptedException {
        final String emailText = email.getText().toString();
        final String passwordText = password.getText().toString();

        Log.d(TAG, emailText);
        Log.d(TAG, passwordText);

        loginBtn.setEnabled(false);

        if (!validate()) {
            onLoginFailed();
            return;
        }

        Authenticate a = new Authenticate();
        a.execute(email.getText().toString(), password.getText().toString());

        Boolean authenticationResult = a.get();

        if (authenticationResult.equals(true)) {
            onLoginSuccess();
        } else {
            onLoginFailed();
        }

    }

    public boolean validate() {
        boolean valid = true;

        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

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

        return valid;
    }

    public void onLoginFailed() {

        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginBtn.setEnabled(true);
    }

    public void onLoginSuccess() {

        Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_LONG).show();

        loginBtn.setEnabled(true);
    }
}

