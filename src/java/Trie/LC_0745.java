/**
 * Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.
 *
 * Implement the WordFilter class:
 *
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix.
 * If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 */

package java.Trie;

import java.util.ArrayList;
import java.util.List;

public class LC_0745 {
    private TrieNode preTrie;
    private TrieNode sufTrie;
    private class TrieNode{
        TrieNode[] next;
        List<Integer> index;
        public TrieNode(){
            next = new TrieNode[26];
            index = new ArrayList();
        }
    }

    public LC_0745(String[] words) {
        preTrie = new TrieNode();
        sufTrie = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            insert(words[i].toCharArray(), preTrie, sufTrie, i);
        }
    }

    private void insert(char[] ch, TrieNode preTN, TrieNode sufTN, int idx) {
        int n = ch.length;
        for (int i = 0; i < n; i++) {
            if (preTN.next[ch[i] - 'a'] == null)
                preTN.next[ch[i] - 'a'] = new TrieNode();
            if (sufTN.next[ch[n - 1 - i] - 'a'] == null)
                sufTN.next[ch[n - 1 - i] - 'a'] = new TrieNode();
            preTN = preTN.next[ch[i] - 'a'];
            sufTN = sufTN.next[ch[n - 1 - i] - 'a'];
            preTN.index.add(idx);
            sufTN.index.add(idx);
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode preTN = preTrie;
        TrieNode sufTN = sufTrie;
        char[] prefixArr = prefix.toCharArray();
        char[] suffixArr = suffix.toCharArray();
        for (int i = 0; i < prefixArr.length; i++) {
            if (preTN.next[prefixArr[i] - 'a'] == null) return -1;
            preTN = preTN.next[prefixArr[i] - 'a'];
        }
        for (int i = suffixArr.length - 1; i >= 0; i--) {
            if (sufTN.next[suffixArr[i] - 'a'] == null) return -1;
            sufTN = sufTN.next[suffixArr[i] - 'a'];
        }
        int i = preTN.index.size() - 1;
        int j = sufTN.index.size() - 1;
        while (i >= 0 && j >= 0) {
            int preIndex = preTN.index.get(i);
            int sufIndex = sufTN.index.get(j);
            if (preIndex > sufIndex) {
                i--;
            }
            else if (preIndex < sufIndex) {
                j--;
            }
            else return preIndex;
        }
        return -1;
    }

}
