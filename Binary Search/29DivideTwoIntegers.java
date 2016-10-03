




public class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return (dividend > 0) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            // return -dividend; 注意别人怎么写的，要考虑overflow
            return (dividend == Integer.MIN_VALUE) ? Integer.MAX_VALUE : -dividend;
        }
        long dividend2 =  Math.abs((long)dividend);
        // System.out.println(dividend2);
        long divisor2 =  Math.abs((long)divisor);
        // System.out.println(divisor2);

        int sign = (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) ? 1 : -1; 
        long result = longDivide(dividend2, divisor2);
        // System.out.println(result);
        if (result > Integer.MAX_VALUE) {
            return (sign > 0) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        return (sign > 0) ? (int) result : - (int)result;
        
    }
    private long longDivide(long dividend, long divisor) {
        if (dividend < divisor) {
            return 0;
        }
        long sum = divisor;
        long multiplier = 1;
        while (sum + sum <= dividend) {
            multiplier += multiplier;
            sum += sum;
        }
        return multiplier + longDivide(dividend - sum, divisor);
    }
}