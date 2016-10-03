
Problem:

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

------------------------------------------------------------------------------------------------------

Solution One: Beats 50% Using FIFO(First In First Out Queue)


public List<String> letterCombinations(String digits) {
    LinkedList<String> ans = new LinkedList<String>();
    if(digits == null || digits.length() == 0) {
    	return ans;
    }
    String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    ans.add("");
    for(int i =0; i<digits.length();i++){
        int x = Character.getNumericValue(digits.charAt(i));
        while(ans.peek().length()==i){	//精华!!!! 巧妙地运用了peek()
            String t = ans.remove();
            for(char s : mapping[x].toCharArray()) //
                ans.add(t+s);
        }
    }
    return ans;
}

------------------------------------------------------------------------------------------------------

Solution Two: Dfs

public class Solution {
    public ArrayList<String> letterCombinations(String digits) {
        String[] dic = {" ","", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ArrayList<String> ret = new ArrayList<String>();
        StringBuilder temp = new StringBuilder(); 
        dfs(digits, dic, 0, temp, ret);
        return ret;
    }
    public void dfs(String digits, String[] dic, int deep, StringBuilder temp, ArrayList<String> ret){
        if(deep == digits.length()){
            ret.add(temp.toString());
            return;
        }
        else{
            for(int i = 0; i < dic[digits.charAt(deep) - '0'].length(); i++){
                temp.append(dic[digits.charAt(deep) - '0'].charAt(i));
                dfs(digits, dic, deep+1, temp, ret);
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }
}