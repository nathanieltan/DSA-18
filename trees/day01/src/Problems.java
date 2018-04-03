import java.util.Arrays;

public class Problems {

    public static int leastSum(int[] A) {
        Arrays.sort(A);

        int one = 0;
        int two = 0;
        boolean alternate = true;

        for(int i = 0; i < A.length; i++){
            if(alternate){
                one = one*10 + A[i];
            }
            else{
                two = two*10 + A[i];
            }
            alternate = !alternate;
        }
        return one + two;
    }
}
