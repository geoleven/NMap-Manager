package nmapproject.uoa.di.gr.ammobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import nmapproject.uoa.di.gr.ammobile.DB.Job;
import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.RecentJobs;
import nmapproject.uoa.di.gr.ammobile.asynctasks.SendJobs;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

/**
 * The fragment that inserts a job to an S.A..
 */
public class SAJobInsertionFragment extends Fragment {

    private static final String mTag = "SAJI";

    Button insertBtn;
    Spinner saList;
//    EditText parameters;
    AutoCompleteTextView parameters;
    CheckBox periodicCheck;
    EditText period;
    Button salistref;

    String selectedSA = null;
    String parametersText = null;
    int periodic = 0;
    int periodTime = -1;
    ArrayAdapter<String> autoCompAdapt;
    List<Map> recent;

    /**
     * Creates the fragment
     */
    public SAJobInsertionFragment() {}

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
        View view = inflater.inflate(R.layout.fragment_job_insert, container, false);

        Log.d("JI", "In job insertion");

        insertBtn = ((Button) view.findViewById(R.id.SAIInsertBtn));
        saList = (Spinner) view.findViewById(R.id.SAIChooseSASpinner);
//        parameters = (EditText) view.findViewById(R.id.SAIWriteComEditText);
        parameters = (AutoCompleteTextView) view.findViewById(R.id.SAIWriteComEditText);
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


                RecentJobs task = new RecentJobs(selectedSA);

                task.execute();

                try {
                    recent = task.get();

                    if(recent == null){
                        recent = new LinkedList<Map>();
                    }

                    String[] recentStr = new String[recent.size()];

                    int counter = 0;
                    for(Map m : recent){
                        recentStr[counter] = (String) m.get("parameters");
                        counter++;
                    }

                    autoCompAdapt = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, recentStr);
                    parameters.setAdapter(autoCompAdapt);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
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


    /**
     * Creates a new job on an S.A.
     */
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

    /**
     * A function that validates it the job to be inserted is valid
     * @return
     */
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

    /**
     * The function to check if the AM is online.
     */
    private void onlineCheck(){
        if(!NetworkStatus.getInstance().isOnline()){
            Toast.makeText(getActivity() , "AM is offline!" , Toast.LENGTH_LONG).show();
        }
    }

}

