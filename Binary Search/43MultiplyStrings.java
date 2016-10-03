Problem:

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

------------------------------------------------------------------------------------------------------
Solution One: Beat 44%

几个要点：
直接乘会溢出，所以每次都要两个single digit相乘，最大81，不会溢出。
比如385 * 97, 就是个位=5 * 7，十位=8 * 7 + 5 * 9 ，百位=3 * 7 + 8 * 9 …
可以每一位用一个Int表示，存在一个int[]里面。
这个数组最大长度是num1.len + num2.len，比如99 * 99，最大不会超过10000，所以4位就够了。
这种个位在后面的，不好做（10的0次方，可惜对应位的数组index不是0而是n-1），
所以干脆先把string reverse了代码就清晰好多。
最后结果前面的0要清掉。

public String multiply(String num1, String num2) {

    num1 = new StringBuilder(num1).reverse().toString();
    num2 = new StringBuilder(num2).reverse().toString();
    // even 99 * 99 is < 10000, so maximaly 4 digits
    int[] d = new int[num1.length() + num2.length()];

    for (int i = 0; i < num1.length(); i++) {
        int a = num1.charAt(i) - '0';
        for (int j = 0; j < num2.length(); j++) {
            int b = num2.charAt(j) - '0';
            d[i + j] += a * b;
        }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < d.length; i++) {
        int digit = d[i] % 10;
        int carry = d[i] / 10;
        sb.insert(0, digit);
        if (i < d.length - 1)
            d[i + 1] += carry;
    }
    //trim starting zeros
    while (sb.length() > 0 && sb.charAt(0) == '0') {
        sb.deleteCharAt(0);
    }
    return sb.length() == 0 ? "0" : sb.toString();
}



------------------------------------------------------------------------------------------------------
Solution One: Beat 79%

public class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
    
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
    
                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }  
    
        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}