package nmapproject.uoa.di.gr.ammobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.GetResults;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

/**
 * The fragment that show results for an S.A..
 */
public class SingleSAResultsFragment extends Fragment {
    private static final String mTag = "SSAR";

//    private TextView saSResultsArea;
    private WebView saSResultsArea;
    private Spinner saSRChooseSASpinner;
    private Button ssarRefBtn;
    private Button ssarResBtn;
    private NumberPicker ssarNumberPicker;

    String selectedSA = null;

    /**
     * Creates the fragment
     */
    public SingleSAResultsFragment() {}

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
        View view = inflater.inflate(R.layout.fragment_single_sa_results, container, false);

        saSResultsArea = (WebView) view.findViewById(R.id.SASResultsArea);
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
                int number = ssarNumberPicker.getValue();

                GetResults task = new GetResults(selectedSA , number);

                task.execute();

                try {
                    LinkedList<Map> results = task.get();

                    String displayText = "";

                    for (Map m : results) {
                        displayText += (String) m.get("xml") + "\n\n\n";
                    }

//                    saSResultsArea.setText(Html.fromHtml(displayText));
                    saSResultsArea.loadDataWithBaseURL("", displayText, "text/html", "UTF-8", "");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
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

        refreshList();
    }

    /**
     * The function that refreshes the list of the S.A.s
     */
    public void refreshList() {
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

        saSRChooseSASpinner.setAdapter(spinnerArrayAdapter);
        selectedSA = null;
    }

    /**
     * The function to check if the AM is online.
     */
    private void onlineCheck(){
        if(!NetworkStatus.getInstance().isOnline()){
            Toast.makeText(getActivity(), "AM is offline!", Toast.LENGTH_LONG).show();
        }
    }



}
