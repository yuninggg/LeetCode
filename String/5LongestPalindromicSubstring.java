Problem:

Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, 
and there exists one unique longest palindromic substring.

------------------------------------------------------------------------------------------------------
Solution One: Dynamic Programming

To improve over the brute force solution, we first observe how we can avoid unnecessary re-computation 
while validating palindromes. Consider the case "ababa". If we already knew that "bab" is a palindrome, 
it is obvious that "ababa" must be a palindrome since the two left and right end letters are the same.
We define P(i,j) as following:
P(i,j)={true, if the substring Si…Sj is a palindrome
		false,	otherwise.
Therefore, P(i,j) = (P( i+ 1,j - 1) and Si == Sj)
The base cases are P(i,i) = true and P(i,i + 1) = (Si == Si+1)
This yields a straight forward DP solution, which we first initialize the one and two letters palindromes, and work our way up finding all three letters palindromes, and so on...

Complexity Analysis
Time complexity : O(n2)
Space complexity : O(n2)
​​It uses O(n2) space to store the table.


public class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int[] result = new int[2];
        result[0] = n - 1;
        result[1] = n - 1;
        
        boolean[][] LPS = new boolean[n][n];
        for (int i = 0; i < n - 1; i++) {
            LPS[i][i] = true;
            LPS[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (LPS[i][i + 1]) {
                result[0] = i;
                result[1] = i + 1;
            }
        }
        LPS[n - 1][n - 1] = true;
        for (int j = 2; j < n; j ++) {
            for (int i = 0; i < n - j; i++) {
                LPS[i][i + j] = LPS[i + 1][i + j - 1] && (s.charAt(i) == s.charAt(i + j));
                if (LPS[i][i + j]) {
                    result[0] = i;
                    result[1] = i + j;
                }
            }
        }
        return s.substring(result[0], result[1] + 1);
    }
}

------------------------------------------------------------------------------------------------------
Solution Three: Expand Around Center

In fact, we could solve it in O(n2) time using only constant space.

We observe that a palindrome mirrors around its center. 
Therefore, a palindrome can be expanded from its center, and there are only 2n−1 such centers.

You might be asking why there are 2n−1 but not n centers? 
The reason is the center of a palindrome can be in between two letters. 
Such palindromes have even number of letters (such as ''abba'') 
and its center are between the two 'b's.



public String longestPalindrome(String s) {
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}

