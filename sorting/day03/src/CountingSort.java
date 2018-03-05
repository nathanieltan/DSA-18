public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = A[0];


        // determines max element
        for(int i = 1; i < A.length; i++){
            if(k<A[i]){
                k = A[i];
            }
        }
        k++;
        int[] counter = new int[k];

        for(int i = 0; i < A.length; i++){
            counter[A[i]]++;
        }

        int index = 0;
        for(int i = 0; i < k; i++){
            for(int j = 0; j < counter[i]; j++){
                A[index] = i;
                index++;
            }
        }
    }

}
