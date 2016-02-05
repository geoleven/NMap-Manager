package nmapproject.uoa.di.gr.ammobile.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Map;

import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.GetResults;
import nmapproject.uoa.di.gr.ammobile.asynctasks.Terminate;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

public class SingleSAResultsFragment extends Fragment {
    private static final String mTag = "SSAR";

    private TextView saSResultsArea;
    private Spinner saSRChooseSASpinner;
    private Button ssarRefBtn;
    private Button ssarResBtn;
    private NumberPicker ssarNumberPicker;

    String selectedSA = null;

    public SingleSAResultsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_sa_results, container, false);

        saSResultsArea = (TextView) view.findViewById(R.id.SASResultsArea);
        saSResultsArea.setText("Large Texsgsdfg nkjsdfbnfgvskadmpampampampampampa hnfksd bigvsdhbfgjbszd\n\n\n\n\n\n\n\n\nhjkfgbsdufgvbsdiuisdbfguwebfguihsdfhui\nsdsdfsdfsdfdsssssssssssssssssssssssssssss\nsdfsdfsdfsdfsdfsdf\nsdfsdfsd\nfsdfsd\nasdfasd\nasdasd\nasdffadas\trialalalala\nTriololololoooo\ntrappppppp\ndasdasdas\n\ndasdsa");
        saSRChooseSASpinner = (Spinner) view.findViewById(R.id.SASRChooseSASpinner);
        ssarRefBtn = (Button) view.findViewById(R.id.ssarRefBtn);
        ssarResBtn = (Button) view.findViewById(R.id.ssarResBtn);
        ssarNumberPicker = (NumberPicker) view.findViewById(R.id.ssarNumberPicker);

        ssarNumberPicker.setMinValue(1);
        ssarNumberPicker.setMaxValue(100);
        ssarNumberPicker.setValue(1);


        saSRChooseSASpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSA = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        ssarRefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshList();
            }
        });

        ssarResBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ssarNumberPicker.getValue();
                // TODO pitta kanta den kserw ti sto peos kaneis ekei
//                GetResults gr = new GetResults(2);
//                String res = "";
//                LinkedList<Map<>> all = gr.execute();
//                for (String sres : all) {
//                    res = sres + "\n\n\n";
//                }
//                saSResultsArea.setText(res);
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

        saSRChooseSASpinner.setAdapter(spinnerArrayAdapter);
        selectedSA = null;
    }


}
