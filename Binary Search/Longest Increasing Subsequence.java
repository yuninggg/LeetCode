Problem: Longest Increasing Subsequence




Solution One:  My Solution
时间复杂度分析： O(n^2)， 一定要分清dp状态函数代表什么

    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] lIS = new int[n];//LIS[i]代表以nums[i]结尾的最长递增子序列长度 - 1;
        for (int i = 0; i < n; i++) {
            int best = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] >= nums[j]) {
                    best = Math.max(best, lIS[j] + 1) ;
                }
            }
            lIS[i] = best;
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(lIS[i], max);
        }
        return max + 1;
    }
    //注意： 要准确判断出结果是什么！！不是最后一个就是最优解



    同一个思路，另一个版本的解法：

    public int lengthOfLIS(int[] nums) {
        // corner case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 
        int[] dp = new int[nums.length]; // dp[n]代表nums[0] - nums[n] 的LIS长度
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int best = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    best = Math.max(best, dp[j] + 1);
                }
            }
            dp[i] = best;
        }
        int result = 0;
        for (int i : dp) {
            result = Math.max(result, i);
        }
        return result;
    }

Solution Two:
分析： 时间复杂度 O(NlogN)

public class Solution {
    public int lengthOfLIS(int[] nums) {
        // corner case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[n] 储存nums[0]到nums[n]最长子序列长度
        int[] dp = new int[nums.length];
        int[] lastNum = new int[nums.length]; // lastNum[n] 代表长度为n + 1的最长子序列最后一个数最小可以是多少
        lastNum[0] = nums[0];
        dp[0] = 1;
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            lastNum[i] = Integer.MAX_VALUE;
            if (nums[i] > lastNum[result]) {
                result++;
                lastNum[result] = nums[i];
            } else {
                int loc = binarySearchHelper(lastNum, 0, i, nums[i]);
                lastNum[loc + 1] = nums[i] < lastNum[loc + 1] ? nums[i] : lastNum[loc + 1];
            }
        }
        return  result + 1;
    }
    // 找比target小的最大的数
    private int binarySearchHelper(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target <= nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] < target) {
            return end;
        }
        if (nums[start] < target) {
            return start;
        }
        return -1;
    }
}