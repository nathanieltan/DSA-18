package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> TrackList;

    public MyStack() {
        ll = new LinkedList<>();
        TrackList = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        if (TrackList.size() == 0){
            TrackList.addFirst(e);
        }
        else {
            if (e > TrackList.getFirst()) {
                TrackList.addFirst(e);
            }
            else {
                TrackList.addFirst(TrackList.getFirst());
            }
        }
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        TrackList.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return TrackList.getFirst();
    }
}
