package DSA_PATTERN;

import java.util.Arrays;
import java.util.PriorityQueue;

//The sliding window technique is used when you're dealing with 
//contiguous subarrays or substrings
//you want to optimize a brute-force solution that checks every possible window.

// The problem involves:
// Type	Example
// Fixed-size window	Max sum of subarray of size k
// Variable-size window	Longest substring with at most k distinct chars
// Minimizing/maximizing condition	Minimum window substring covering all characters
// Count of specific condition	Count of subarrays with sum < k, vowels, etc
class SlidingWindow{

     public int minimumDifference(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0;
        int right = k-1;
        while(right<n){
            min = Math.min(min,nums[right]-nums[left]);
            left++;
            right++;
        }
        return min;
    }

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

    public static double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for(int i=0;i<k;i++){
            sum+=nums[i];
        }
        double ans = (double)sum/k;
        for(int i=k;i<n;i++){
            sum = sum - nums[i-k]+nums[i];
            ans = Math.max(ans,(double)sum/k);
        }
        return ans;
    }

    public int maxVowels(String s, int k) {
        int maxCount = 0, currCount = 0;
        int n = s.length();
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currCount++;
            }
        }
        maxCount = currCount;
        for (int i = k; i < n; i++) {
            if (isVowel(s.charAt(i))) {
                currCount++;
            }
            if (isVowel(s.charAt(i - k))) {
                currCount--;
            }
            maxCount = Math.max(maxCount, currCount);
            if (maxCount == k) return k;
        }
        return maxCount;
    }
    private boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    //Advanced Sliding window
     public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        int left = 1;
        int right = k;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[0]-a[0]);
        for(int i=0;i<k;i++){
            pq.add(new int[]{nums[i],i});
        }
        ans[0]=(pq.peek()[0]);
        while (right < n) {
           pq.add(new int[]{nums[right],right});
           while(pq.peek()[1]<left){
            pq.poll();
           }
           ans[left]=(pq.peek()[0]);
           left++;
           right++;
        }
        
        return ans;
    }

    //Need to solve Count Number of Nice Subarrays.
    //Max Consecutive Ones III
    //Longest Substring Without Repeating Characters
    //K-length Substrings with No Repeats

}