package nmapproject.uoa.di.gr.ammobile.operations;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import nmapproject.uoa.di.gr.ammobile.asynctasks.StatusUpdate;

/**
 * Singleton Class with the online status of am and sas
 */
public class NetworkStatus {
    /**
     * instance of class
     */
    private static NetworkStatus ourInstance = new NetworkStatus();

    /**
     *returns the instance
     */
    public static NetworkStatus getInstance() {
        return ourInstance;
    }

    /**
     * constructor (private)
     */
    private NetworkStatus() {
        onlineSA = new LinkedList();
    }

    /**
     * a list of all the sas that are online
     */
    public LinkedList onlineSA;
    /**
     * true if the am s online false otherwise
     */
    public boolean online;

    /**
     * update the status
     */
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

    /**
     * returns truw if am is online otherwise false
     */
    public boolean isOnline(){
        return online;
    }

    /**
     * returns the list of online sas
     */
    public LinkedList<String> onlineSAs(){
        return onlineSA;
    }

}
