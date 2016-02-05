package nmapproject.uoa.di.gr.ammobile.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import nmapproject.uoa.di.gr.ammobile.R;

public class SAJobInsertionFragment extends Fragment{

    private static final String mTag = "SAJI";


    public SAJobInsertionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_insert, container, false);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
