
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(nlog(n))
     * Worst-case runtime: O(nlog(n))
     * Average-case runtime: O(nlog(n))
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        if(array.length > 1) {
            int middle = array.length/ 2;
            int[] sub1 = new int[middle];
            int[] sub2 = new int[array.length - middle];

            for (int i = 0; i < middle; i++) {
                sub1[i] = array[i];
            }
            for (int j = 0; j < (array.length - middle); j++) {
                sub2[j] = array[j + middle];
            }

            sub1 = sort(sub1);
            sub2 = sort(sub2);

            array = merge(sub1,sub2);
        }
        return array;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] toReturn = new int[a.length+b.length];
        while(i < a.length && j < b.length){
            if(a[i] <= b[j]){
                toReturn[k] = a[i];
                i++;
            }
            else{
                toReturn[k] = b[j];
                j++;
            }
            k++;
        }

        while(i < a.length){
            toReturn[k] = a[i];
            i++;
            k++;
        }

        while(j < b.length){
            toReturn[k] = b[j];
            j++;
            k++;
        }
        return toReturn;
    }

}
