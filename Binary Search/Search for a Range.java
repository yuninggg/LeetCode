Problem: Search for a Range 

/*

Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).


If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].


Solution One: My Solution

*/
分析：如果先找到最左边再用while找到右边界的话，有可能会造成O(n)的时间复杂度，所以还是用两个binary search 比较好

	public int[] searchRange(int[] nums, int target) {
        //corner case
        int[] result = {-1,-1};
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return result;
        }
        // find the first target location
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target <= nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] != target && nums[end] != target) { //每写完一行代码就对一下。。。
            return result;
        }
        if (nums[start] == target) {
            result[0] = start;
        } else {
            result[0] = end;
        }
        start = result[0];
        while (start < nums.length && nums[start] == target) {
            start++;
        }
        result[1] = start - 1;
        return result;
    }

Solution Two:
思路：用两个binary search 分别确定两个边界，时间复杂度是O(logn)

 	public int[] searchRange(int[] nums, int target) {
        //corner case
        int[] result = {-1,-1};
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return result;
        }
        // find the first target location
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target <= nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] != target && nums[end] != target) { //每写完一行代码就对一下。。。
            return result;
        }
        if (nums[start] == target) {
            result[0] = start;
        } else {
            result[0] = end;
        }
        start = result[0];
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target >= nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            result[1] = end;
        } else {
            result[1] = start;
        }
        return result;
    }