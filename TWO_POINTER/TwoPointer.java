package TWO_POINTER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TwoPointer{

    public int[] twoSum(int[] arr, int target)// find the target sum in given array 
    {
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            int sum = arr[left]+arr[right];
            if(sum == target){
                return new int[]{left+1,right+1};
            }
            if(sum<target){
                left++;
            }
            else{
                right--;
            }
        }
        return new int[]{-1,-1};
    }

    public List<List<Integer>> threeSum(int[] nums) 
    {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for(int i=0;i<n;i++){
            if(i!=0 && nums[i]==nums[i-1])
            continue;
            int j = i+1;
            int k = n-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum<0){
                    j++;
                }
                else if(sum>0){
                    k--;
                }else{
                    List<Integer> temp = Arrays.asList(nums[i],nums[j],nums[k]);
                    ans.add(temp);
                    j++;
                    k--;
                    while(j<k && nums[j] == nums[j-1])
                    j++;
                    while(j<k && nums[k]==nums[k+1])
                    k--;
                }
            }
        }
        return ans;
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = arr.length-1;
        while(right-left>=k){
            if(Math.abs(arr[left]-x)>Math.abs(arr[right]-x)){
                left++;
            }else{
                right--;
            }
        }
        for(int i=left;i<=right;i++){
            ans.add(arr[i]);
        }
        return ans;
    }

    public static int triangleNumber(int[] nums) {
        int cnt  = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for(int k = n-1;k>=2;k--){
            int i =0;
            int j = k-1;
            while(i<j){
                if(nums[i]+nums[j]>nums[k]){
                    cnt += j-i;
                    j--;
                }else{
                    i++;
                }
            }
        }
        return cnt;
    }

    public static int numRescueBoats(int[] arr, int k) {
        Arrays.sort(arr);
        int n= arr.length;
        int left = 0;
        int right = n-1;
        int cnt = 0;
        while(left <= right){
            if(arr[left]+arr[right]<=k){
                left++;
            }
            right--;
            cnt++;
        }
        return cnt;
    }



    //remove given element using two pointer
    public static int removeElement(int[] nums, int val) {
        int k =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != val){
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    //remove duplicate and same pattern like above
    public static int removeDuplicates_I(int[] arr) {
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }
    
    public int removeDuplicates_II(int[] nums) {
        int i = 2;
        for(int j = 2;j<nums.length;j++){
            if(nums[j]!=nums[i-2]){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
     // Brute force Sort Array
    // Better Count 0s 1s 2s and fill it in array
    //Dutch National Flag Algorithm 
    public static int[] sort012(int[] arr){
        int low = 0;
        int mid = 0;
        int high = arr.length -1;
        while (mid<=high) {
            if(arr[mid] == 0){
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            }else if(arr[mid] == 1){
                mid++;
            }else{
                int temp = arr[high];
                arr[high] = arr[mid];
                arr[mid] = temp;
                high--;
            }
        }
        return arr;
    }




    // two Array
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;
        while(i>=0 && j>=0){
            if(nums1[i]>nums2[j]){
                nums1[k--] = nums1[i--];
            }else{
                nums1[k--] = nums2[j--];
            }
        }
        while(j>=0){
            nums1[k--] = nums2[j--];
        }
    }

     //two array
    public static int kthElement(int a[], int b[], int k) {
        int ans = -1;
        int cnt = 0;
        int n = a.length;
        int m = b.length;
        int i = 0;
        int j = 0;
        while(i<n && j<m){
            if(a[i]>b[j]){
                if(cnt == k-1){
                    ans = b[j];
                }
                j++;
            }else{
                if(cnt == k-1){
                    ans=a[i];
                }
                i++;
            }
            cnt++;
        }
        while(i<n){
             if(cnt == k-1){
                    ans=a[i];
                }
                i++;
                cnt++;
        }
        while(j<m){
             if(cnt == k-1){
                    ans = b[j];
                }
                j++;
                cnt++;
        }
        return ans;
    }

    //prefix using map
    public static int countSubarrays(int[] arr, int k) {
        int cnt  =0;
        int n = arr.length;
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i=0;i<n;i++){
            sum+=arr[i];
            
            int prefixSum = sum - k;
            
            if(map.containsKey(prefixSum)){
                cnt += map.get(prefixSum);
            }
            
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return cnt;
    }

    //same model like above
    public int maxOperations(int[] nums, int k) {
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for(int num : nums){
            int val = k-num;
            if(map.getOrDefault(val,0)>0){
                count++;
                map.put(val,map.get(val)-1);
            }else{
                map.put(num,map.getOrDefault(num,0)+1);
            }
        }
        return count;
    }

    // In which we are using map which take space that why SC goes O(N)
    public static int findPairs_Better(int[] arr, int k) {
        int cnt = 0;
        Arrays.sort(arr);
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : arr){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for(int num : map.keySet()){
           if(k == 0) {
           if(map.get(num)>1) cnt++;
           }
           else{
            if(map.containsKey(num+k)){
                cnt++;
            }
           }
        }
        return cnt;
    }
    // used two pointer and extra space reduce to O(1)
    public static int findPairs_Optimize(int[] A, int k) {
        int n = A.length;
        Arrays.sort(A); 
        int i = 0, j = 1, count = 0;
        while (j < n) {
            int diff = A[j] - A[i];
            if (i == j || diff < k) {
                j++;
            } else if (diff > k) {
                i++;
            } else {
                count++;
                int prevI = A[i];
                int prevJ = A[j];
                while (i < n && A[i] == prevI) i++;
                while (j < n && A[j] == prevJ) j++;
            }
        }
        return count;
    }
   




    // <<<<<Histogram>>>>> type
    //using prefix max and suffix max val TC O(3N) and SC O(2N)  
    public static int trapingRainWater_Better(int[] arr) {
        int n = arr.length;
        int suffix[] = new int[n];
        suffix[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], arr[i]);
        }
        int waterTrapped = 0;
        int prefixMax = arr[0];
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, arr[i]);
            waterTrapped += Math.min(prefixMax, suffix[i]) - arr[i];
        }
        return waterTrapped;
    }
    //using two pointer TC O(N) SC and O(1) -- Hard Level
    public static int trapingRainWater_Optimize(int[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n-1;
        int maxLeft = 0;
        int maxRight = 0;
        int ans = 0;
        while(left <= right){
            if(arr[left]<=arr[right]){
                if(arr[left]>maxLeft){
                    maxLeft = arr[left];
                }else{
                    ans += maxLeft - arr[left];
                }
                left++;
            }else{
                if(arr[right]>maxRight){
                    maxRight = arr[right];
                }else{
                    ans += maxRight - arr[right];
                }
                right--;
            }
        }
        return ans;
    }
    // similiar pattern like tarpping rain water
    public static int maxArea(int[] arr) {
        int i =0;
        int j = arr.length-1;
        int maxi = 0;
        while(i<j){
            int min = Math.min(arr[i],arr[j]);
            int wid = j-i;
            maxi = Math.max(maxi,wid*min);
            if(arr[i]<arr[j])i++;
            else j--;
        }
        return maxi;
    }





    //prefix and two pointer SC O(N) due to prefix sum 
    //it can be optimized using Sliding window Technique
    public static int maximumSumSubarray(int[] arr, int l) {
        int n = arr.length;
        int i =0;
        int j = l-1;
        int[] prefix = new int[n+1];
        prefix[0] = 0;
        for(int k=0;k<n;k++){
            prefix[k+1] = prefix[k]+arr[k];
        }
        int max = Integer.MIN_VALUE;
        while(j<n){
            max = Math.max(max,(prefix[j+1]-prefix[i]));
            i++;
            j++;
        }
        return max;
    }
    //find max vowels in given length substring among all
    // it can also optimize using sliding window
    public static int maxVowels(String s, int k) {
        int cnt = 0;
        int n = s.length();
        int[] prefix = new int[n+1];
        prefix[0] = 0;
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                prefix[i+1] = 1 + prefix[i];
            }else{
                prefix[i+1] = prefix[i]; 
            }
        }
        int l = 0;
        int j = k-1;
        while(j<n){
            cnt = Math.max(cnt,prefix[j+1]-prefix[l]);
            l++;
            j++;
        }
        return cnt;
        }

        //same pattern like above.
    static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
    public static List<Integer> vowelCountsInKSubstrings(String s, int k) {
        int n = s.length();
        int[] prefix = new int[n + 1];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (isVowel(s.charAt(i)) ? 1 : 0);
        }
        for (int i = 0; i <= n - k; i++) {
            result.add(prefix[i + k] - prefix[i]);
        }
        return result;
    }
 

    // Two pointer and related to merge intervels
    public static List<int[]> intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();
        int i=0;
        int j=0;
        while(i<firstList.length && j<secondList.length){
            int start = Math.max(firstList[i][0],secondList[j][0]);
            int end = Math.min(firstList[i][1],secondList[j][1]);
            if(start<=end) ans.add(new int[]{start,end});

            if(firstList[i][1]<secondList[j][1]){
                i++;
            }else{
                j++;
            }
        }
        return ans;
    }

    
    public static String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for(String word : dictionary){
            if(isSubsequence(s,word)){
                if((word.length()>ans.length())||(word.length() == ans.length() && word.compareTo(ans)<0)){
                    ans = word;
                }
            }
        }
        return ans;
    }
    static boolean isSubsequence(String s,String word){
        int i = 0;
        int j =0;
        while(i<s.length() && j<word.length()){
            if(s.charAt(i) == word.charAt(j)){
                j++;
            }
            i++;
        }
        return j == word.length();
    } 
}

