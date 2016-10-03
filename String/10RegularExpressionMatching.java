Problem:

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

-------------------------------------------------------------------------
My Solution:

public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        } else if (s == null || p == null) {
            return false;
        }
        int n1 = s.length();
        int n2 = p.length();
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        // init dp array
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                // base case
                // System.out.println("i: " + i + ", j: " + j);
                if (i == 0 && j > 1 && p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    continue;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                } else if (j == 0 || i == 0) {
                    dp[i][j] = false;
                    continue;
                }
                //
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                        if (j > 1) {
                            if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')  { //attention
                                dp[i][j] = dp[i - 1][j];// changed
                            } 
                            dp[i][j] |= dp[i][j - 2];
                        } else {
                            dp[i][j] = true;
                        }
                    } else {
                        dp[i][j] = false;
                    }
                }
                
            }
        }
        return dp[n1][n2];
    }
}

//分析： 这题是大dp套用小dp， 并且需要注意indexing的越界问题处理。

-------------------------------------------------------------------------






