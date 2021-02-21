package java.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * Example 1
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 */
public class LC_0212 {
    private class TrieNode {
        TrieNode[] next;
        boolean isWord;

        public TrieNode() {
            next = new TrieNode[26];
            isWord = false;
        }
    }

    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res, new StringBuilder());
            }
        }
        return res;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String s : words) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                int i = c - 'a';
                if (cur.next[i] == null) cur.next[i] = new TrieNode();
                cur = cur.next[i];
            }
            cur.isWord = true;
        }
        return root;
    }

    private void dfs(char[][] board,
                     int i, int j, TrieNode node, List<String> res, StringBuilder sb) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        if (c == '.' || node.next[c - 'a'] == null) return;
        board[i][j] = '.';
        sb.append(c);
        node = node.next[c - 'a'];
        if (node.isWord) {
            res.add(sb.toString());
            node.isWord = false;
        }
        for (int[] dir : dirs)
            dfs(board, i + dir[0], j + dir[1], node, res, sb);
        board[i][j] = c;
        sb.setLength(sb.length() - 1);
    }


}
