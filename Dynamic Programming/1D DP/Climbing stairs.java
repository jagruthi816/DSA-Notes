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

/* Method 2
  Using Memoization 
  Time complexity : O(n) This is because we perform a linear number of operations relative to the input size n. 
  Space complexity : O(n) + O(n) We are using a recursion stack space O(N) and an array (again O(N)). Therefore total space complexity will be O(N) + O(N) ≈ O(N). */

/* Logic 
  Memoization tends to store the value of subproblems in some map/ table. As, here we have only one parameter in recursive function, so we can create an 1D array to store the values of subproblems.
Declare an Array dp[] of Size n+1: Here, n is the parameter or size of the problem, as the highest number of subproblems can be n+1(including 0 as well). It represents the solution to the subproblem for any given index. Initially, fill the array with -1, to indicate that no subproblem has been solved yet.
While encountering an overlapping subproblem: When we come across a subproblem, for which the dp array has value other than -1, it means we have found a subproblem which has been solved before hence it is an overlapping subproblem. No need to calculate it's value again just retrieve the value from dp array and return it.
Store the value of subproblem: Whenever, a subproblem is enocountered and it is not solved yet(the value at this index will be -1 in the dp array), store the calculated value of subproblem in the array. */

class Solution {
    public int climbStairs(int n) {
      int dp[] = new int[n+1];
      Arrays.fill(dp, -1);
      return func(n, dp);
    }
    private int func(int n, int[] dp){
        if(n <= 1){
            return 1;
        }
        
        if(dp[n] != -1){
            return dp[n];
        }

        dp[n] = func(n-1, dp) + func(n-2, dp);
        return dp[n];
    }
}
