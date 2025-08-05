package TWO_POINTER;

import java.util.Arrays;

class SlidingWindow{
    public static boolean checkPermutaion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if(n>m) return false;
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for(int i =0;i<n;i++){
            count1[s1.charAt(i)-'a']++;
            count2[s2.charAt(i)-'a']++;
        }
        int left = 0;
        int right = n-1;
        while (right <m) {
            if(Arrays.equals(count1, count2)){
                return true;
            }
            right++;
            if(right<m){
                count2[s2.charAt(right)-'a']++;
                count2[s2.charAt(left)-'a']--;
                left++;
            }
        }
        return false;
    }
}