package main.java.gr.uoa.di.NmapProject.SA;

public class Result {
	
	public NmapJob job;
	public String result;
	
	public Result(NmapJob j ,String r){
		job = j;
		result = r;
	}
	
	public void print(){
		System.out.println("Result of job :");
		job.print();
		System.out.println(result);
	}
}
