Problem: Minimum Size Subarray Sum


Solution One: Using Two Pointer

/* 

思路： 我们需要定义两个指针left和right，分别记录子数组的左右的边界位置，然后我们让right向右移，
直到子数组和大于等于给定值或者right达到数组末尾，此时我们更新最短距离，并且将left像右移一位，
然后再sum中减去移去的值，然后重复上面的步骤，直到right到达末尾，且left到达临界位置，即要么到达边界，要么再往右移动，和就会小于给定值。
时间复杂度： O(n)

*/

    public int minSubArrayLen(int s, int[] nums) {
        //corner case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //
        int result = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (left < nums.length) {
            while (sum < s && right < nums.length) {
                sum += nums[right];
                right++;
            }
            if (sum < s) {
                break;
            }
            int length = right - left;
            result = length < result ? length : result;
            sum -= nums[left];
            left++;
        }
        return result == Integer.MAX_VALUE ? 0: result;
    }

Solution Two: 

Another solution is by using binary search algorithm, we initial another array with the length to be nums.length + 1, 
and sum[i] is equal to the sum from nums[0] to num[i - 1], when meet an element, 
for example sum[i] is larger or equal to s, 
then find the largest element that smaller than sum[i] - target + 1 by using binary search.
If find, the index j to index i - 1 is the subarray. length = i - j


public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        //corner case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //
        int[] sum = new int[nums.length + 1];
        int min = nums.length + 1;
        sum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
            if (sum[i + 1] >= s) {
                int left = binarySearch(sum[i + 1] - s, sum, 0, i + 1);
                min = Math.min(min, i - left + 1);
            }
        }
        return min == nums.length + 1 ? 0 : min;
    }
    // Find the largest element's index that <= target
    private int binarySearch(int target, int[] nums, int start, int end){
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target < nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] <= target) {
            return end;
        } else {
            return start;
        }
    }
}