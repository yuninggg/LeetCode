
Problem:

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

------------------------------------------------------------------------------------
My Solution: Beats 44.75%

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (nums == null || nums.length < 2) {
            return result;
        }
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = hash.get(target - nums[i]);
            } else {
                hash.put(nums[i], i);
            }
        }
        return result;
    }
    
}

------------------------------------------------------------------------------------
Solution Two: Beats 89%

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> numPos = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length ; i++) {
            Integer position = numPos.get(target-nums[i]);
            if(position == null) {
                numPos.put(nums[i],i);
            } else {
                return new int[]{ i, position };
            }
        }
        return null;
    }
}

Explanation: 之所以比我的解法好，是因为 containsKey 的功能被get功能取缔了。 
				当不contains的时候， get返回null

------------------------------------------------------------------------------------
