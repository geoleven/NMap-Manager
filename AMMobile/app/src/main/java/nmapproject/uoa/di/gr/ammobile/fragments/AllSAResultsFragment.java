package nmapproject.uoa.di.gr.ammobile.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nmapproject.uoa.di.gr.ammobile.R;

public class AllSAResultsFragment extends Fragment {
    private static final String mTag = "ASAR";

    public AllSAResultsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_sa_results, container, false);

        return view;
    }

}
