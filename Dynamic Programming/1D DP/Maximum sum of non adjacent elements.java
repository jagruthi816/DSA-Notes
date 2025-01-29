/*Given an integer array nums of size n. Return the maximum sum possible using the elements of nums such that no two elements taken are adjacent in nums.
A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.

Input: nums = [2, 1, 4, 9]
Output: 11
*/

/*Method 1
  Recursion
  Time Complexity: O(2^N) - Each call branches into 2 more calls
  Space Complexity: O(N) - Recursion stack space
*/

import java.util.*;

class Solution {
    // Function to solve the problem using recursion
    private int func(int ind, int[] arr) {
        // Base cases
        if (ind == 0)
            return arr[ind];
        if (ind < 0)
            return 0;

        // Choosing the current element
        int pick = arr[ind] + func(ind - 2, arr);

        // Skipping the current element
        int nonPick = 0 + func(ind - 1, arr);

        // Return the maximum
        return Math.max(pick, nonPick);
    }

    /* Function to calculate the maximum
    sum of non-adjacent elements*/
    public int nonAdjacent(int[] nums) {
        int ind = nums.length - 1;

        // Return the maximum sum
        return func(ind, nums);
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 9};

        // Create an instance of Solution class
        Solution sol = new Solution();

        // Call the nonAdjacent function and print the result
        System.out.println(sol.nonAdjacent(arr));
    }
}

/* Method 2 
  Memoization
  Time Complexity: O(N)
  Space Complexity: O(N) + O(N)
  */

class Solution {
    public int nonAdjacent(int[] nums) {
        int ind = nums.length - 1;
        int[] ans = new int[ind+1];
        Arrays.fill(ans, -1);
        return maxSum(ind, nums, ans);
    }

    private int maxSum(int ind, int[] nums, int[] ans){
        if(ind == 0){
            return nums[ind];
        }
        if(ind < 0){
            return 0;
        }
        if(ans[ind] != -1){
            return ans[ind];
        }
        int pick = nums[ind] + maxSum(ind-2, nums, ans);
        int notPick = 0 + maxSum(ind - 1, nums, ans);
        ans[ind] = Math.max(pick, notPick);
        return ans[ind];
    }
}

/* Method 3
  Tabulation
  Time Complexity : O(N)
  Space Complexity: O(N)
  */

class Solution {
    public int nonAdjacent(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = nums[0];
        for(int i = 1; i < n; i++){
            int pick = nums[i]; 
            if(i > 1)
            pick = pick + ans[i - 2];
            int notPick = 0 + ans[i-1];
            ans[i] = Math.max(pick, notPick);
        }
        return ans[n - 1];
    }
}

/*Method 4
  Space Optimization
  Time Complexity: O(N)
  Space Complexity: O(1)
  */

class Solution {
    public int nonAdjacent(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int prev = nums[0];
        int prev2 = 0;
        for(int i = 1; i < n; i++){
            int pick = nums[i]; 
            if(i > 1)
            pick = pick + prev2;
            int notPick = 0 + prev;
            int curr = Math.max(pick, notPick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
}
