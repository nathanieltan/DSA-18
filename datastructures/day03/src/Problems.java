import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // TODO: your code here
        // For now, return a List that's correct size, but contains only 0s
        List<Integer> l = new LinkedList<>();
        Stack<Integer> myStack = new Stack<>();
        for (int i = 0; i < A.length; i++){
            while(!myStack.empty() && A[i]<myStack.peek() && (myStack.size() + (A.length - i))>A.length-k){
                myStack.pop();
            }
            if(myStack.size() < A.length-k){
                myStack.push(A[i]);
            }
        }
        for (int i = 0; i < A.length - k; i++) l.add(0,myStack.pop());
        return l;
    }

    public static boolean isPalindrome(Node n) {
        Node moveOne = n;
        Node moveTwo = n;
        Node prevMoveOne = n;
        Node midNode = null;

        if(n == null || n.next == null){
            return true;
        }

        while(moveTwo != null && moveTwo.next != null){
            moveTwo = moveTwo.next.next;
            prevMoveOne = moveOne;
            moveOne = moveOne.next;
        }
        if(moveTwo != null){
            midNode = moveOne;
            moveOne = moveOne.next;
        }

        Node secondHalf = moveOne;
        prevMoveOne.next = null;

        //Reverses second half of the node chain
        Node prev = null;
        Node current = secondHalf;
        Node next;
        while (current != null)
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        secondHalf = prev;

        while(n != null && secondHalf != null){
            if (n.val == secondHalf.val){
                n = n.next;
                secondHalf = secondHalf.next;
            }
            else{
                return false;
            }
        }

        if(n == null && secondHalf == null){
            return true;
        }
        return false;
    }

    public static String infixToPostfix(String s) {
        String toReturn = "";
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i<s.length(); i++){
            char curChar = s.charAt(i);

            if (Character.isDigit(curChar)){
                toReturn += curChar;
                toReturn += ' ';
            }
            else if (curChar == '('){
                operators.push(curChar);
            }
            else if (curChar == ')'){
                while (!operators.isEmpty() && operators.peek() != '('){
                    toReturn += operators.pop();
                    toReturn += ' ';
                }

                operators.pop();
            }
            else if (curChar == ' '){}
            else{
                operators.push(curChar);
            }
        }

        while(!operators.isEmpty()){
            toReturn += operators.pop();
            toReturn += ' ';
        }
        toReturn = toReturn.substring(0,toReturn.length()-1);
        return toReturn;
    }

}
