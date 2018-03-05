import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        // TODO
        List<Point> mySkyline = new ArrayList<Point>();
        if(B.length == 1){
            mySkyline.add(new Point(B[0].l,B[0].h));
            mySkyline.add(new Point(B[0].r,0));

            return mySkyline;
        }

        int mid = (B.length/2);

        List<Point> left = new ArrayList<>();
        List<Point> right = new ArrayList<>();

        Building[] sub1 = new Building[mid];
        Building[] sub2 = new Building[B.length - mid];

        for(int i = 0; i < mid; i++){
            sub1[i] = B[i];
        }
        for(int i = 0; i < (B.length - mid); i++){
            sub2[i] = B[i + mid];
        }

        left = skyline(sub1);
        right = skyline(sub2);

        mySkyline = mergeSkyline(left, right);

        return mySkyline;
    }

    public static List<Point> mergeSkyline(List<Point> skyline1, List<Point> skyline2){
        ArrayList<Point> merged = new ArrayList<Point>();

        // heights of the two skylines
        int h1 = 0, h2 = 0;

        while(!skyline1.isEmpty() && !skyline2.isEmpty()){
            if(skyline1.get(0).x < skyline2.get(0).x){
                Point current = skyline1.remove(0);
                int x = current.x;
                h1 = current.y;

                append(merged,new Point(x,Math.max(h1,h2)));
            }
            else{
                Point current = skyline2.remove(0);
                int x = current.x;
                h2 = current.y;
                append(merged,new Point(x,Math.max(h1,h2)));
            }
        }

        //empties remaining points in skylines
        while(!skyline1.isEmpty()){
            append(merged,skyline1.remove(0));
        }

        while(!skyline2.isEmpty()){
            append(merged,skyline2.remove(0));
        }

        return merged;
    }

    public static void append(ArrayList<Point> skyline, Point toAppend){
        if(!skyline.isEmpty()){
            if(toAppend.y == skyline.get(skyline.size()-1).y){
                return;
            }
            if(toAppend.x == skyline.get(skyline.size()-1).x){
                skyline.get(skyline.size()-1).y = Math.max(skyline.get(skyline.size()-1).y,toAppend.y);
                return;
            }
        }
        skyline.add(toAppend);
    }
}
