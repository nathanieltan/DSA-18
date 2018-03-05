import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        HashMap<Integer,Integer> myMap = new HashMap<>();
        int[] sum = new int[nums.length];
        int max = 0;
        int endIndex = 0;
        if(nums[0]==0){
            sum[0] = -1;
        }
        else{
            sum[0] = 1;
        }

        for(int i = 1; i < sum.length; i++){
            if(nums[i]==0){
                sum[i] = sum[i-1]-1;
            }
            else{
                sum[i] = sum[i-1]+1;
            }
        }

        for(int i = 0; i < sum.length; i++) {
            if(sum[i] == 0){
                if(max < i+1){
                    max = i+1;
                    endIndex = i;
                }
            }
            if(myMap.containsKey(sum[i])){
                if(max < i-myMap.get(sum[i])){
                    max = i-myMap.get(sum[i]);
                    endIndex = i;
                }
            }
            else{
                myMap.put(sum[i], i );
            }
        }
        System.out.print(endIndex + " " + max + ": ");
        for(int i = 0; i < sum.length; i++){
            System.out.print(sum[i]+", ");
        }
        System.out.println();
        return new int[]{endIndex-max+1, endIndex};
    }
}
