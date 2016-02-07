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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.DeleteJobs;
import nmapproject.uoa.di.gr.ammobile.asynctasks.GetPeriodicJobOfSA;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

public class JobDeletionFragment extends Fragment {
    private static final String mTag = "SAJD";

    Button deleteBtn;
    Spinner saSpin;
    Spinner jobSpin;

    String selectedSA = null;
    LinkedList<Map> jobList = new LinkedList();
    int selectedJob = -1;

    public JobDeletionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jod_deletion, container, false);

        Log.d(mTag, "In job deletion");

        deleteBtn = (Button) view.findViewById(R.id.JDDeleteBtn);
        saSpin = (Spinner) view.findViewById(R.id.JDChooseSASpinner);
        jobSpin = (Spinner) view.findViewById(R.id.JDChooseJobSpinner);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        LinkedList<String> online = NetworkStatus.getInstance().onlineSAs();

        String[] listElems = online.toArray(new String[online.size()]);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for(int i = 0;i < listElems.length; i++){
            spinnerArrayAdapter.add(listElems[i]);
            spinnerArrayAdapter.notifyDataSetChanged();
        }

        saSpin.setAdapter(spinnerArrayAdapter);

        saSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("JI", "onItemSelected");

                selectedSA = parentView.getItemAtPosition(position).toString();
                populateJobList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onDeleteJob();
            }
        });

    }

    private void populateJobList(){

        GetPeriodicJobOfSA task = new GetPeriodicJobOfSA();
        task.execute(selectedSA);

        try {

            jobList = new LinkedList();

            jobList = task.get();

            if(jobList.size() == 0){
                selectedJob = -1;
            }

            Log.d(mTag , String.valueOf(jobList));


            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            for(Map job : jobList){
                spinnerArrayAdapter.add((int) job.get("id") + " : " + (String) job.get("parameters"));
                spinnerArrayAdapter.notifyDataSetChanged();
            }

            jobSpin.setAdapter(spinnerArrayAdapter);

            jobSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    Log.d("JI", "onItemSelected");

                    selectedJob = (int) jobList.get(position).get("id");

                    Log.d(mTag , "Selected id : "+selectedJob);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    return;
                }

            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void onDeleteJob(){

        deleteBtn.setEnabled(false);

        if(validate()){
            Toast.makeText(getActivity(), "Job deletion Successful", Toast.LENGTH_LONG).show();

            LinkedList jobs = new LinkedList();

            Map m = new LinkedHashMap();
            m.put("id", selectedJob);

            jobs.add(m);

            DeleteJobs task = new DeleteJobs();
            task.execute(jobs);

        }

        populateJobList();
        deleteBtn.setEnabled(true);

    }

    private boolean validate(){

        boolean valid = true;
        String errorMsg = "";

        if(selectedJob <= 0){
            errorMsg += "No job Selected";
            valid = false;
        }

        if(!valid){
            Toast.makeText(getActivity(), "Job deletion Failed : "+errorMsg, Toast.LENGTH_LONG).show();
        }
        return valid;
    }
}