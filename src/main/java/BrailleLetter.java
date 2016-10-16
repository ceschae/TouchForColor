public class BrailleLetter {
	final char letter;
	final boolean[] raised;
	
	public BrailleLetter(char letter, boolean[] raised) {
		this.letter = letter;
		this.raised = raised;
	}
	
	public String toString() {
		return "" + raised[0] + raised[1] + "\n" +
				raised[2] + raised[3] + "\n" +
				raised[4] + raised[5];
	}
}
