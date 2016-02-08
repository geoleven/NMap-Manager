package nmapproject.uoa.di.gr.ammobile.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.asynctasks.GetResults;

public class AllSAResultsFragment extends Fragment {
    private static final String mTag = "ASAR";

//    private TextView aSAResultsArea;
    private WebView aSAResultsArea;
    private Button asarResBtn;
    private NumberPicker asarNumberPicker;

    String selectedSA = null;

    public AllSAResultsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_sa_results, container, false);

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

}
