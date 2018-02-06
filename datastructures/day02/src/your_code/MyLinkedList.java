package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size == 0) {
            Node newNode = new Node(c,null,null);
            head = newNode;
            tail = newNode;
        }
        else{
            Node newNode = new Node(c, tail, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        if (size == 0) {
            Node newNode = new Node(c, null, null);
            head = newNode;
            tail = newNode;
        }
        else {
            Node newNode = new Node(c, null, head);
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public Chicken get(int index) {
        if (index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        int counter = 0;
        Node currentNode = head;
        while (counter != index) {
            currentNode = currentNode.next;
            counter++;
        }
        return currentNode.val;
    }

    public Chicken remove(int index) {
        if (index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if (index == 0 ) return removeFirst();
        if (index == size - 1) return removeLast();
        int counter = 0;
        Node currentNode = head;
        while (counter != index) {
            currentNode = currentNode.next;
            counter++;
        }
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        size--;
        return currentNode.val;
    }

    public Chicken removeFirst() {
        if (size == 0){
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        Chicken toReturn = head.val;
        if (size == 1){
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return toReturn;
    }

    public Chicken removeLast() {
        if (size == 0){
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        Chicken toReturn = tail.val;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return toReturn;
    }
}