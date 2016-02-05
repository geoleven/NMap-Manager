package nmapproject.uoa.di.gr.ammobile.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nmapproject.uoa.di.gr.ammobile.R;

public class SingleSAResultsFragment extends Fragment {
    private static final String mTag = "SSAR";

    public SingleSAResultsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_sa_results, container, false);

        return view;
    }
}
