package sa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class JobFinder implements Runnable {
	
	public JobQueue queue;
	
	public BufferedReader openedFile;
	
	private Random rand;
	
	private ArrayList<String> fileHistory;
	
	//private AtomicReference<Boolean>finish;
	
//	private final Object lock = new Object();
	
	public JobFinder(JobQueue q){
		queue = q;
		openedFile = null;
		rand = new Random();
		fileHistory = new ArrayList<String>();
	
	}
	
	@Override
	public void run(){
		try {
//			while(Globals.finish.get() == false){
			while(true) {
				readJobs();
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			System.out.println("JobFinder interrupted");
		} catch (Exception e) {
			System.err.println("Unexpected exception "+e.getMessage());
		}
	}
	
	private void readJobs() throws Exception{
		
		if(openedFile == null){
			openfile();
		}
		if(openedFile != null){
			int num = rand.nextInt(10)+1;
			if(Globals.verbose) System.out.println("reading "+num+" jobs from file");
			readJobsFromFile(num);
			checkFileEmpty();
		}
	}
	
	private void openfile() throws Exception{
		if(Globals.verbose) System.out.println("Searching for new File");
		
		File folder = new File(Globals.pathName);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().equals("threadNum") == false && fileHistory.contains(listOfFiles[i].getName()) == false){
				
				if(Globals.verbose) System.out.println("Found File: "+listOfFiles[i].getName());
				
				fileHistory.add(listOfFiles[i].getName());
				
				openedFile = new BufferedReader(new InputStreamReader(new FileInputStream(Globals.pathName+listOfFiles[i].getName())));
				
				break;
				
			}
		}
		
	}
	
	//return true if file is empty after reading or true if it still has lines left
	private void readJobsFromFile(int num) throws Exception{
		String strLine;
		int counter = 0;
		while ((strLine = openedFile.readLine()) != null){
			createJob(strLine);
			counter++;
			if(counter == num)
				break;
		}
	}
	
	private void createJob(String line){
		String[] split = line.split(",");
		
		if(split.length == 4){
			NmapJob job = new NmapJob(Integer.parseInt(split[0]) , split[1] , Boolean.parseBoolean(split[2]) , Integer.parseInt(split[3]));
			
			if(Globals.verbose) job.print();
			
			queue.addJob(job);
		}
		else{
			if(Globals.verbose) System.out.println("Bad Job Format");
		}
	}
	
	private void checkFileEmpty() throws Exception{
		if(!openedFile.ready()){
			if(Globals.verbose) System.out.println("Closing empty File");
			closeFile();
		}
	}
	
	private void closeFile() throws Exception{
		openedFile.close();
		openedFile = null;
	}
	
}
