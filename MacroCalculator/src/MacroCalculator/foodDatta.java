package MacroCalculator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class foodDatta {
	public static void main(String[] args) {
		ArrayList<FoodElement> list = new ArrayList<FoodElement>();
		try {
			BufferedReader sc = new BufferedReader(new FileReader("/Users/nicoleandrews/eclipse-workspace/MacroCalculator/src/macros_dataset.csv")); 
			// first line headers
			String line = "";
			try {
				sc.readLine();
				while((line = sc.readLine())!= null) {
					String result = line.replaceAll("\"", "");
					String[] lineArray = result.split(",");
					
					FoodElement food = new FoodElement(lineArray[0]);
					food.setProtein(Double.parseDouble(lineArray[1]));
					food.setCarbs(Double.parseDouble(lineArray[2]));
					food.setFat(Double.parseDouble(lineArray[3]));
	
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
