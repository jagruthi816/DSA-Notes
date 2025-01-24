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

  
