package Activities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;;
 
public class Activity11 {
	public static void main(String[] args) {
	//	Create a Map named colours with integer keys and String values.
		Map<Integer, String> colours = new HashMap<>();
		
	//	Add 5 random colours to it and print the Map to the console.
		colours.put(600,"Blue");
		colours.put(300,"Pink");
		colours.put(500,"Red");
		colours.put(100,"White");
		colours.put(200,"Black");
	// Print map value to the console
		System.out.println(colours);
		for (Entry<Integer, String> item:colours.entrySet()) {
			System.out.println(item.getKey() + " :" + item.getValue());
		}
	//	Remove one colour using the remove() method.
		colours.remove(100);
		
	//	Check if the colour green exists in the Map using the containsValue() method.
		System.out.println("Does Green Exists? "+ colours.containsValue("Green"));
	//	Print the size of the Map using the size() method.
		System.out.println("size of the Map " + colours.size());
	}
 
}
