/* Problem description : Given an integer array height[] where height[i] represents the height of the i-th stair, a frog starts from the first stair and wants to reach the top. 
From any stair i, the frog has two options: it can either jump to the (i+1)-th stair or the (i+2)-th stair. 
The cost of a jump is the absolute difference in height between the two stairs. 
The task is to find the minimum total cost required for the frog to reach the top. */

/* Recursion Approach
  1. Define the problem in terms of indices
  2. Do all stuffs on every index
  3. Return count or min or mix of all the possible cases */

/* Method 1 
  Recursion */
class Solution {
    public int frogJump(int[] heights, int ind) {
        // Base case: if the frog is at the first step
        if (ind == 0) return 0;
        
        // Recursively calculate the energy required to 
        // jump to the current step from the previous step
        int jumpOne = frogJump(heights, ind - 1) + Math.abs(heights[ind] - heights[ind - 1]);
        
        // Initialize jumpTwo to a large value
        int jumpTwo = Integer.MAX_VALUE;
        
        // If possible, recursively calculate the energy required to 
        // jump to the current step from two steps back
        if (ind > 1) {
            jumpTwo = frogJump(heights, ind - 2) + Math.abs(heights[ind] - heights[ind - 2]);
        }
        
        // Return the minimum energy required to reach the current step
        return Math.min(jumpOne, jumpTwo);
    }

    public int frogJump(int[] heights) {
        int n = heights.length;
        return frogJump(heights, n - 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] heights = {2, 1, 3, 5, 4};
        int result = sol.frogJump(heights);
        System.out.println("Minimum energy required: " + result);
    }
}

/* Method 2 
   Memoization
  */
class Solution {
    public int frogJump(int[] heights) {
       int n = heights.length;
       int dp[] = new int[n];
       Arrays.fill(dp, -1);
        return frogJump(heights, n - 1, dp); 
    }
    public int frogJump(int[] heights, int ind, int[] dp) {
        // Base case: if the frog is at the first step
        if (ind == 0) return 0;
        
        if(dp[ind] != -1){
            return dp[ind];
        }
        // Recursively calculate the energy required to 
        // jump to the current step from the previous step
        int jumpOne = frogJump(heights, ind - 1, dp) + Math.abs(heights[ind] - heights[ind - 1]);
        
        // Initialize jumpTwo to a large value
        int jumpTwo = Integer.MAX_VALUE;
        
        // If possible, recursively calculate the energy required to 
        // jump to the current step from two steps back
        if (ind > 1) {
            jumpTwo = frogJump(heights, ind - 2, dp) + Math.abs(heights[ind] - heights[ind - 2]);
        }
        
        // Return the minimum energy required to reach the current step
        dp[ind] = Math.min(jumpOne, jumpTwo);
        return dp[ind];
    }
}

/* Method 3 
  Tabulation */
class Solution {
    public int frogJump(int[] heights) {
        int n = heights.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        // Base case
        dp[0] = 0; 

        // Iterative calculation
        for (int ind = 1; ind < n; ind++) {
            int jumpOne = dp[ind - 1] + Math.abs(heights[ind] - heights[ind - 1]);
            int jumpTwo = Integer.MAX_VALUE;
            if (ind > 1)
            // Store minimum energy for this stair
                jumpTwo = dp[ind - 2] + Math.abs(heights[ind] - heights[ind - 2]);
            dp[ind] = Math.min(jumpOne, jumpTwo); 
        }
        // Return the minimum energy required to reach the last stair
        return dp[n - 1]; 
    }

  /* Method 4 
     Space Optimazation */
  public int frogJump(int[] heights) {
        int n = heights.length;
        int prev = 0, prev2 = 0; // Initialize for base cases

        // Iterative calculation
        for (int i = 1; i < n; i++) {
            int jumpOne = prev + Math.abs(heights[i] - heights[i - 1]);
            int jumpTwo = Integer.MAX_VALUE;
            if (i > 1)
                jumpTwo = prev2 + Math.abs(heights[i] - heights[i - 2]);
                
            // Minimum energy for current stair
            int cur_i = Math.min(jumpOne, jumpTwo); 
            // Update previous values
            prev2 = prev; 
            prev = cur_i;
        }
        // Return the minimum energy required to reach the last stair
        return prev; 
    }
