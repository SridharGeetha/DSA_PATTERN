package DSA_PATTERN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

    // <<<<<<<<<<<<<<<< Fixed Size >>>>>>>>>>>>>>>>>

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

     public int numOfSubarrays(int[] arr, int k, int threshold) {
     int sum = 0;
     int cnt = 0;
     int n = arr.length;
     for(int i=0;i<k;i++)   {
        sum += arr[i];
     }
     int left = 0;
     int right = k-1;
     while(right<n){
        if(sum/k >= threshold) cnt++;
        right++;
        if(right<n){
        sum += arr[right];
        }
        sum -= arr[left++];
     }
     return cnt;
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

    public static List<Integer> firstNegInt(int arr[], int k) {
        Queue<Integer> q = new LinkedList<>();
        int ind = 0;
        int n = arr.length;
        while(ind <k && ind <n){
            if(arr[ind]<0){
                q.add(arr[ind]);
            }
            ind++;
        }
        int left = 1;
        int right = k;
        List<Integer> ans = new ArrayList<>();
        ans.add(q.isEmpty() ? 0 : q.peek());
        while(right < n){
            if(arr[left-1]<0) q.poll();
            if(arr[right]<0) q.add(arr[right]);
            ans.add(q.isEmpty() ? 0 : q.peek());
            left++;
            right++;
        }
        return ans;
    }


    //Need to solve Count Number of Nice Subarrays.
    //Max Consecutive Ones III
    //Count of Anagrams 

    
    public static int longestSubarray(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int left = 0;
        int right = 0;
        int zero = 0;
        while(right<n){
            if(nums[right] == 0) zero++;
            if(zero > 1){
                if(nums[left] == 0) zero--;
                left++;
            }
            int val = zero == 0 ? right-left+1 : right - left;
            if(zero<=1){
                cnt = Math.max(cnt,val);
            }
            right++;
        }
        return cnt == n ? cnt-1 : cnt;
    }

     public static int longestSubarray_Clean_Code(int[] nums) {
        int leftIndex = 0;
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            
            if (nums[i] == 0) zeroCount++;
            
            if (zeroCount > 1) {
                if (nums[leftIndex] == 0) zeroCount--;
                leftIndex++;
            }
        }
        
        return nums.length - 1 - leftIndex;
    }
    //Longest Substring Without Repeating Characters
    //K-length Substrings with No Repeats

}