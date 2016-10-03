Problem: Longest Substring Without Repeating Characters



Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

------------------------------------------------------------------------------------------
My Solution: Beats 2.31%


public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i ++) {
            Integer loc = map.get(s.charAt(i));
            if(loc != null) {
                maxLength = Math.max(maxLength, map.size());
                i = loc;
                map.clear();
            } else {
                map.put(s.charAt(i), i);
            }
        }
        maxLength = Math.max(maxLength, map.size());
        return maxLength;
    }
}

改良版：击败7%

public int lengthOfLongestSubstring(String s) {
    if (s.length() == 0 || s == null) {
        return 0;
    }
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    int lengthofLS = 0;
    int i,j;
    for (i = 0, j = 0; i < s.length(); i ++) {
        Integer lookupChar = map.get(s.charAt(i));
        if (lookupChar == null) {
            map.put(s.charAt(i), i);
        } else {
            lengthofLS = Math.max(lengthofLS, i - j);
            j = lookupChar + 1;
            i = j;
            map.clear();
            map.put(s.charAt(i), i);
        }
    }
    if (i == s.length()) {
        lengthofLS = Math.max(lengthofLS, i - j);
    }
    return lengthofLS;
}

------------------------------------------------------------------------------------------
Solution Two: Optimized Sliding Window method with HashMap

A sliding window is an abstract concept commonly used in array/string problems. 
A window is a range of elements in the array/string which usually defined by the start and end indices, 
i.e. [i,j) (left-closed, right-open). 

A sliding window is a window "slides" its two boundaries to the certain direction. 
For example, if we slide [i,j) to the right by 1 element, then it becomes [i+1, j+1)

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}


------------------------------------------------------------------------------------------
Solution Three: Optimized Sliding Window method without HashMap


The previous implements all have no assumption on the charset of the string s.

If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.

Commonly used tables are:

int[26] for Letters 'a' - 'z' or 'A' - 'Z'
int[128] for ASCII
int[256] for Extended ASCII




public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}