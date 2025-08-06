package DSA_PATTERN;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // String s1 = "ab";
        // String s2 = "eidaooo";
        // System.out.println(SlidingWindow.checkPermutaion(s1, s2));
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
        List<int[]> ans = TwoPointer.intervalIntersection(firstList,secondList);
        // System.out.println(ans);
        // for(int[] num : ans){
        //     System.out.print("["+num[0]+" "+num[1]+"] ");
        // }
        int x = -1;
        System.out.println(1-x);
    }

    
}

//[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
