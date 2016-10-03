Problem:

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

------------------------------------------------------------------------------------------------------
Solution One: 

思路：

通过向string插入"("和")"直到两者的数量都为n，则一个combination构建完成。如何保证这个combination是well-formed？在插入过程中的任何时候：

1. 只要"("的数量没有超过n，都可以插入"("。
2. 而可以插入")"的前提则是当前的"("数量必须要多余当前的")"数量。

public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<String>();
    generateOneByOne("", list, n, n);
    return list;
}
public void generateOneByOne(String sublist, List<String> list, int left, int right){
    if(left > right){
        return;
    }
    if(left > 0){
        generateOneByOne( sublist + "(" , list, left-1, right);
    }
    if(right > 0){
        generateOneByOne( sublist + ")" , list, left, right-1);
    }
    if(left == 0 && right == 0){
        list.add(sublist);
        return;
    }
}