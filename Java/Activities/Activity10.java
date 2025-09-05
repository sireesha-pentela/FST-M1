package Activities;
import java.util.HashSet;
import java.util. Set;

public class Activity10 {
	public static void main(String[] args) {
		Set<Integer> numSet = new HashSet<>();
		numSet.add(100);
		numSet.add(200);
		numSet.add(300);
		numSet.add(400);
		numSet.add(500);
		numSet.add(600);
		for(int num : numSet) {
			System.out.println(num); 
		}
		System.out.println("Size of the set: " + numSet.size());
		numSet.remove(100);
		System.out.println("Size of the set: " + numSet.size());
		System.out.println(numSet);
		System.out.println("Search for 100? " + numSet.contains(100));
	}

}
