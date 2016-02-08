package nmapproject.uoa.di.gr.ammobile.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import nmapproject.uoa.di.gr.ammobile.DB.DBHelper;
import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.Authenticate;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText email;
    private EditText password;
    private Button loginBtn;
    private TextView regLink;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        email = (EditText) findViewById(R.id.loginMailInp);
        password = (EditText) findViewById(R.id.loginPassInp);
        loginBtn = (Button) findViewById(R.id.loginButton);
        regLink = ((TextView) findViewById(R.id.loginRegLink));


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    login();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                onLoginSuccess();
            }
        });

        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regLink.setTextColor(Color.MAGENTA);
                Toast.makeText(getBaseContext(), "Registration process", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                regLink.setEnabled(true);
            }
        });

        DBHelper db = new DBHelper(getApplicationContext());
        db.clearJobs();

    }

    public void login() throws ExecutionException, InterruptedException {
        loginBtn.setEnabled(false);

        if (!validate()) {
            onLoginFailed();
            return;
        }

        Authenticate a = new Authenticate();
        a.execute(email.getText().toString(), password.getText().toString());

        String authenticationResult = a.get();

        if (authenticationResult.equals("ok")) {
            onLoginSuccess();
        } else {
            onLoginFailed(authenticationResult);
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

    public void onLoginFailed(String message) {
        Toast.makeText(getBaseContext(), "Login failed : "+message, Toast.LENGTH_LONG).show();
        loginBtn.setEnabled(true);
    }

    public void onLoginSuccess() {
        Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        loginBtn.setEnabled(true);
    }
}

