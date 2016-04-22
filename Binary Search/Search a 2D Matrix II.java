
Leetcode Version: https://leetcode.com/problems/search-a-2d-matrix-ii/


Solution One:

We start search the matrix from top right corner, initialize the current position to top right corner, 
if the target is greater than the value in current position, 
	then the target can not be in entire row of current position because the row is sorted, 
if the target is less than the value in current position, 
	then the target can not in the entire column because the column is sorted too. 
We can rule out one row or one column each time, so the time complexity is O(m+n).

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
Complexity Analysis: O（m+n)





Solution Two: 用了Arrays.binarySearch

public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0)
        return false;
    for (int[] row : matrix) {
        if (Arrays.binarySearch(row, target) >= 0) {
            return true;
        }
    }
    return false;
}




Solution Three: Divide and Conquer
https://leetcode.com/discuss/77842/java-an-easy-to-understand-divide-and-conquer-method


public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    if(m<1) return false;
    int n = matrix[0].length;

    return searchMatrix(matrix, new int[]{0,0}, new int[]{m-1, n-1}, target);
}

private boolean searchMatrix(int[][] matrix, int[] upperLeft, int[] lowerRight, int target) {
    if(upperLeft[0] > lowerRight[0] || upperLeft[1]>lowerRight[1]
            || lowerRight[0]>=matrix.length || lowerRight[1]>=matrix[0].length) 
        return false;
    if(lowerRight[0]-upperLeft[0]==0 && lowerRight[1]-upperLeft[1]==0)
        return matrix[upperLeft[0]][upperLeft[1]] == target;
    int rowMid = (upperLeft[0] + lowerRight[0]) >> 1;
    int colMid = (upperLeft[1] + lowerRight[1]) >> 1;
    int diff = matrix[rowMid][colMid] - target;
    if(diff > 0) {
        return searchMatrix(matrix, upperLeft, new int[]{rowMid, colMid}, target)
                || searchMatrix(matrix, new int[]{upperLeft[0],colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
                || searchMatrix(matrix, new int[]{rowMid+1,upperLeft[1]}, new int[]{lowerRight[0], colMid}, target);
    }
    else if(diff < 0) {
        return searchMatrix(matrix, new int[]{upperLeft[0], colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
                || searchMatrix(matrix, new int[]{rowMid+1, upperLeft[1]}, new int[]{lowerRight[0], colMid}, target)
                || searchMatrix(matrix, new int[]{rowMid+1, colMid+1}, lowerRight, target);
    }
    else return true;
}
