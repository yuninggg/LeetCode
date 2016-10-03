Problem:

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.


---------------------------------------------------------------------------------------------




public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            Integer curr = map.get(nums[i]); // 这里用Integer就能接收null
            if (curr == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], curr + 1);
            }
        }
        for (Map.Entry<Integer, Integer> kv : map.entrySet()) { //注意如何iterate through hashmap
            // Map.Entry<k,v>是一个类型
            if(kv.getValue() > nums.length / 3) {
                result.add(kv.getKey());
            }
        }
        return result;
    }
    
}