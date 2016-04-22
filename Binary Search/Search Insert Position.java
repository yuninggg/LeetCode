Problem: Search Insert Position
Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0


Solution One: My Solution
public int searchInsert(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (target < nums[mid]) {
            end = mid;
        } else if (target > nums[mid]){
            start = mid;
        } else {
            return mid;
        }
    }
    //跳出之后不要考虑start是否等于end！！！！！！！
    //此种二分法跳出循环的时候start和end不一样
    if (target <= nums[start]) {
        return start;
    } else if (target > nums[end]) {
        return end + 1;
    } else {
        return end;
    }
}