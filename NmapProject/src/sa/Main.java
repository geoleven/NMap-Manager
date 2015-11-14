package sa;

public class Main {

	public static void main(String[] args) {
		SAProperties myp = new SAProperties();
		myp.readOneTimeThreadNumberFromFile("threadNum");
		System.out.println(myp.oneTimeJobThreadsNumber);
	}

}
