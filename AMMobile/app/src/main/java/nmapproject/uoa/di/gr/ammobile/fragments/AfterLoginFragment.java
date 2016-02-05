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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_after_login, container, false);

        //Find the +1 button
        saMonitorBtn = (Button) view.findViewById(R.id.CVBtn1);

        saMonitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment sam = new SAMonitorFragment();
                FragmentTransaction samTransaction = getFragmentManager().beginTransaction();
                samTransaction.replace(R.id.fragment_container, sam, sam.getTag());
                samTransaction.addToBackStack(null);
                samTransaction.commit();
//                samTransaction.replace(R.id.fragment_container, sam).addToBackStack(null).commit();
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
