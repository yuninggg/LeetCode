Problem:

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

------------------------------------------------------------------------------------------------------
Solution: Beats 50%

思路：

这题需要一些背景知识，首先要知道罗马数字是怎么表示的： http://en.wikipedia.org/wiki/Roman_numerals

I: 1
V: 5
X: 10
L: 50
C: 100
D: 500
M: 1000

字母可以重复，但不超过三次，当需要超过三次时，用与下一位的组合表示：
I: 1, II: 2, III: 3, IV: 4
C: 100, CC: 200, CCC: 300, CD: 400

s = 3978
3978/1000 = 3: MMM
978>(1000-100), 998/900 = 1: CM
78<(100-10), 78/50 = 1 :L
28<(50-10), 28/10 = XX
8<(100-1), 8/5 = 1: V
3<(5-1), 3/1 = 3: III
ret = MMMCMLXXVIII

所以可以将单个罗马字符扩展成组合形式，来避免需要额外处理类似IX这种特殊情况。
I: 1
IV: 4
V: 5
IX: 9
X: 10
XL: 40
L: 50
XC: 90
C: 100
CD: 400
D: 500
CM: 900
M: 1000


public class Solution {
    public String intToRoman(int num) {
       int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
       String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
       String result = "";
       for (int i = 0; i < 13; i++) {
           for (int j = 0; j < num / val[i]; j++) {
                result += roman[i];
           }
           num = num % val[i];
       }
       return result;
    }
}

------------------------------------------------------------------------------------------------------
Solution Two: Beats 72%
从第一种解法改进而来, 用了StringBuilder

public class Solution {
    public String intToRoman(int num) {
       if(num <= 0) {
			return "";
		}
	    int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	    StringBuilder res = new StringBuilder();
	    int digit=0;
	    while (num > 0) {
	        int times = num / nums[digit];
	        num -= nums[digit] * times;
	        for ( ; times > 0; times--) {
	            res.append(symbols[digit]);
	        }
	        digit++;
	    }
	    return res.toString();
    }
}



------------------------------------------------------------------------------------------------------

Solution Three: Beats 25% 

Nice and Clean!!


public String intToRoman(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num/1000] + C[(num%1000)/100]+ X[(num%100)/10] + I[num%10];
}