package nmapproject.uoa.di.gr.ammobile.operations;

import android.content.Context;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.DB.DBHelper;
import nmapproject.uoa.di.gr.ammobile.DB.Job;
import nmapproject.uoa.di.gr.ammobile.asynctasks.SendJobs;

public class NetworkThread extends Thread {
    private static final String TAG = "NetworkStatus";
    private static final int Delaytime = 3;

    private Context context;


    public NetworkThread(Context c){
        context = c;
    }

    @Override
    public void run(){

        DBHelper db = new DBHelper(context);

        while(true){


            try {
                Thread.sleep(Delaytime*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            NetworkStatus net = NetworkStatus.getInstance();

            boolean prevStatus = net.isOnline();

            net.update();

            LinkedList<Job> pendJobs = db.pendingJobs();

            if(pendJobs.size() > 0 && net.isOnline()){
                SendJobs task = new SendJobs(context);
                task.execute(pendJobs);

                db.clearJobs();
            }

            if(prevStatus != net.isOnline()){
                if(net.isOnline() == true){
                    //Toast.makeText(context , "AM is back online" , Toast.LENGTH_LONG).show();
                }else{
                    //Toast.makeText(context , "AM went offline" , Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
