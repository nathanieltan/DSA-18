 public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        quickSort(locks,keys,0,locks.length-1);
        result[0] = locks;
        result[1] = keys;
        for(char ch : locks){
            System.out.print(ch+" ");
        }
        System.out.println();
        for(char ch : keys){
            System.out.print(ch+" ");
        }
        System.out.println();
        return result;
    }

    private static void quickSort(char[] locks, char[] keys, int low, int high){
        if(low < high){
            int pivot = partition(locks, low, high, keys[high]);

            partition(keys, low, high, locks[pivot]);

            quickSort(locks, keys, low, pivot-1);
            quickSort(locks, keys, pivot+1, high);
        }
    }

    private static int partition(char[] A, int low, int high, char pivot){
        int i = low;

        for(int j = low; j < high; j++){
            if(A[j] < pivot){
                swap(A,i,j);
                i++;
            }
            else if(A[j] == pivot){
                swap(A,j,high);
                j--;
            }
        }
        swap(A,i,high);
        return i;
    }
}




