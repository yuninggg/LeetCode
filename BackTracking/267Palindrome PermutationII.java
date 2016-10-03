Problem:

Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

----------------------------------------------------------------------------------------------------
My Solution:

public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = initMap(arr);
        int oddOccurCount = 0;
        char oddChar = ' ';
        // check if there are palindromic permutation could be form and set middle to the single char
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (arr.length % 2 == 0 && entry.getValue() % 2 != 0) {
                return result;
            } else if (arr.length % 2 != 0) {
                if (entry.getValue() % 2 != 0) {
                    if (oddOccurCount != 0) {
                        return result;
                    } else {
                        oddOccurCount++;
                        oddChar = entry.getKey();
                    }
                }
            }
        }
        char[] curr = new char[arr.length];
        if (arr.length % 2 != 0) {
            curr[arr.length / 2] = oddChar;
            map.put(oddChar, map.get(oddChar) - 1);
        }
        helper(result, curr, 0, map);
        return result;
    }
    
    private void helper(List<String> result, char[] curr, int index, Map<Character, Integer> map) {
        if (index >= curr.length / 2) {
            result.add(new String(curr));
            return;
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character c = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0) {
                continue;
            }
            curr[index] = c;
            curr[curr.length - 1 - index] = c;
            map.put(c, count - 2);
            helper(result, curr, index + 1, map);
            map.put(c, count); //backtracking
        }
    }
    
    private Map<Character, Integer> initMap(char[] arr) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : arr) {
            Integer count = map.get(c);
            if (count == null) {
                map.put(c, 1);
            } else {
                map.put(c, count + 1);
            }
        }
        return map;
    }
}

----------------------------------------------------------------------------------------------------
Discussion Solution:

public List<String> generatePalindromes(String s) {
    int odd = 0;
    String mid = "";
    List<String> res = new ArrayList<>();
    List<Character> list = new ArrayList<>();
    Map<Character, Integer> map = new HashMap<>();

    // step 1. build character count map and count odds
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        Integer count = map.get(c);
        map.put(c, count == null ? count + 1 : 1);
        odd += count % 2 != 0 ? 1 : -1; // 这个思路非常好！ 不需要单独抽离成一个循环来写
    }

    // cannot form any palindromic string
    if (odd > 1) return res; // 这里长度为偶数的string， 如果有一个落单的， 那么必定也有第二个落单的

    // step 2. add half count of each character to list
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
        char key = entry.getKey();
        int val = entry.getValue();
        if (val % 2 != 0) mid += key;
        for (int i = 0; i < val / 2; i++) {
            list.add(key);
        }
    }

    // step 3. generate all the permutations
    getPerm(list, mid, new boolean[list.size()], new StringBuilder(), res);

    return res;
}

// generate all unique permutation from list
void getPerm(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> res) {
    if (sb.length() == list.size()) {
        // form the palindromic string
        res.add(sb.toString() + mid + sb.reverse().toString());
        sb.reverse();
        return;
    }

    for (int i = 0; i < list.size(); i++) {
        // avoid duplication
        if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) continue;

        if (!used[i]) {
            used[i] = true; sb.append(list.get(i));
            // recursion
            getPerm(list, mid, used, sb, res);
            // backtracking
            used[i] = false; sb.deleteCharAt(sb.length() - 1);
        }
    }
}

分析: 别人家的代码， 简洁！



----------------------------------------------------------------------------------------------------

public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        char[] arr = s.toCharArray();
        char[] curr = new char[arr.length];
        Map<Character, Integer> map = new HashMap<>();
        int odd = 0;
        char oddChar = ' ';
        // check if there are palindromic permutation could be form and set middle to the single char
        for (int i = 0; i < s.length(); i++) {
            Integer count = map.get(arr[i]);
            count = (count == null) ? 1 : count + 1;
            map.put(arr[i], count);
            odd += count % 2 != 0 ? 1 : -1; // 这个思路非常好！ 不需要单独抽离成一个循环来写
        }
        if (odd > 1)  return result;
        
        if (arr.length % 2 != 0) {
            Integer count = null;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() % 2 != 0) {
                    oddChar = entry.getKey();
                    count = entry.getValue() - 1;
                    break;
                }
            }
            curr[arr.length / 2] = oddChar;
            map.put(oddChar, count);
        }
        helper(result, curr, 0, map);
        return result;
    }
    
    private void helper(List<String> result, char[] curr, int index, Map<Character, Integer> map) {
        if (index >= curr.length / 2) {
            result.add(new String(curr));
            return;
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character c = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0) {
                continue;
            }
            curr[index] = c;
            curr[curr.length - 1 - index] = c;
            map.put(c, count - 2);
            helper(result, curr, index + 1, map);
            map.put(c, count); //backtracking
        }
    }
}

