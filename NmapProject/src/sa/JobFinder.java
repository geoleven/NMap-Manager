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
	private String openedFileName;

	public JobFinder(JobQueue q) {
		queue = q;
		openedFile = null;
		openedFileName = null;
		rand = new Random();
		fileHistory = new ArrayList<String>();

	}

	@Override
	public void run() {
		try {
			// while(Globals.finish.get() == false){
			while (true) {
				aquireJobs();
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			System.out.println("JobFinder interrupted.");
		} catch (Exception e) {
			System.err.println("Unexpected exception " + e.getMessage() + " @Jobfinder.run");
		}
	}

	private void aquireJobs() throws Exception {

		if (openedFile == null) {
			openfile();
		}
		if (openedFile != null) {
			int num = rand.nextInt(10) + 1;
			if (Globals.verbose)
				System.out.println("Reading " + num + " jobs from file " + openedFileName + ".");
			readJobsFromFile(num);
			checkFileEmpty();
		}
	}

	private void openfile() throws Exception {
		// if (Globals.verbose)
		// System.out.println("Searching for new File");

		File folder = new File(Globals.pathName);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().equals("properties") == false
					&& fileHistory.contains(listOfFiles[i].getName()) == false) {

				if (Globals.verbose)
					System.out.println("Found file: " + listOfFiles[i].getName() + ".");

				fileHistory.add(listOfFiles[i].getName());

				openedFile = new BufferedReader(
						new InputStreamReader(new FileInputStream(Globals.pathName + listOfFiles[i].getName())));
				openedFileName = Globals.pathName + listOfFiles[i].getName();

				break;

			}
		}

	}

	private void readJobsFromFile(int num) throws Exception {
		String strLine;
		int counter = 0;
		while ((strLine = openedFile.readLine()) != null) {
			createJob(strLine);
			counter++;
			if (counter == num)
				break;
		}
	}

	private void createJob(String line) {
		String[] split = line.split(",");

		if (split.length == 4) {
			NmapJob currentJob = new NmapJob(Integer.parseInt(split[0]), split[1].replaceAll("-oX - ", ""), Boolean.parseBoolean(split[2]),
					Integer.parseInt(split[3]));

			if (Globals.verbose)
				currentJob.print();

			queue.addJob(currentJob);
			synchronized (queue) {
				queue.notify();
			}
		} else {
			if (Globals.verbose)
				System.err.println("Job in file is not formatted correctly.");
		}
	}

	private void checkFileEmpty() throws Exception {
		if (!openedFile.ready()) {
			if (Globals.verbose)
				System.err.println("Closing empty file.");
			closeFile();
		}
	}

	private void closeFile() throws Exception {
		openedFile.close();
		openedFile = null;
	}

}
