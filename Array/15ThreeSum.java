Problem:

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)


------------------------------------------------------------------------------------------------------
额外的要求是不能返回重复的triplets，返回的a,b,c的顺序要是非递减的。

解法一：首先想一下，三个数相加，要为0的话，如果不是都为0，那么至少有一个正数一个负数。
可以从这一点出发，设置两个指针i和j，分别指向数组S的首尾，always保持numbers[i] <= 0 and numbers[j]>0。
哦，对了，数组要先给它排序。然后判断numbers[i] + numbers[j]的符号，如果是大于0，我们就去数组负数部分search，
反之去正数部分找。因为数组是sorted，search部分可以用binarySearch。代码如下:


public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if(nums == null || nums.length < 3) return result;
    Arrays.sort(nums);

    int len = nums.length;
    for(int i = 0; i < len; i++) {
        if(i > 0 && nums[i] == nums[i - 1]) continue;        // Skip same results
        int target = 0 - nums[i];
        int j = i + 1, k = len - 1;
        while(j < k) {
            if(nums[j] + nums[k] == target) {
                result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                while(j < k && nums[j] == nums[j + 1]) j++;  // Skip same results
                while(j < k && nums[k] == nums[k - 1]) k--;  // Skip same results
                j++; k--;
            } else if(nums[j] + nums[k] < target) {
                j++;
            } else {
                k--;
            }
        }
    }
    return result;
}