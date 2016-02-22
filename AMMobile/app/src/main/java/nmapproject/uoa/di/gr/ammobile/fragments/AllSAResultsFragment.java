package nmapproject.uoa.di.gr.ammobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.GetResults;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

/**
 * The fragment that show results for all S.A.s.
 */
public class AllSAResultsFragment extends Fragment {
    private static final String mTag = "ASAR";


    private WebView aSAResultsArea;
//    private TextView aSAResultsArea;
    private Button asarResBtn;
    private NumberPicker asarNumberPicker;

    String selectedSA = null;

    /**
     * Creates the fragment
     */
    public AllSAResultsFragment() {}

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
        View view = inflater.inflate(R.layout.fragment_all_sa_results, container, false);

        onlineCheck();

//        aSAResultsArea = (TextView) view.findViewById(R.id.ASAResultsArea);
        aSAResultsArea = (WebView) view.findViewById(R.id.ASAResultsArea);
        asarResBtn = (Button) view.findViewById(R.id.asarResBtn);
        asarNumberPicker = (NumberPicker) view.findViewById(R.id.asarNumberPicker);

        asarNumberPicker.setMinValue(1);
        asarNumberPicker.setMaxValue(100);
        asarNumberPicker.setValue(1);

        asarResBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = asarNumberPicker.getValue();

                GetResults task = new GetResults(number);

                task.execute();

                try {
                    LinkedList<Map> results = task.get();

                    String displayText = "";

                    for (Map m : results) {
                        displayText += (String) m.get("xml") + "\n\n\n";
                    }
                    Log.d(mTag , displayText);
//                    aSAResultsArea.setText(Html.fromHtml(displayText));
                    aSAResultsArea.loadDataWithBaseURL("", displayText, "text/html", "UTF-8", "");

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
     * The function to check if the AM is online.
     */
    private void onlineCheck(){
        if(!NetworkStatus.getInstance().isOnline()){
            Toast.makeText(getActivity(), "AM is offline!", Toast.LENGTH_LONG).show();
        }
    }


}
