public class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        long mask = 0;
        char[] arr = s.toCharArray();
        for (char i : arr) {
           mask = mask ^ (1 << (i - 'a'));
           System.out.println(i - ' ');
        }
        if (arr.length % 2 == 0) {
            return mask == 0;
        } else {
            // Brian Kernighan’s Algorithm:
            // Subtraction of 1 from a number toggles all the bits (from right to left) till the rightmost set bit(including the righmost set bit). So if we subtract a number by 1 and do bitwise & with itself (n & (n-1)), we unset the righmost set bit. If we do n & (n-1) in a loop and count the no of times loop executes we get the set bit count.
            // Beauty of the this solution is number of times it loops is equal to the number of set bits in a given integer.
            int count = 0;
            while (mask != 0) {
                mask = mask & (mask - 1);
                count++;
            }
            return count <= 1;
        }
    }
}