Problem:



---------------------------------------------------------------------------------------------
Solution One: Moore`s voting algorithm



public class Solution {
    public int majorityElement(int[] nums) {
        int length = nums.length;
        int count = 0;
        int candidate = nums[0];
        for(int i=0;i<length;i++){
            if(count == 0){
                candidate = nums[i];
                count++;
            }
            else if(nums[i] == candidate){
                count++;
            }
            else count--;
        }
        return candidate;
    }
}
---------------------------------------------------------------------------------------------
Solution Two: Sorting

public int majorityElement1(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length/2];
}

---------------------------------------------------------------------------------------------
Solution Three: Hashtable


public int majorityElement2(int[] nums) {
    Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
    //Hashtable<Integer, Integer> myMap = new Hashtable<Integer, Integer>();
    int ret=0;
    for (int num: nums) {
        if (!myMap.containsKey(num))
            myMap.put(num, 1);
        else
            myMap.put(num, myMap.get(num)+1);
        if (myMap.get(num)>nums.length/2) {
            ret = num;
            break;
        }
    }
    return ret;
}

---------------------------------------------------------------------------------------------

Solution 4: Bit manipulation 
public int majorityElement(int[] nums) {
    int[] bit = new int[32];
    for (int num: nums) {
        for (int i=0; i<32; i++) {
            if ((num>>(31-i) & 1) == 1) {
                bit[i]++;
            }
        }
    }
        
    int ret=0;
    for (int i=0; i<32; i++) {
        bit[i]=bit[i]>nums.length/2?1:0;
        ret += bit[i]*(1<<(31-i));
    }
    return ret;
}