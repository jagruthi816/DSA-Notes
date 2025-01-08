/* Method 1
  Using recursion 
  Time complexity : O(2^n) This is because each call branches into two more calls, leading to an exponential growth in the number of calls.
  Space complexity : O(n) Recursion stack takes n stack space */

import java.util.*;

class Solution{
    //Function to count total ways to reach nth stair
    public static int climbStairs(int n){
        //Base case
        if(n == 0) return 1;
        if(n == 1) return 1;
        
        //Taking 1 step at a time
        int oneStep = climbStairs(n-1);
        
        //Taking 2 steps at a time
        int twoSteps = climbStairs(n-2);
        
        //Return total ways
        return oneStep+twoSteps;
    }
	public static void main (String[] args){
        int n = 3;
        
        //Create an instance of Solution class
        Solution sol = new Solution();
        
        //Print the answer
        System.out.println("The total number of ways: "+sol.climbStairs(n));
	}
}
