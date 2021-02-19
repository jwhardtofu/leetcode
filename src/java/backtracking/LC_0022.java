/*
Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
Example 1:
Input: n = 3
Output: Output: ["((()))","(()())","(())()","()(())","()()()"]
Constraints:
1 <= n <= 8
 */

package java.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC_0022 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(new char[n * 2], 0, res, 0, 0, n);
        return res;
    }

    private void backtracking(char[] ch, int i, List<String> res, int l, int r, int n) {

        if (l == n && r == n) res.add(new String(ch));

        if (l < n) {
            ch[i] = '(';
            backtracking(ch, i + 1, res, l + 1, r, n);
        }

        if (r < l) {
            ch[i] = ')';
            backtracking(ch, i + 1, res, l, r + 1, n);
        }
    }
}
