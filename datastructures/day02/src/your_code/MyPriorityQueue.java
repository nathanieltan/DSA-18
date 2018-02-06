package your_code;

import java.util.ArrayList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private ArrayList<Integer> al = new ArrayList();

    public void enqueue(int item) {
        if (al.size() == 0){
            al.add(item);
        }
        else {
            int counter = 0;
            while (item < al.get(counter) && counter < al.size()){
                counter++;
            }
            al.add(counter, item);
        }
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        if (al.size() == 0){
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return al.remove(0);
    }

}