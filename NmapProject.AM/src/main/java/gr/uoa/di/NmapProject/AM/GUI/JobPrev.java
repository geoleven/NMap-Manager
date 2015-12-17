package gr.uoa.di.NmapProject.AM.GUI;

public class JobPrev {
	public String params = null;
	public String periodic = null;
	public int period = -1;
	public int id = -1;

	public JobPrev(int i, String p, int per, String ispr) {
		params = p;
		periodic = ispr;
		period = per;
		id = i;
	}

	@Override
	public String toString() {
		return String.format("<html><pre>%s</pre></html>",
				"ID: " + id + "\t Parameters: " + params + "\t Period: " + period);
	}
}
