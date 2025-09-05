package Activities;

public class Activity1 {
	
	public static void main(String[] args) {
		Car toyota = new Car();
		toyota.make = 2014;
		toyota.color = "Black";
		toyota.transmission = "manual";
		
		toyota.displayCharacterstics();
		toyota.accelerate();
		toyota.brake();
	}

}
