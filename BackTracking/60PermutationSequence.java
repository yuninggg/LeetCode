class Solution {
	public static void main(String[] args) {
		System.out.println(getPermutation(3,1));		
	}
	public static String getPermutation(int n, int k) {
		int[] factorial = new int[n];
		calFactorial(factorial);
		return helper(new char[n], new boolean[n], k, 0, factorial);
	}
	private static String helper(char[] curr, boolean[] used, int k, int index, int[] factorial) {
		if (index == curr.length) {
			return new String(curr);
		}
		System.out.println(curr.length);
		int ithSmallest = k / factorial[curr.length - 1 - index];
		for (int i =  curr.length - 1; i >= 0; i--) {
			if (!used[i]) {
				ithSmallest--;
				if (ithSmallest == -1) {
					curr[index] = Character.forDigit(i, 10); ///很重要！！！
//					System.out.println(curr[index]);
					used[i] = true;
					break;
				}
			}
		}
		return helper(curr, used, k % factorial[curr.length - 1 - index], index + 1, factorial);
	}
	
	private static void calFactorial(int[] factorial) {
		int rst = 1;
		for (int i = 0; i < factorial.length; i++) {
			rst *= (i + 1);
			factorial[i] = rst;
		}
	}
}