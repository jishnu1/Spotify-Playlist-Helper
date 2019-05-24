
public class Occurrence {
	
	String playlist;
	int frequency;
	
	public Occurrence(String list, int freq) {
		playlist = list;
		frequency = freq;
	}
	
	public String toString()
	{
		return "(" + playlist + "," + frequency + ")";
	}

}
