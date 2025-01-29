/*A frog wants to climb a staircase with n steps. Given an integer array heights, where heights[i] contains the height of the ith step, and an integer k.
To jump from the ith step to the jth step, the frog requires abs(heights[i] - heights[j]) energy, where abs() denotes the absolute difference. 
The frog can jump from the ith step to any step in the range [i + 1, i + k], provided it exists.
Return the minimum amount of energy required by the frog to go from the 0th step to the (n-1)th step.
*/

/*Method 1 
Recursion
Time Complexity: O(k^N)
Space Complexity: O(N)
*/

import java.util.*;

class Solution {
    //Helper function for recursion
    private int func(int ind, int[] heights, int k) {
        //Base case
        if (ind == 0) {
            return 0;
        }
        //Initialize mmStep to INT_MAX
        int mmStep = Integer.MAX_VALUE;
        
        //Try all possible steps
        for (int j = 1; j <= k; j++) {
            
            //Check if ind-j is non negative
            if (ind - j >= 0) {
                int jump = func(ind - j, heights, k) + Math.abs(heights[ind] - heights[ind - j]);
                
                //Update the mimimum energy
                mmStep = Math.min(jump, mmStep);
            }
        }
        //Return the answer
        return mmStep;
    }
    /* Function to find the mimimum 
    energy to reach last stair*/
    public int frogJump(int[] heights, int k) {
        int ind = heights.length - 1;
        
        //Return the mimimum energy
        return func(ind, heights, k);
    }
    
    public static void main(String[] args) {
        int[] heights = {15, 4, 1, 14, 15};
        int k = 3;
        
        // Create an instance of Solution class
        Solution sol = new Solution();
        
        // Print the answer
        System.out.println("Minimum energy: " + sol.frogJump(heights, k));
    }
}

/*Method 2 
Using Memoization
Time Complexity: O(K * N)
Space Complexity: O(N) + O(N)
*/

class Solution {
    public int frogJump(int[] heights, int k) {
        int ind = heights.length - 1;
        
        /* Initialize a memoization array
        to store calculated results*/
        int[] dp = new int[ind + 1];
        Arrays.fill(dp, -1);
        
        // Return the minimum energy
        return func(ind, heights, k, dp);
    }
    private int func(int ind, int[] heights, int k, int[] dp) {
        // Base case
        if (ind == 0) {
            return 0;
        }
        
        /* If the result for this index has been 
        previously calculated, return it*/
        if (dp[ind] != -1) {
            return dp[ind];
        }
        
        // Initialize mmStep to Integer.MAX_VALUE
        int mmStep = Integer.MAX_VALUE;
        
        // Try all possible jumps
        for (int j = 1; j <= k; j++) {
            
            // Check if ind - j is non-negative
            if (ind - j >= 0) {
                int jump = func(ind - j, heights, k, dp) + Math.abs(heights[ind] - heights[ind - j]);
                
                //Update the minimum energy
                mmStep = Math.min(jump, mmStep);
            }
        }
        
        // Store the result in dp array and return
        dp[ind] = mmStep;
        return dp[ind];
    }
}

/*Method 3 
Tabulation
Time complexity: O(N*K)
Space complexity: O(N)
*/

public int frogJump(int[] heights, int k) {
        int ind = heights.length;
        
        /* Initialize a memoization array
        to store calculated results*/
        int[] dp = new int[ind + 1];
        Arrays.fill(dp, -1);
        
        dp[0] = 0; 
        
        // Loop through the array
        for (int i = 1; i < ind; i++) {
            int mmSteps = Integer.MAX_VALUE;
            
            // Loop to try all possible jumps from 1 to k
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                    
                    //Update the minimum energy
                    mmSteps = Math.min(jump, mmSteps);
                }
            }
            
            dp[i] = mmSteps;
        }
        
        // Result is stored in the last element of dp
        return dp[ind - 1];
    }
