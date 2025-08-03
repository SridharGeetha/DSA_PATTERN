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

     public static int findPairs(int[] arr, int k) {
        int cnt = 0;
        Arrays.sort(arr);
        int n = arr.length;
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

}

