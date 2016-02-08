package nmapproject.uoa.di.gr.ammobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.DB.Job;
import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.SendJobs;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

public class SAJobInsertionFragment extends Fragment {

    private static final String mTag = "SAJI";

    Button insertBtn;
    Spinner saList;
    EditText parameters;
    CheckBox periodicCheck;
    EditText period;
    Button salistref;

    String selectedSA = null;
    String parametersText = null;
    int periodic = 0;
    int periodTime = -1;

    public SAJobInsertionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_insert, container, false);

        Log.d("JI", "In job insertion");

        insertBtn = ((Button) view.findViewById(R.id.SAIInsertBtn));
        saList = (Spinner) view.findViewById(R.id.SAIChooseSASpinner);
        parameters = (EditText) view.findViewById(R.id.SAIWriteComEditText);
        periodicCheck = (CheckBox) view.findViewById(R.id.SAIIsPeriodic);
        period = (EditText) view.findViewById(R.id.SAIPeriodEditText);
        salistref = (Button) view.findViewById(R.id.SAIRefreshBtn);

        salistref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList<String> online = NetworkStatus.getInstance().onlineSAs();
                if(online == null){
                    online = new LinkedList<String>();
                }
                String[] listElems = online.toArray(new String[online.size()]);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                for (int i = 0; i < listElems.length; i++) {
                    spinnerArrayAdapter.add(listElems[i]);
                    spinnerArrayAdapter.notifyDataSetChanged();
                }

                saList.setAdapter(spinnerArrayAdapter);
                selectedSA = null;
            }
        });


        saList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("JI", "onItemSelected");
                selectedSA = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        LinkedList<String> online = NetworkStatus.getInstance().onlineSAs();
        if(online == null){
            online = new LinkedList<String>();
        }
        String[] listElems = online.toArray(new String[online.size()]);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (int i = 0; i < listElems.length; i++) {
            spinnerArrayAdapter.add(listElems[i]);
            spinnerArrayAdapter.notifyDataSetChanged();
        }

        saList.setAdapter(spinnerArrayAdapter);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onNewJob();
            }
        });
    }


    private void onNewJob() {

        insertBtn.setEnabled(false);

        parametersText = parameters.getText().toString();
        if (periodicCheck.isChecked()) {
            periodic = 1;
        }
        String periodText = period.getText().toString();
        if (periodText != null && !periodText.equals("")) {
            periodTime = Integer.parseInt(periodText);
        }

        if (validate()) {
            Toast.makeText(getActivity(), "Job insertion Success", Toast.LENGTH_LONG).show();

            LinkedList jobs = new LinkedList();
            jobs.add(new Job(parametersText, periodic, periodTime, selectedSA));

            SendJobs task = new SendJobs(getActivity());
            task.execute(jobs);
        }


        insertBtn.setEnabled(true);
    }

    private boolean validate() {

        String errorMsg = "";
        boolean valid = true;

        if (selectedSA == null || selectedSA.equals("")) {
            errorMsg += "No SA Selected\n";
            valid = false;
        }
        if (parametersText == null || parametersText.equals("")) {
            errorMsg += "Parameters field cant be empty\n";
            valid = false;
        }
        if (periodic == 1 && periodTime <= 0) {
            errorMsg += "No time given for a periodic job\n";
            valid = false;
        }

        if (valid == false) {
            Toast.makeText(getActivity(), "Job insertion Failed :\n" + errorMsg, Toast.LENGTH_LONG).show();
        }

        return valid;
    }
}

