package nmapproject.uoa.di.gr.ammobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    public SAMonitorFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sa_status, container, false);

        hashList = ((ListView) view.findViewById(R.id.OnlineHashList));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SAInfo info = new SAInfo();
        info.execute();

        try {
            LinkedList<Map> mylist = info.get();

//            int size  = mylist.size();
//            String[] hashArray = new String[size];
//            Boolean[] indiArray = new Boolean[size];

            Log.d("sas", String.valueOf(mylist));

            OnlineIndicator<String> hashAdapter = new OnlineIndicator<String>(getActivity(), R.layout.online_checklist, R.id.samListText, R.id.onlineListIcon);

            for (Map curMap : mylist) {
//                hashArray[counter] = (String) curMap.get("unionHash");
//                indiArray[counter] = (boolean) curMap.get("status");

                hashAdapter.add((String) curMap.get("unionHash"), (boolean) curMap.get("status"));
            }

            hashList.setAdapter(hashAdapter);

//            hashList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

//            int counter = 0;
//            for (Map curMap : mylist){
////                hashList.setItemChecked(counter, (boolean) curMap.get("status"));
//                hashList.setItemChecked(counter, true);
//                ++counter;
//            }

//            ArrayAdapter<String> hashAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked, hashArray);

//            hashList.setAdapter(hashAdapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
