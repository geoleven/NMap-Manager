package nmapproject.uoa.di.gr.ammobile.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import nmapproject.uoa.di.gr.ammobile.R;

public class AllSAResultsFragment extends Fragment {
    private static final String mTag = "ASAR";

    private TextView aSAResultsArea;
    private Button asarResBtn;
    private NumberPicker asarNumberPicker;

    String selectedSA = null;

    public AllSAResultsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_sa_results, container, false);

        aSAResultsArea = (TextView) view.findViewById(R.id.ASAResultsArea);
        aSAResultsArea.setText("Large Texsgsdfg nkjsdfbnfgvskadmpampampampampampa hnfksd bigvsdhbfgjbszd\n\n\n\n\n\n\n\n\nhjkfgbsdufgvbsdiuisdbfguwebfguihsdfhui\nsdsdfsdfsdfdsssssssssssssssssssssssssssss\nsdfsdfsdfsdfsdfsdf\nsdfsdfsd\nfsdfsd\nasdfasd\nasdasd\nasdffadas\trialalalala\nTriololololoooo\ntrappppppp\ndasdasdas\n\ndasdsa");
        asarResBtn = (Button) view.findViewById(R.id.asarResBtn);
        asarNumberPicker = (NumberPicker) view.findViewById(R.id.asarNumberPicker);

        asarNumberPicker.setMinValue(1);
        asarNumberPicker.setMaxValue(100);
        asarNumberPicker.setValue(1);

        asarResBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asarNumberPicker.getValue();
                // TODO pitta kanta den kserw ti sto peos kaneis ekei
//                GetResults gr = new GetResults(2);
//                String res = "";
//                LinkedList<Map<>> all = gr.execute();
//                for (String sres : all) {
//                    res = sres + "\n\n\n";
//                }
//                saSResultsArea.setText(res);
            }
        });

        return view;
    }

}
