package nmapproject.uoa.di.gr.ammobile.operations;

import android.content.Context;
import java.util.LinkedList;
import nmapproject.uoa.di.gr.ammobile.DB.DBHelper;
import nmapproject.uoa.di.gr.ammobile.DB.Job;
import nmapproject.uoa.di.gr.ammobile.asynctasks.SendJobs;

/**
 * Thread for keeping NetworkStatus up to date at all times
 */
public class NetworkThread extends Thread {
    /**
     * Tag foe debugging
     */
    private static final String TAG = "NetworkStatus";
    /**
     * sec between updates
     */
    private static final int Delaytime = 3;
    /**
     * context
     */
    private Context context;

    /**
     * Constructor
     */
    public NetworkThread(Context c){
        context = c;
    }

    /**
     * update status every delaytime seconds
     */
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
