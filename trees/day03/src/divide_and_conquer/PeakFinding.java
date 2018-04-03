package divide_and_conquer;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {

        return findOneDPeakHelper(nums, 0, nums.length, nums.length);
    }

    private static int findOneDPeakHelper(int[] nums, int lo, int hi, int n){
        int mid = lo + (hi-lo)/2;

        if(peakOneD(mid, nums) == 0){
            return mid;
        }

        else if(peakOneD(mid,nums) == -1){
            return findOneDPeakHelper(nums, lo, (mid-1), n);
        }

        else{
            return findOneDPeakHelper(nums, (mid+1), hi, n);
        }
    }

    private static int[] findTwoDPeakHelper(int[][] nums, int xlo, int ylo, int xhi, int yhi){
        int xmid = xlo + (xhi-xlo)/2;
        int ymid = ylo + (yhi-ylo)/2;

        int maxY = maxYIndex(xmid,ylo,yhi,nums);
        int maxX;

        int peakX = peakX(xmid,maxY, nums);
        int peakY;

        if(xlo == xhi && ylo == yhi){
            return new int[]{ylo,xlo};
        }

        if(peakX == 0){
            return new int[]{maxY,xmid};
        }
        else if(peakX == -1){
            maxX = maxXIndex(ymid,xlo,xmid,nums);
            peakY = peakY(maxX,ymid,nums);
            if(peakY == 0){
                return new int[]{ymid,maxX};
            }
            else if(peakY == 1){
                return findTwoDPeakHelper(nums, xlo, ymid+1, xmid,yhi);
            }
            else{
                return findTwoDPeakHelper(nums, xlo, ylo, xmid, ymid);
            }
        }
        else{
            maxX = maxXIndex(ymid, xmid+1, xhi, nums);
            peakY = peakY(maxX, ymid, nums);
            if(peakY == 0){
                return new int[]{ymid,maxX};
            }
            else if(peakY == 1){
                return findTwoDPeakHelper(nums,xmid+1,ymid+1,xhi,yhi);
            }
            else{
                return findTwoDPeakHelper(nums,xmid+1,ylo,xhi,ymid);
            }
        }
    }
    public static int[] findTwoDPeak(int[][] nums) {
        return findTwoDPeakHelper(nums, 0, 0, nums.length, nums.length);
    }

}
