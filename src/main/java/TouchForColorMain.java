import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Color;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;

public class TouchForColorMain {
	final static ClarifaiClient client = new ClarifaiBuilder("<clientid>", "<clientsecret>").client(new OkHttpClient()).buildSync();
	
	public static void main(String[] args) throws FileNotFoundException {
		String input = "";
		ClarifaiResponse<List<ClarifaiOutput<Color>>> colors = client.getDefaultModels().colorModel().predict()
		    .withInputs(ClarifaiInput.forImage(ClarifaiImage.of("http://www.warrenphotographic.co.uk/photography/bigs/19634-Ginger-Maine-Coon-kitten-with-sombrero-hat-on-white-background.jpg")))
		    .executeSync();
		
		int i = 0;
		if (colors.isSuccessful()) {
			System.out.println("Colors: ");
			List<ClarifaiOutput<Color>> results = colors.get();
			for (ClarifaiOutput<Color> co : results) {
				for (Color c : co.data()) {
					String name = c.webSafeColorName();
					if (i < 5) {
						input = input + name + " ";
						i++;
						System.out.println("   " + name);
					}
				}
			}
		}
		
		ClarifaiResponse<List<ClarifaiOutput<Concept>>> tags = client.getDefaultModels().generalModel().predict()
		    .withInputs(
		        ClarifaiInput.forImage(ClarifaiImage.of("http://www.warrenphotographic.co.uk/photography/bigs/19634-Ginger-Maine-Coon-kitten-with-sombrero-hat-on-white-background.jpg"))
		    )
		    .executeSync();
		
		System.out.println("----------");
		
		i = 0;
		if (tags.isSuccessful()) {
			System.out.println("Tags: ");
			List<ClarifaiOutput<Concept>> results = tags.get();
			for (ClarifaiOutput<Concept> co : results) {
				for (Concept c : co.data()) {
					String name = c.name();
					if (i < 5) {
						System.out.println("   " + name);
						input = input + name + " ";
						i++;
					}
				}
			}
		}
		Scanner fileScan = new Scanner(new File("braille.txt"));
		BrailleTranslator brailleLetters = new BrailleTranslator(fileScan);
		List<BrailleLetter> braille = brailleLetters.getBraille(input.toLowerCase());
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String filename = "result.txt";
		PrintStream out = new PrintStream(new File(filename));
		for (BrailleLetter letter : braille) {
			out.println(letter.toString());
		}
	}

}