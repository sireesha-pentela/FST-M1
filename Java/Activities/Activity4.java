package Activities;

public class Activity4 {
	public static void insertionSort(int[] array) {
        
        for (int i = 1; i < array.length; i++) {
            int x = array[i]; 
            int j = i - 1;

            
            while (j >= 0 && array[j] > x) {
                array[j + 1] = array[j];
                j--;
            }

            
            array[j + 1] = x;
        }
    }

    public static void main(String[] args) {
        int[] data = {29, 10, 14, 37, 13};

        System.out.println("Before sorting:");
        printArray(data);

        insertionSort(data);

        System.out.println("After sorting:");
        printArray(data);
    }

    
    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
