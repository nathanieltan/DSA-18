public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(N)
    public void add(Cow c) {
        if (size==elems.length) {
            Cow[] replacementElems = new Cow[elems.length*2];
            System.arraycopy(elems, 0, replacementElems, 0, size);
            elems = replacementElems;
        }
        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index >= size){
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }
        return elems[index];
    }

    // TODO: Runtime: O(N)
    public Cow remove(int index) {
        if (size<elems.length/4){
            int newCap = elems.length/2;
            Cow[] replacementElems = new Cow[newCap];
            System.arraycopy(elems, 0, replacementElems, 0, newCap);
            elems = replacementElems;
        }

        if (index >= size){
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }

        Cow removed = get(index);
        index++;

        while (index < size) {
            elems[index-1] = elems[index];
            index = index + 1;
        }

        size--;
        return removed;
    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        if (size==elems.length) {
            Cow[] replacementElems = new Cow[elems.length*2];
            System.arraycopy(elems, 0, replacementElems, 0, size);
            elems = replacementElems;
        }

        if (index >= size){
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        }
        int counter = size;
        while (counter > index){
            elems[counter] = elems[counter-1];
            counter--;
        }
        elems[index] = c;
        size++;
    }
}
