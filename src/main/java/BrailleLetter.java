public class BrailleLetter {
	final char letter;
	final boolean[] raised;
	
	public BrailleLetter(char letter, boolean[] raised) {
		this.letter = letter;
		this.raised = raised;
	}
	
	public String toString() {
		String result = "";
		for (boolean b : raised) {
			if (b)
				result += "1 ";
			else
				result += "0 ";
		}
		return result + "\n";
	}
}
