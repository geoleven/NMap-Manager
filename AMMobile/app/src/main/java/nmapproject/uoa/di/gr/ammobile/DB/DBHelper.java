package nmapproject.uoa.di.gr.ammobile.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;

/**
 * handles the db
 */
public class DBHelper extends SQLiteOpenHelper {
    /**
     * name of the db
     */
    public static final String DATABASE_NAME = "nmap.db";
    /**
     * verison of the db
     */
    public static final int version = 2;

    /**
     * Constructor
     *
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    /**
     * on Create event
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE jobs " +
                        "(" +
                        "`id` integer primary key," +
                        "`parameters` text," +
                        "`time` interger," +
                        "`periodic` integer," +
                        "`sa_hash` text" +
                        ")"
        );

        db.execSQL("CREATE TABLE cred (id integer primary key , key text , value text)");

    }

    /**
     * on upgrade event
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS jobs");
        db.execSQL("DROP TABLE IF EXISTS cred");
        onCreate(db);
    }

    /**
     * clears jobs table
     */
    public void clearJobs(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from jobs");
    }

    /**
     * clears cred table
     */
    public void clearCreds(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from cred");
    }

    /**
     * deletes a key from creds table
     * @param key
     * the key
     */
    public void clearCred(String key){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from cred where key = \'"+key+"\'");
    }

    /**
     * creates a new job record given all the info
     * @return
     *  true
     */
    public boolean insertJob(String parameters , int time , int periodic , String sa_hash) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("parameters", parameters);
        contentValues.put("time", time);
        contentValues.put("periodic", periodic);
        contentValues.put("sa_hash", sa_hash);
        db.insert("jobs", null, contentValues);
        return true;
    }

    /**
     * creates a new cred record given all the info
     * @return
     *  true
     */
    public boolean insertCred(String key, String value) {

        clearCred(key);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", key);
        contentValues.put("value", value);
        db.insert("cred", null, contentValues);

        return true;
    }

    /**
     * inserts jobs to the db from a list
     * @param jobList
     *  the list
     */
    public void storeJobs(LinkedList<Job> jobList){
        for(Job j : jobList){
            insertJob(j.parameters , j.time , j.periodic , j.saHash);
        }
    }

    /**
     * prints all jobs
     */
    public void printAllJobs(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM jobs" , null);

        res.moveToFirst();

        boolean in = false;
        while(res.isAfterLast() == false){
            in = true;
            Log.d("DB" ,
                "[" +
                    res.getInt(res.getColumnIndex("id"))+" , "+
                    res.getString(res.getColumnIndex("parameters")) +" , "+
                    res.getInt(res.getColumnIndex("time")) +" , "+
                    res.getInt(res.getColumnIndex("periodic")) +" , "+
                    res.getString(res.getColumnIndex("sa_hash"))+
                "]"
            );
            res.moveToNext();
        }

        if(!in){
            Log.d("DB" , "Table jobs is empty");
        }

    }

    /**
     * prints all creds
     */
    public void printCreds(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM cred" , null);

        res.moveToFirst();

        boolean in = false;
        while(res.isAfterLast() == false){
            in = true;
            Log.d("DB" ,
                    "[" +
                            res.getInt(res.getColumnIndex("id"))+" , "+
                            res.getString(res.getColumnIndex("key")) +" , "+
                            res.getString(res.getColumnIndex("value")) +
                            "]"
            );
            res.moveToNext();
        }

        if(!in){
            Log.d("DB" , "Table cred is empty");
        }
    }

    /**
     * returns all list of all the pending jobs
     * @return
     *  a list of jobs
     */
    public LinkedList<Job> pendingJobs(){

        LinkedList<Job> jobs = new LinkedList<Job>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM jobs" , null);

        res.moveToFirst();

        while(res.isAfterLast() == false){

            jobs.add(
                    new Job(
                            res.getString(res.getColumnIndex("parameters")),
                            res.getInt(res.getColumnIndex("periodic")),
                            res.getInt(res.getColumnIndex("time")),
                            res.getString(res.getColumnIndex("sa_hash"))
                    )
            );

            res.moveToNext();
        }

        return jobs;
    }

    public String getCred(String key){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM cred where key = \'"+key+"\'" , null);

        res.moveToFirst();

        String ret = null;
        while(res.isAfterLast() == false){

            ret = res.getString(res.getColumnIndex("value"));

            res.moveToNext();
        }
        return ret;
    }

}
