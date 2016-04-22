Problem: Implement pow(x, n).



My Solution: Recursive

public class Solution {
    public double myPow(double x, int n) {
        if (n >= 0) {
            return myHalfPowHelper(x, n);
        } else {
            return 1 / myHalfPowHelper(x, Math.abs(n));            
        }
        
    }
    // return pow(x,n/2);
    private double myHalfPowHelper (double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double halfPow = myHalfPowHelper(x, n / 2);
        if (n % 2 == 0) {
            return  halfPow * halfPow;
        } else {
            return halfPow * halfPow * x;
        }
        
    }
}


Solution 2:  也是recursive但不需要自己定义helper函数。。。 把负号=> 1/底数

public class Solution {
    public double pow(double x, int n) {
        if(n == 0)
            return 1;
        if(n < 0){
            n = -n;
            x = 1/x;
        }
        return (n % 2 == 0) ? pow(x * x, n / 2) : x * pow(x * x, n/2);
    }
}

Solution 3: 不用recursive纯iterative，但这种解法会在 n = Integer.MIN_VALUE 的时候overflow，还没有

	public double pow(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double result = 1;
        for (double f = x; n > 0; n = n >> 1) {
            if (n % 2 == 1) {
                result *= f;
            }
            f = f * f;
        }
        return result;
    }
