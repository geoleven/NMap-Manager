package nmapproject.uoa.di.gr.ammobile.operations;


import android.util.Log;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import nmapproject.uoa.di.gr.ammobile.asynctasks.SAInfo;
import nmapproject.uoa.di.gr.ammobile.asynctasks.StatusUpdate;

public class NetworkStatus {
    private static NetworkStatus ourInstance = new NetworkStatus();

    public static NetworkStatus getInstance() {
        return ourInstance;
    }

    private NetworkStatus() {
        onlineSA = new LinkedList();
    }

    public LinkedList onlineSA;
    public boolean online;

    public void update(){

        StatusUpdate a = new StatusUpdate();
        a.execute();

        try {
            onlineSA = a.get();

            if(onlineSA == null){
                online = false;
            }else{
                online = true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline(){
        return online;
    }

    public LinkedList<String> onlineSAs(){
        return onlineSA;
    }

}
