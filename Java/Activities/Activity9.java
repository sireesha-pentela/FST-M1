package Activities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Activity9 {
	 public static void main(String[] args) {
	       
	        List<String> myList = new ArrayList<>();
	        
	        myList.add("Apple");
	        myList.add("Mango");
	        myList.add("Orange");
	        
	        myList.add(3, "Grapes");
	        myList.add(1, "Papaya");
	        
	        System.out.println("Print All the Objects:");
	        for(String s:myList){
	            System.out.println(s);
	        }
	        
	        System.out.println("3rd element in the list is: " + myList.get(2));
	        System.out.println("Is Chicku is in list: " + myList.contains("Chicku"));
	        System.out.println("Size of ArrayList: " + myList.size());
	        
	        myList.remove("Papaya");
	        
	        System.out.println("New Size of ArrayList: " + myList.size());
	    }

}
