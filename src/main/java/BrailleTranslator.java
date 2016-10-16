import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BrailleTranslator {
	Map<Character, BrailleLetter> letters;
	
	public BrailleTranslator(Scanner fileScan) {
		letters = new HashMap<Character, BrailleLetter>();
		while (fileScan.hasNextLine()) {
			Scanner lineScan = new Scanner(fileScan.nextLine());
			char letter = lineScan.next().charAt(0);
			boolean[] raised = new boolean[6];
			for (int i = 0; i < raised.length; i++) {
				if (lineScan.nextInt() == 1) {
					raised[i] = true;
				}
			}
			letters.put(letter, new BrailleLetter(letter, raised));
		}
		letters.put(' ', new BrailleLetter(' ', new boolean[6]));
	}
	
	public List<BrailleLetter> getBraille(String input) {
		List<BrailleLetter> result = new ArrayList<BrailleLetter>();
		for (int i = 0; i < input.length(); i++) {
			result.add(letters.get(input.charAt(i)));
		}
		return result;
	}
}
