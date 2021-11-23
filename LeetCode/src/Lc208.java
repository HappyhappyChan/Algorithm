/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc208
 * @Description: [208. Implement Trie (Prefix Tree) (Medium)  实现一个 Trie]
 * @Author: [clh]
 * @Date: 2021/11/22 16:00
 **/
public class Lc208 {
    //1 leetcode-cn
    class Trie {
        private Trie[] children;
        private boolean isEnd;
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                int idx = ch - 'a';
                if(node.children[idx] == null){
                    node.children[idx] = new Trie();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix){
            Trie node = this;
            for(int i = 0; i < prefix.length(); i++){
                char ch = prefix.charAt(i);
                int idx = ch - 'a';
                if(node.children[idx] == null){
                    return null;
                }
                node = node.children[idx];
            }
            return node;
        }
    }

    //2 看leetcode solution
}
