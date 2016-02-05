package nmapproject.uoa.di.gr.ammobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import nmapproject.uoa.di.gr.ammobile.R;
import nmapproject.uoa.di.gr.ammobile.adapters.OnlineIndicator;
import nmapproject.uoa.di.gr.ammobile.asynctasks.SAInfo;

public class SAMonitorFragment extends Fragment {

    private static final String mTag = "SAM";
    private ListView hashList;
    private Button refreshSAM;

    public SAMonitorFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sa_status, container, false);

        hashList = ((ListView) view.findViewById(R.id.OnlineHashList));
        refreshSAM = ((Button) view.findViewById(R.id.RefreshSAM));

        refreshSAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                OnlineIndicator<String> prevAdapter;
                if (hashList != null && (prevAdapter = (OnlineIndicator<String>) hashList.getAdapter()) != null) {
                    SAInfo info = new SAInfo();
                    info.execute();
                    try {
                        LinkedList<Map> myList = info.get();
                        prevAdapter.removeAll();
                        for (Map curMap : myList) {
                            prevAdapter.add((String) curMap.get("unionHash"), (boolean) curMap.get("status"));
                        }
                        hashList.setAdapter(prevAdapter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SAInfo info = new SAInfo();
        info.execute();

        try {
            LinkedList<Map> myList = info.get();
            Log.d("sas", String.valueOf(myList));
            OnlineIndicator<String> hashAdapter = new OnlineIndicator<String>(getActivity(), R.layout.online_checklist, R.id.samListText, R.id.onlineListIcon);

            for (Map curMap : myList) {
                hashAdapter.add((String) curMap.get("unionHash"), (boolean) curMap.get("status"));
            }

            hashList.setAdapter(hashAdapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
