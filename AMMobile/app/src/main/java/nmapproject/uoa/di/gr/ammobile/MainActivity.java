package nmapproject.uoa.di.gr.ammobile;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by fozzip on 1/19/16.
 */
public class MainActivity extends ActionBarActivity {

    private final Integer delaySplash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onResume(){
        super.onResume();
        if (getIntent().getBooleanExtra("EXIT", false)){
            finish();
            moveTaskToBack(true);
            System.exit(0);
        }
        setContentView(R.layout.activity_main);

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 2 seconds
                    sleep(2*delaySplash);
                    Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {

                }
            }
        };
        // start thread
        background.start();
    }

}
