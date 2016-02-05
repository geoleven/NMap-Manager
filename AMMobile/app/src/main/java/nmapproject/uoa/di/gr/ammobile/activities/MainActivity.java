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
import nmapproject.uoa.di.gr.ammobile.fragments.AllSAResultsFragment;
import nmapproject.uoa.di.gr.ammobile.fragments.JobDeletionFragment;
import nmapproject.uoa.di.gr.ammobile.fragments.SAJobInsertionFragment;
import nmapproject.uoa.di.gr.ammobile.fragments.SAMonitorFragment;
import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.fragments.SATerminationFragment;
import nmapproject.uoa.di.gr.ammobile.fragments.SingleSAResultsFragment;
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
                    case 3:
                        if(!(getFragmentManager().findFragmentById(R.id.fragment_container) instanceof SAJobInsertionFragment)) {
                            Fragment  saji = new SAJobInsertionFragment();
                            FragmentTransaction sajiTransaction = getFragmentManager().beginTransaction();
                            sajiTransaction.replace(R.id.fragment_container, saji, saji.getTag());
                            sajiTransaction.addToBackStack(null);
                            sajiTransaction.commit();
                        }
                        break;
                    case 4:
                        if(!(getFragmentManager().findFragmentById(R.id.fragment_container) instanceof SingleSAResultsFragment)) {
                            Fragment ssar = new SingleSAResultsFragment();
                            FragmentTransaction ssarTransaction = getFragmentManager().beginTransaction();
                            ssarTransaction.replace(R.id.fragment_container, ssar, ssar.getTag());
                            ssarTransaction.addToBackStack(null);
                            ssarTransaction.commit();
                        }
                        break;
                    case 5:
                        if(!(getFragmentManager().findFragmentById(R.id.fragment_container) instanceof AllSAResultsFragment)) {
                            Fragment asar = new AllSAResultsFragment();
                            FragmentTransaction asarTransaction = getFragmentManager().beginTransaction();
                            asarTransaction.replace(R.id.fragment_container, asar, asar.getTag());
                            asarTransaction.addToBackStack(null);
                            asarTransaction.commit();
                        }
                        break;
                    case 6:
                        if(!(getFragmentManager().findFragmentById(R.id.fragment_container) instanceof SATerminationFragment)) {
                            Fragment sat = new SATerminationFragment();
                            FragmentTransaction satTransaction = getFragmentManager().beginTransaction();
                            satTransaction.replace(R.id.fragment_container, sat, sat.getTag());
                            satTransaction.addToBackStack(null);
                            satTransaction.commit();
                        }
                        break;
                    case 7:
                        if(!(getFragmentManager().findFragmentById(R.id.fragment_container) instanceof SAMonitorFragment)) {
                            Fragment sajd = new JobDeletionFragment();
                            FragmentTransaction sajdTransaction = getFragmentManager().beginTransaction();
                            sajdTransaction.replace(R.id.fragment_container, sajd, sajd.getTag());
                            sajdTransaction.addToBackStack(null);
                            sajdTransaction.commit();
                        }
                        break;
                    case 9:
                        // TODO write logout code here
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
//
//
//    }

}
