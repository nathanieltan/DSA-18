public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int leftVal;
        int rightVal;
        int currVal;
        boolean goOn = false;

        if(i<this.size) {
            if(left<this.size){
                if(this.heap[left]>this.heap[i]) {
                    goOn = true;
                }
            }
            if(right<this.size) {
                if (this.heap[right] > this.heap[i]) {
                    goOn = true;
                }
            }
        }

        while(goOn){
            if(this.heap[left]>this.heap[i]){
                leftVal = this.heap[left];
                currVal = this.heap[i];
                this.heap[left] = currVal;
                this.heap[i] = leftVal;
                i = left;
            }
            else if(this.heap[right]>this.heap[i]){
                rightVal = this.heap[right];
                currVal = this.heap[i];
                this.heap[right] = currVal;
                this.heap[i] = rightVal;
                i = right;
            }

            left = leftChild(i);
            right = rightChild(i);

            goOn = false;
            if(i<this.size-1) {
                if(left<this.size){
                    if(this.heap[left]>this.heap[i]) goOn = true;
                }
                if(right<this.size){
                    if(this.heap[right]>this.heap[i]) goOn = true;
                }
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(Nlog(N))
     * Worst-case runtime: O(Nlog(N))
     * Average-case runtime: O(Nlog(N))
     *
     * Space-complexity: O(N)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            int[] newArray = new int[i+1];
            System.arraycopy(array,0,newArray,0,i);
            heapify(newArray);
            array[i] = newArray[0];
        }

        return array;
    }
}
