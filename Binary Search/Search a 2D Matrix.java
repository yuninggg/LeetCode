Problem: Search a 2D Matrix

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.



My Solution:	两次二分法
 	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length ==0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[row - 1][col - 1]) { //先判断是否在这个矩阵里，
            return false;
        }
        //查找在哪行，要考虑在最后一行的特殊情况，这样start和end就一直指向<target的值
        int end = row - 1;
        int r = 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target > matrix[mid][0] && target < matrix[mid][col - 1]) {
                r = mid;
                break;
            }
            if (target < matrix[mid][0]) {
                end = mid;
            } else if (target > matrix[mid][0]) {
                start = mid;
            } else {
                return true;
            }
        }
        if (matrix[start][0] == target || matrix[end][0] == target) {
            return true;
        } 
        if (start + 1 >= end) {		// 要考虑target出现在最后一行的情况！！！
            if (matrix[end][0] < target) {
            r = end;
            } else {
        	r = start;
            }
        }
        start = 0;
        end = col - 1;
         while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target < matrix[r][mid]) {
                end = mid;
            } else if (target > matrix[r][mid]) {
                start = mid;
            } else {
                return true;
            }
        }
        if (matrix[r][start] == target || matrix[r][end] == target) {
            return true;
        } 
        return false;
    }


Solution 2: 把这个矩阵当成一个巨大的数组，而不是矩阵。


	public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int row = matrix.length;
            int col = matrix[0].length;
            if (target < matrix[0][0] || target > matrix[row - 1][col - 1]) {
                return false;
            }
            int start = 0;
            int end = row * col - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (target < matrix[mid / col][mid % col]) {
                    end = mid;
                } else if (target > matrix[mid / col][mid % col]) {
                    start = mid;
                } else {
                    return true;
                }
            }
            if (target == matrix[start / col][start % col] || target == matrix[end / col][end % col]) {
                return true;
            }
            return false;
        }