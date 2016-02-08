package nmapproject.uoa.di.gr.ammobile.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.DB.DBHelper;
import nmapproject.uoa.di.gr.ammobile.DB.Job;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkRequests;
import nmapproject.uoa.di.gr.ammobile.operations.NetworkStatus;

public class SendJobs extends AsyncTask<LinkedList<Job> , Void , Void>{

    private Context myContext;

    public SendJobs(Context c){
        myContext = c;
    }

    @Override
    protected Void doInBackground(LinkedList<Job>... params) {

        LinkedList<Job> jobList = params[0];

        Log.d("SENDJOBS", String.valueOf(jobList));

        NetworkStatus status = NetworkStatus.getInstance();

        if(status.isOnline() == true)
            NetworkRequests.sendJobs(jobList);
        else{
            DBHelper db = new DBHelper(myContext);
            db.storeJobs(jobList);
        }
        return null;
    }
}
