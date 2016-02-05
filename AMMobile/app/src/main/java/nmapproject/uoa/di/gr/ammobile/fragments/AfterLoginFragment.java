package nmapproject.uoa.di.gr.ammobile.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import nmapproject.uoa.di.gr.ammobile.R;

public class AfterLoginFragment extends Fragment /*implements View.OnClickListener*/ {

    private static final String mTag = "ALF";
    private Button saMonitorBtn;
    private Button saInsertJobBtn;
    private Button saSpeResultsBtn;
    private Button saGenResultsBtn;
    private Button saTerminateBtn;
    private Button saJobDeletionBtn;
    private Button logoutBtn;

    public AfterLoginFragment() {

    }

    public static AfterLoginFragment newInstance() {
        AfterLoginFragment alf = new  AfterLoginFragment();
        return alf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //fill the parameters here
        }
    }

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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
        saMonitorBtn.setEnabled(true);
    }

//    @Override
//    public void onClick(View v) {
//
//    }
}
