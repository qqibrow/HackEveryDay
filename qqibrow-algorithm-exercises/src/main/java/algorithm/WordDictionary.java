package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lniu on 5/21/16.
 */
public class WordDictionary {
        public class TireNode {
            Map<Character, TireNode> children;
            boolean isLeaf;
            char c;
            public TireNode() {
                children = new HashMap<>();
                isLeaf = false;
            }
            public TireNode(char value) {
                this();
                c = value;
            }
        }

        TireNode root = new TireNode();

        // Adds a word into the data structure.
        public void addWord(String word) {
            TireNode parent = root;
            for(int i = 0; i < word.length(); ++i) {
                char curr = word.charAt(i);
                if(!parent.children.containsKey(curr)) {
                    TireNode newChild = new TireNode(curr);
                    parent.children.put(curr, newChild);
                }
                parent = parent.children.get(curr);
            }
            parent.isLeaf = true;
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            return search(word, root);
        }

        public boolean search(String word, TireNode parent) {
            TireNode p = parent;
            for(int i = 0; i < word.length(); ++i) {
                char currChar = word.charAt(i);
                if(currChar == '.') {
                    for(TireNode child: p.children.values()) {
                        if(search(word.substring(i+1), child)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    if(!p.children.containsKey(currChar)) {
                        return false;
                    }
                    p = p.children.get(currChar);
                }
            }
            return p.isLeaf;
        }

    public static  void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("ab");
        wordDictionary.addWord("ac");
        wordDictionary.addWord("ad");
        System.out.println(wordDictionary.search(".."));
        System.out.println(wordDictionary.search("a."));
        System.out.println(wordDictionary.search(".f"));
    }


}

