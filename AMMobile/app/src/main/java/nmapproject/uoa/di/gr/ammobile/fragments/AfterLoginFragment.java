package nmapproject.uoa.di.gr.ammobile.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import nmapproject.uoa.di.gr.ammobile.DB.DBHelper;
import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.activities.LoginActivity;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

/**
 * The fragment that is opened after login (choose operation)
 */
public class AfterLoginFragment extends Fragment /*implements View.OnClickListener*/ {

    private static final String mTag = "ALF";
    private Button saMonitorBtn;
    private Button saInsertJobBtn;
    private Button saSpeResultsBtn;
    private Button saGenResultsBtn;
    private Button saTerminateBtn;
    private Button saJobDeletionBtn;
    private Button logoutBtn;

    /**
     * Creates the fragment
     */
    public AfterLoginFragment() {}

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            //fill the parameters here
//        }
//    }

    /**
     * Manages what happens when the view is created
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState savedInstanceState
     * @return The view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_after_login, container, false);

        saMonitorBtn = (Button) view.findViewById(R.id.CVBtn1);
        saInsertJobBtn = (Button) view.findViewById(R.id.CVBtn2);
        saSpeResultsBtn = (Button) view.findViewById(R.id.CVBtn3);
        saGenResultsBtn = (Button) view.findViewById(R.id.CVBtn4);
        saTerminateBtn = (Button) view.findViewById(R.id.CVBtn5);
        saJobDeletionBtn = (Button) view.findViewById(R.id.CVBtn6);
        logoutBtn = (Button) view.findViewById(R.id.CVBtn7);

        saMonitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment sam = new SAMonitorFragment();
                FragmentTransaction samTransaction = getFragmentManager().beginTransaction();
                samTransaction.replace(R.id.fragment_container, sam, sam.getTag());
                samTransaction.addToBackStack(null);
                samTransaction.commit();
            }
        });

        saInsertJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment  saji = new SAJobInsertionFragment();
                FragmentTransaction sajiTransaction = getFragmentManager().beginTransaction();
                sajiTransaction.replace(R.id.fragment_container, saji, saji.getTag());
                sajiTransaction.addToBackStack(null);
                sajiTransaction.commit();
            }
        });

        saSpeResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ssar = new SingleSAResultsFragment();
                FragmentTransaction ssarTransaction = getFragmentManager().beginTransaction();
                ssarTransaction.replace(R.id.fragment_container, ssar, ssar.getTag());
                ssarTransaction.addToBackStack(null);
                ssarTransaction.commit();
            }
        });

        saGenResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment asar = new AllSAResultsFragment();
                FragmentTransaction asarTransaction = getFragmentManager().beginTransaction();
                asarTransaction.replace(R.id.fragment_container, asar, asar.getTag());
                asarTransaction.addToBackStack(null);
                asarTransaction.commit();
            }
        });

        saTerminateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment sat = new SATerminationFragment();
                FragmentTransaction satTransaction = getFragmentManager().beginTransaction();
                satTransaction.replace(R.id.fragment_container, sat, sat.getTag());
                satTransaction.addToBackStack(null);
                satTransaction.commit();
            }
        });

        saJobDeletionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment sajd = new JobDeletionFragment();
                FragmentTransaction sajdTransaction = getFragmentManager().beginTransaction();
                sajdTransaction.replace(R.id.fragment_container, sajd, sajd.getTag());
                sajdTransaction.addToBackStack(null);
                sajdTransaction.commit();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        return view;
    }

    /**
     * Manages what happens when the view is resumed
     */
    @Override
    public void onResume() {
        super.onResume();

        onlineCheck();

        // Refresh the state of the +1 button each time the activity receives focus.
        saMonitorBtn.setEnabled(true);


        DBHelper db = new DBHelper(getActivity());
        db.printAllJobs();

    }

//    @Override
//    public void onClick(View v) {
//
//        Fragment curFrag;
//        FragmentTransaction Transaction = getFragmentManager().beginTransaction();
//
//        switch (v.getId()) {
//            case R.id.CVBtn1:
//                curFrag = new SAMonitorFragment();
//                break;
//            case R.id.CVBtn2:
//                curFrag = new SAJobInsertionFragment();
//                break;
//            case R.id.CVBtn3:
//                curFrag = new SingleSAResultsFragment();
//                break;
//            case R.id.CVBtn4:
//                curFrag = new AllSAResultsFragment();
//                break;
//            case R.id.CVBtn5:
//                curFrag = new SATerminationFragment();
//                break;
//            case R.id.CVBtn6:
//                curFrag = new JobDeletionFragment();
//                break;
//            case R.id.CVBtn7:
//                // TODO add logout code here
//                Transaction.commit();
//                return;
//            default:
//                Transaction.commit();
//                return;
//        }
//        Transaction.replace(R.id.fragment_container, curFrag, curFrag.getTag());
//        Transaction.addToBackStack(null);
//        Transaction.commit();
//    }

    /**
     * The function to manage the logout
     */
    private void logout(){
        DBHelper db = new DBHelper(getActivity());
        db.insertCred("online" , "off");

        Toast.makeText(getActivity(), "Logging out", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    /**
     * The function to check if the AM is online.
     */
    private void onlineCheck(){
        if(!NetworkStatus.getInstance().isOnline()){
            Toast.makeText(getActivity() , "AM is offline!" , Toast.LENGTH_LONG).show();
        }
    }

}
