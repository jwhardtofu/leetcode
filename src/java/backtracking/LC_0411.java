package java.backtracking;

import java.util.HashSet;
import java.util.Set;

public class LC_0411 {
    private String res;

    public String minAbbreviation(String target, String[] dictionary) {
        res = null;
        Set<String> set = new HashSet();
        int n = target.length();
        for (String s : dictionary) {
            if (isValid(target, s, set))
                backtrack(s.toCharArray(), 0, new StringBuilder(), 0, set);
        }
        backtrack2(target.toCharArray(), 0, new StringBuilder(), 0, set);
        if (res == null) return null;
        return res;
    }

    private void backtrack(char[] ch, int idx, StringBuilder sb, int ct, Set<String> set) {
        int l = sb.length();
        if (idx >= ch.length) {
            if (ct != 0) sb.append(ct);
            set.add(sb.toString());
        }
        else {
            backtrack(ch, idx + 1, sb, ct + 1, set);
            if (ct != 0) sb.append(ct);
            sb.append(ch[idx]);
            backtrack(ch, idx + 1, sb, 0, set);
        }
        sb.setLength(l);
    }

    private void backtrack2(char[] ch, int idx, StringBuilder sb, int ct, Set<String> set) {
        int l = sb.length();
        if (idx >= ch.length) {
            if (ct != 0) sb.append(ct);
            String abbr = sb.toString();
            if (!set.contains(abbr)) {
                if (res == null) res = abbr;
                else if (res.length() > abbr.length()) res = abbr;
            }
        }
        else {
            backtrack2(ch, idx + 1, sb, ct + 1, set);
            if (ct != 0) sb.append(ct);
            sb.append(ch[idx]);
            backtrack2(ch, idx + 1, sb, 0, set);
        }
        sb.setLength(l);
    }

    private boolean isValid(String target, String dict, Set<String> set) {
        if (target.length() != dict.length()) return false;
        set.add("" + target.length());
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == dict.charAt(i)) return true;
        }
        return false;
    }
}
