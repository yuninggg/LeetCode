Problem:

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, 
such that the container contains the most water.

------------------------------------------------------------------------------------------------------
Solution: Beats 95%

首先讲最大值设为最边上两条线，之后只有两种情况值得考虑，左边新的线大于左边现有的线或者右边新的线大于右边现有的线，
这样才有可能超过原来的最大值，


public class Solution {
    public int maxArea(int[] height) {
        int max = 0, area;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int l = height[left];
            int r = height[right];
            if (l < r) {
                area = (right - left) * l;
                while (height[++left] < l);
            } else {
                area = (right - left) * r;
                while (height[--right] < r);
            }
            if (area > max) {
                max = area;
            }
        }
        return max;
    }
}