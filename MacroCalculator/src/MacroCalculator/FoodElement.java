package MacroCalculator;

public class FoodElement {
	private String name;
	private double protein;
	private double carbs;
	private double fat;
	private double calories; 
	private double percent;
	
	public FoodElement(String name) {
		this.name = name;
		protein = 0;
		carbs = 0;
		fat = 0;
		calories = 0;
		percent = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
	
}
