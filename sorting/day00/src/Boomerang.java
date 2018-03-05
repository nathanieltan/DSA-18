import java.util.HashMap;
import java.util.Collection;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        HashMap distanceMap = new HashMap();
        int boomerangCount = 0;
        double distance;
        for(int i = 0; i < points.length; i++){
            distanceMap.clear();
            for(int j = 0; j < points.length; j++){
                if(j != i){
                    distance = Math.sqrt((Math.pow(points[j][0]-points[i][0],2)) + (Math.pow(points[j][1] - points[i][1],2)));
                    if(distanceMap.containsKey(distance)){
                        distanceMap.replace(distance,(int)distanceMap.get(distance)+1);
                    }
                    else{
                        distanceMap.put(distance,1);
                    }
                }
            }
            Object[] distanceCounts = distanceMap.values().toArray();
            for(int x = 0; x < distanceCounts.length; x++){
                boomerangCount = boomerangCount + ((int)distanceCounts[x]*((int)distanceCounts[x]-1));
            }
        }
        return boomerangCount;
    }
}

