package nmapproject.uoa.di.gr.ammobile.operations;


import android.util.Log;

public class NetworkThread extends Thread {
    private static final String TAG = "NetworkStatus";
    private static final int Delaytime = 3;

    @Override
    public void run(){

        while(true){

            try {
                Thread.sleep(Delaytime*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            NetworkStatus.getInstance().update();

        }

    }
}
