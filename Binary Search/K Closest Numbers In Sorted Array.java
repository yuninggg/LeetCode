题干： Given a target number, a non-negative integer k and an integer array A sorted in ascending order, 
find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. 
Otherwise, sorted in ascending order by number if the difference is same.
Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].
Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].


Link: http://www.lintcode.com/en/problem/k-closest-numbers-in-sorted-array/#


Solution One: My Solution
public class Solution {
    /**
     * @param A an integer array
     * @param target an integer
     * @param k a non-negative integer
     * @return an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // Corner Case: !!!! k == 0
        if (A == null || A.length < k || k == 0) {
            return new int[0];
        }
        int start = 0;
        int end = A.length - 1;     // 注意:数组.length - 1才是数组的index!!!!!
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target < A[mid]) {
                end = mid;
            } else if (target > A[mid]) {
                start = mid;
            } else {
                start = mid;
                break;
            }
        }
        //find the closest number of the target
        int center = 0;
        if (Math.abs(A[start] - target) <= Math.abs(A[end] - target)) {
            center = start;
        } else {
            center = end;
        }
        int[] rst = new int[k];
        rst[0] = A[center];
        // loop through the k smaller and k larger
        int count = 1;
        start = center - 1;
        end = center + 1;
        while (count < k) {
            if (start >= 0 && end <= A.length - 1) {
                if (Math.abs(A[start] - target) <= Math.abs(A[end] - target)) {
                    rst[count++] = A[start--];
                } else {
                    rst[count++] = A[end++];
                }
            } else {
                break;
            }
        }
        if (start >= 0) {
            while (count < k) {
                rst[count++] = A[start--];
            }
        } else if (end < A.length) {
            while (count < k) {
                rst[count++] = A[end++];
            }
        }
        return rst;
    }
}




Solution Two: From jiuzhang.com

	public int[] kClosestNumbers(int[] A, int target, int k) {
        int[] result = new int[k];
        
        if (A == null || A.length == 0) {
            return A;
        }
        if (k > A.length) {
            return A;
        }
        
        int index = firstIndex(A, target);
        
        int start = index - 1;
        int end = index;
        for (int i = 0; i < k; i++) {
            if (start < 0) {
                result[i] = A[end++];
            } else if (end >= A.length) {
                result[i] = A[start--];
            } else {
                if (target - A[start] <= A[end] - target) {
                    result[i] = A[start--];
                } else {
                    result[i] = A[end++];
                }
            }
        }
        return result;
    }
    
    private int firstIndex(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < target) {
                start = mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                end = mid;
            }
        }
        
        if (A[start] >= target) {
            return start;
        }
        if (A[end] >= target) {
            return end;
        }
        return A.length;
    }