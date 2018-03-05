import java.util.Arrays;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        Arrays.sort(arr);
        int second, third;
        int sumCount = 0;

        for(int i = 0; i < arr.length - 2; i++){
            second = i+1;
            third = arr.length - 1;
            while(second < third){
                if(arr[i] + arr[second] + arr[third] == sum){
                    sumCount++;
                    second++;
                }
                else if(arr[i] + arr[second] + arr[third] < sum){
                    second++;
                }
                else{
                    third--;
                }
            }
        }
        return sumCount;
    }
}
