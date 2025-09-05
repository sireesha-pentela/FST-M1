package Activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Activity13 {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> intList = new ArrayList<>();
        Random rand = new Random();
        
        System.out.print("Enter the number to add to the list:");
 
        while(scan.hasNextInt()) {
            intList.add(scan.nextInt());
        }
        
        System.out.println("List of numbers: " + intList);
        
        scan.close();
        
        int genIndex = rand.nextInt(intList.size()-1);
        System.out.println("Generated index: " + genIndex);
        
        System.out.println("Value at index: " + intList.get(genIndex));
 
       
    }

}
