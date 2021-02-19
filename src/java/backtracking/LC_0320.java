/*
* A word's generalized abbreviation can be constructed by taking any number of non-overlapping
* substrings and replacing them with their respective lengths.
* For example, "abcde" can be abbreviated into "a3e" ("bcd" turned into "3"), "
* 1bcd1" ("a" and "e" both turned into "1").
*
* Given a string word, return a list of all the possible generalized
* abbreviations of word. Return the answer in any order.
*
 */

package java.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC_0320 {

    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        backtrack(word.toCharArray(), 0, new StringBuilder(), 0, res);
        return res;
    }

    private void backtrack(char[] ch, int i, StringBuilder sb, int ct, List<String> res) {
        int l = sb.length();
        if (i >= ch.length) {
            if (ct != 0) sb.append(ct);
            res.add(sb.toString());
        }
        else {
            backtrack(ch, i + 1, sb, ct + 1, res);

            if (ct != 0) sb.append(ct);
            sb.append(ch[i]);
            backtrack(ch, i + 1, sb, 0, res);
        }
        sb.setLength(l);
    }
}
