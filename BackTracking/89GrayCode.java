Problem:

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

----------------------------------------------------------------------------------------------------
My Solution:

镜像生成法
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        if (n == 0) {
            return result;
        }
        result.add(1);
        if (n == 1) {
            return result;
        }
        for (int i = 1; i < n; i++) {
            int length = result.size();
            int tmp = 1 << i;
            for (int j = length - 1; j >= 0; j--) {
                result.add(result.get(j) | tmp);
            }
        }
        return result;
    }
}


Discussion的方法
/*
n = 0: 0
n = 1: 0 | 1
n = 2: 00, 01 | 11, 10
n = 3: 000, 001, 011, 010 | 110, 111, 101, 100
f(n) = f(n - 1) | 2 ^ (n - 1) + f(n - 1)
*/
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        
        for(int i = 1; i <= n; i++) {
            int size = res.size(), base = 1 << (i - 1);
            for(int j = size - 1; j >= 0; j--) {
                res.add(base + res.get(j));
            }
        }
        return res;
    }
}

方法2
public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        for (int i = 1, shift = 1; i <= n; i++) {
            // mirror from previous 2 ^ (i-1) 
            for (int j = shift-1; j >= 0; j--) {
                // ret.get(j) ^ shift is more efficient here that +
                ret.add((ret.get(j) ^ shift));
            }
            shift <<= 1;
        }
        
        return ret;
    }
----------------------------------------------------------------------------------------------------
Solution:

位移的方法。
B（n / 2) XOR B(n)
 public List<Integer> grayCode(int n) {
    List<Integer> result = new LinkedList<>();
    for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
    return result;
}
