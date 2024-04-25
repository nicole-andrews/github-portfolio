package MacroCalculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MacroCalc2 {
	private static ArrayList<FoodElement> foodArray = new ArrayList<FoodElement>();
	private static ArrayList<FoodElement> breakfast = new ArrayList<FoodElement>();
	private static ArrayList<FoodElement> lunch = new ArrayList<FoodElement>();
	private static ArrayList<FoodElement> dinner = new ArrayList<FoodElement>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello, Welcome to Daily macro calculator.");
		System.out.println("Insert Food Elements to begin.");
		
		//loop for adding a new element
		//enterFoodElements();
		loadData();
		
		//menu();

				
		
		System.out.println("We will be calculating your breakfast lunch and dinner."
				+ "\nAfter every calculation you get the option again to see the list and add more food items.");
	     Scanner src = new Scanner(System.in);
	     printList();
	     addMoreFoodToList();
	     
	    System.out.println("\nFirst we will start off with breakfast.");
	    mealElements("Breakfast", breakfast);
	    printList();
	    addMoreFoodToList();
	    System.out.println("\nNow we will do lunch.");
	    mealElements("Lunch", lunch);
	    printList();
	    addMoreFoodToList();
	    System.out.println("\nFinally, We will do dinner");
	    mealElements("Dinner", dinner);
	    	     
	            
	    
	     
	     System.out.println("\nBreakfast: "+calculateMacros(breakfast));
	     System.out.println("\nLunch: "+calculateMacros(lunch));
	     System.out.println("\nDinner: "+calculateMacros(dinner));		
	}
	/**
	public static void menu( ) {
		 Scanner src = new Scanner(System.in);
		 boolean isValid = false;
		do {
			System.out.println("Choose what you would like to do:");
			System.out.println("    (1) Print Full Food List");
			System.out.println("    (2) Add a Food Item to Food List");
			System.out.println("    (3) Add a meal"); 
			String input = src.next();
			
			switch(input) {
			case "1": 
				System.out.println("Everything on the list is out of 100g.\nYou will be able to type in the percentage of the 100g you ate of each kind of food later.");
				System.out.println("Here is a list of all the food you can pick from.");
				System.out.print(printFoodList());
				isValid = true;
			case "2":
				addMoreFoodToList();
				isValid = true;
			case "3":
				System.out.println("What meal would you like to add?\n1) Breakfast, 2) Lunch, 3) Dinner");
				System.out.println("You are able to come back and add more food to you meals before the list is calculated ");
				input = src.next();
				switch(input) {
					case "1":
						 mealElements("Breakfast", breakfast);
					case "2":
						mealElements("Lunch", lunch);
					case "3":
						mealElements("Dinner", dinner);
					default:
						System.out.println("Not Valid");
				}
				
				isValid = true;
			default:
				System.out.println("Not a valid input");
			}
		}while(!isValid);
		
		
		
	}
	
	public static void pastChoices() {
		
	}
	**/
	/**
	 * asks if you want to add more food items to the list
	 */
	public static void addMoreFoodToList() {
		Scanner src = new Scanner(System.in);
		boolean validInput = false;
		do {
			System.out.println("Would you like to add more food to this list?(y/n)");
	            String answer = src.next();
	            if (answer.equals("n")) {
	                validInput = true;
	            } else if (answer.equals("y")) {
	            	validInput = true;
	            	enterFoodElements();
	            } else {
	            	System.out.println("Not a Valid Input! try again");
	            	validInput = false;
	            }
	     }while(!validInput);
	}
	
	/**
	 * Asks if you want to print the list of all food items again
	 */
	public static void printList() {
		Scanner src = new Scanner(System.in);
		boolean validInput = false;
		do {
			System.out.println("Would you like to print this list?(y/n)");
	            String answer = src.next();
	            if (answer.equals("n")) {
	                validInput = true;
	            } else if (answer.equals("y")) {
	            	validInput = true;
	            	System.out.print(printFoodList());
	            } else {
	            	System.out.println("Not a Valid Input! try again");
	            	validInput = false;
	            }
	     }while(!validInput);
	}
	
	/**
	 * creates a meal array 
	 */
	public static void mealElements(String mealName, ArrayList<FoodElement> meal) {
		boolean foodInsert = true;
		do {
			mealElementsAdder(mealName, meal);
			
			foodInsert = insertAnother();
			
		}while(foodInsert);
	}
	
	/**
	 * Adds a new element to a specific meal array 
	 */
	public static void mealElementsAdder(String mealName, ArrayList<FoodElement> meal) {
		int ogSize = meal.size();
		Boolean finished = false;
		Scanner src = new Scanner(System.in);
		do {
			System.out.print("Enter one of the food items you ate for "+ mealName+", with the exact name entered in the list: ");
			String food = src.nextLine();
			for(int i = 0; i < foodArray.size(); i++) {
				if (food.equals(foodArray.get(i).getName())) {
					meal.add(foodArray.get(i));
					foodArray.get(i).setPercent(amountConsumed());
					System.out.println("Your item was successfully added.");
					finished = true;
					break;
				} 
			}
			if(ogSize+1 != meal.size()) {
				System.out.println("thats not a valid inpout trey again with the correct spelling");
			}
				
		}while(!finished);	
	}
	
	/**
	 * Asks for the amount of item consumed out of 100g
	 * @return the double value of the percent consumed
	 */
	public static double amountConsumed() {
		Scanner src = new Scanner(System.in);
		boolean validInput = false;
		double percent = 0;
	     do {
	    	 System.out.println("Out of 100g what percent of the food did you eat? in decimal form (50% = .50)");
	            String answer = src.next();
	            try {
	            	percent = (Double.parseDouble(answer));
	            	validInput = true;
	            }catch(NumberFormatException e) {
	            	System.out.print("Not valid, try again");
	            	validInput = false;
	            }
	     }while(!validInput);
	     
	     return percent;
	}
	
	/**
	 * calculates the macro for every item on a specific meal list 
	 * @param meal what meal you are calculating for 
	 * @return the list of macros total in string form 
	 */
	public static String calculateMacros(ArrayList<FoodElement> meal) {
		int protein = 0;
		int carbs = 0;
		int fat = 0;
		int calories = 0;
		String names = "";
		for(int i = 0; i< meal.size(); i++) {
			protein += (meal.get(i).getProtein()* meal.get(i).getPercent());
			fat += meal.get(i).getFat()* meal.get(i).getPercent();
			carbs += meal.get(i).getCarbs()* meal.get(i).getPercent();
			calories += meal.get(i).getCalories()* meal.get(i).getPercent();
			names += meal.get(i).getName() +", ";
		}
		return names + "Macros: Protein: "+ protein + " Carbs: "+ carbs + " Fat: "+ fat  + " Calories: "+ calories; 
	}
	
	/**
	 * Asks you to enter food elements into the list 
	 */
	public static void enterFoodElements() {
		boolean foodInsert = true;
		do {
			insertFood();
			foodInsert = insertAnother();
		}while(foodInsert);
	}
	
	/**
	 * asks if you want to insert another item into the list
	 * this is used for both the large food list and when you are making individual meal lists
	 * @return boolean value of if you are going to insert another item or not
	 */
	public static boolean insertAnother() {
		 boolean foodInsert = true;;
		 boolean validInput = false;
	     Scanner src = new Scanner(System.in);
	     do {
	    	 System.out.println("Do you want to insert Another food element?(y/n)");
	            String answer = src.next();
	            if (answer.equals("n")) {
	                foodInsert = false;
	                validInput = false;
	            } else if (answer.equals("y")) {
	            	foodInsert=true;
	            	validInput = false;
	            } else {
	            	System.out.println("Not a Valid Input! try again");
	            	validInput = true;
	            }
	     }while(validInput);
	     
		return foodInsert;
	}
	
	/**
	 * inserts a new item into the food list, with specific macros
	 */
	public static void insertFood() {
		Scanner myObj = new Scanner(System.in);
		System.out.println("What is the name of the new food Element you want to insert?");
		FoodElement newElement = new FoodElement(myObj.nextLine());
		System.out.println("What is the amount of protein, as a double");
		newElement.setProtein(myObj.nextInt());
		System.out.println("What is the amount of carbs, as a double");
		newElement.setCarbs(myObj.nextInt());
		System.out.println("What is the amount of fat, as a double");
		newElement.setFat(myObj.nextInt());
		//setting the correct calorie count
		newElement.setCalories((double)(newElement.getProtein()*4)+(newElement.getFat()*9)+(newElement.getCarbs()*4));
		foodArray.add(newElement);
		System.out.println();
	}
	
	/**
	 * makes a string of all elements in the food list
	 * @return the string of all elements in the food list 
	 */
	private static String printFoodList() {
		String foodList = "";
		for(int i = 0; i< foodArray.size(); i++) {
			FoodElement element = foodArray.get(i);
			foodList += element.getName() +": ";
			foodList += "Protein: " + element.getProtein();
			foodList += "  Carbs: " + element.getCarbs();
			foodList += "  Fat: " + element.getFat();
			foodList += "  Calories: " + element.getCalories();
			foodList += "\n";
		}
		return foodList;
	}
	
	/**
	 * loads basic food data from csv file 
	 */
	public static void loadData() {
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
					food.setCalories((double)(food.getProtein()*4)+(food.getFat()*9)+(food.getCarbs()*4));
					foodArray.add(food);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
