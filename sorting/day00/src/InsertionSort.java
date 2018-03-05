
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime:O(n^2)
     * Average-case runtime:O(n^2)
     *
     * Space-complexity:O(1)
     */
    @Override
    public int[] sort(int[] array) {
        int length = array.length;
        int counter;
        int current;
        for(int i=1; i<length; i++){
            current = array[i];
            counter = i-1;

            while(counter>=0 && array[counter] > current){
                array[counter+1] = array[counter];
                counter = counter-1;
            }
            array[counter+1] = current;
        }
        return array;
    }
}
