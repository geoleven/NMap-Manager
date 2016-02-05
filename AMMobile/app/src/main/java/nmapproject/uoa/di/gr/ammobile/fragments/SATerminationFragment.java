package nmapproject.uoa.di.gr.ammobile.fragments;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.LinkedList;

import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.Terminate;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

public class SATerminationFragment extends Fragment {
    private static final String mTag = "SAT";

    private Button satRefBtn;
    private Button satTermBtn;
    private Spinner saTerminationList;
    private String selectedSA = null;
    private View prevSelectedView = null;

    public SATerminationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sa_termination, container, false);

        satRefBtn = (Button) view.findViewById(R.id.satRefBtn);
        satTermBtn = (Button) view.findViewById(R.id.satTermBtn);
        saTerminationList = (Spinner) view.findViewById(R.id.saTerminationList);

//        saTerminationList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        saTerminationList.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSA = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        satRefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshList();
            }
        });

        satTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Terminate term = new Terminate();
                term.execute(selectedSA);
                refreshList();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    public void refreshList() {
        LinkedList<String> online = NetworkStatus.getInstance().onlineSAs();
        String[] listElems = online.toArray(new String[online.size()]);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (int i = 0; i < listElems.length; i++) {
            spinnerArrayAdapter.add(listElems[i]);
            spinnerArrayAdapter.notifyDataSetChanged();
        }

        saTerminationList.setAdapter(spinnerArrayAdapter);
        selectedSA = null;
    }
}
