package nmapproject.uoa.di.gr.ammobile.activities;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import nmapproject.uoa.di.gr.ammobile.fragments.AfterLoginFragment;
import nmapproject.uoa.di.gr.ammobile.fragments.SAMonitorFragment;
import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkThread;


public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Thread network = new NetworkThread();
        network.start();

        Fragment afl = new AfterLoginFragment();
        FragmentTransaction aflTransaction = getFragmentManager().beginTransaction();
        aflTransaction.add(R.id.fragment_container, afl, afl.getTag());
        aflTransaction.commit();


    }

    private void addDrawerItems() {
        String[] actionArray = {"Activity chooser", "", getString(R.string.CVBtn1), getString(R.string.CVBtn2),
                getString(R.string.CVBtn3), getString(R.string.CVBtn4),
                getString(R.string.CVBtn5), getString(R.string.CVBtn6), "", getString(R.string.Logout) };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, actionArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        if(!(getFragmentManager().findFragmentById(R.id.fragment_container) instanceof AfterLoginFragment)) {
                            Fragment afl = new AfterLoginFragment();
                            FragmentTransaction aflTransaction = getFragmentManager().beginTransaction();
                            aflTransaction.replace(R.id.fragment_container, afl);
                            aflTransaction.addToBackStack(null);
                            aflTransaction.commit();
                        }
                        break;
                    case 2:
                        if(!(getFragmentManager().findFragmentById(R.id.fragment_container) instanceof SAMonitorFragment)) {
                            Fragment sam = new SAMonitorFragment();
                            FragmentTransaction samTransaction = getFragmentManager().beginTransaction();
                            samTransaction.replace(R.id.fragment_container, sam);
                            samTransaction.addToBackStack(null);
                            samTransaction.commit();
                        }
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(getString(R.string.title_activity_after_login));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                else
                    mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            Toast.makeText(MainActivity.this, "Going to login!", Toast.LENGTH_SHORT).show();
            //super.onBackPressed();
        }
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        if (getIntent().getBooleanExtra("EXIT", false)){
//            finish();
//            moveTaskToBack(true);
//            System.exit(0);
//        }
//
//        Thread background = new Thread() {
//            public void run() {
//                try {
//                    Intent intent = new Intent(getBaseContext(),LoginActivity.class);
//                    startActivity(intent);
//                } catch (Exception e) {
//
//                }
//            }
//        };
//        // start thread
//        background.start();
//    }

}
