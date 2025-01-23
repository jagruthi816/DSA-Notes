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

/* Method 3
   Bottom-Up Approach in Tabulation: Tabulation involves solving a problem by building a solution from the bottom up. This means start with the smallest subproblems and iteratively compute solutions for larger subproblems until the desired solution has been found.
   Setting Base Cases in the Array:In the recursive code, we knew the answer for base cases, similarly if we are computing in tabulation, we definitely know that the answer for dp[0] = 1 and dp[1] = 1.
   Iterative Computation Using a Loop: Set an iterative loop that traverses the array( from index 2 to n). To compute the solution for larger values, use a loop that iterates from the smallest subproblem up to n. The current value(dp[i]) represents the subproblem and by the recurrence relation it is obvious that it is sum of previous two values, for every index(i) set its value as dp[i-1] + dp[i-2].
   
   Time Complexity: O(N), where N is the given number of stairs. This is because we perform a linear number of operations relative to the input size N.
   Space Complexity:O(N), an additional array dp of size n + 1 is used to store intermediate results. Hence, the space complexity is O(N).
*/

import java.util.*;

class Solution {
    // Function to count total ways to reach nth stair
    public int climbStairs(int n) {
        // Declare dp array of size n+1
        int[] dp = new int[n + 1];

        // Insert the values of base conditions
        dp[0] = 1;
        dp[1] = 1;

        // Iterate and update every index of dp array
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return the answer
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 3;

        // Create an instance of Solution class
        Solution sol = new Solution();

        // Print the answer
        System.out.println("The total number of ways: " + sol.climbStairs(n));
    }
}

/* Method 4
   Space Optimization
   If we observe the relation dp[i] = dp[i-1] + dp[i-2], we notice that to calculate the current value at index i, we only need the previous two values. Using an entire array for just two values seems overhead. Instead, the work can be done using two variables to store the previous two values, achieving O(1) space complexity. In each iteration, the current value and the variables containing previous values can be updated.
   In each iteration, cur_i is calculated and prev and prev2 are updated to become the next iteration's prev and prev2 respectively.
   After the iterative loop has ended, prev is returned as the answer.
   
   Time Complexity: O(N), where N is the given number of stairs. This is because we perform a linear number of operations relative to the input size n.
   Space Complexity:O(1). As no extra space is being used here.
*/

import java.util.*;

class Solution {
    // Function to count total ways to reach nth stair
    public int climbStairs(int n) {
        /*Initialize two variables to 
        store previous results*/
        int prev2 = 1;
        int prev = 1;
        
        //Iterate and update the variables
        for (int i = 2; i <= n; i++) {
            int cur_i = prev2 + prev;
            prev2 = prev;
            prev = cur_i;
        }
        //Return the answer as prev
        return prev;
    }

    public static void main(String[] args) {
        int n = 3;

        // Create an instance of Solution class
        Solution sol = new Solution();

        // Print the answer
        System.out.println("The total number of ways: " + sol.climbStairs(n));
    }
}
