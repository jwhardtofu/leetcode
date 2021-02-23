/**
 * Design a data structure that supports adding new words and finding
 * if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data
 *      structure that matches word or false otherwise.
 *      word may contain dots '.' where dots can be matched with any letter.
 */
package java.Trie;

public class LC_0211 {
    private TrieNode root;
    private class TrieNode {
        TrieNode[] next;
        boolean isWord;
        public TrieNode(){
            next = new TrieNode[26];
            isWord = false;
        }
    }

    /** Initialize your data structure here. */
    public LC_0211() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        return search(word.toCharArray(), 0, node);
    }

    private boolean search(char[] ch, int idx, TrieNode node) {
        if (idx >= ch.length) return node.isWord;
        if (ch[idx] == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.next[i] == null) continue;
                else {
                    if (!search(ch, idx + 1, node.next[i])) continue;
                    else return true;
                }
            }
            return false;
        }
        else {
            if (node.next[ch[idx] - 'a'] == null) return false;
            else node = node.next[ch[idx] - 'a'];
        }
        return search(ch, idx + 1, node);
    }
}
