/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc677
 * @Description: [677. Map Sum Pairs (Medium) 实现一个 Trie，用来求前缀和]
 * @Author: [clh]
 * @Date: 2021/11/22 19:11
 **/
public class Lc677 {
    //1 github
    class MapSum {

        private class Node {
            Node[] child = new Node[26];
            int value;
        }

        private Node root = new Node();
        public MapSum() {

        }

        public void insert(String key, int val) {
            insert(key, root, val);
        }

        private void insert(String key, Node node, int val){
            if(node == null) return;
            if(key.length() == 0){
                node.value = val;
                return;
            }
            if(node.child[key.charAt(0) - 'a'] == null){
                node.child[key.charAt(0) - 'a'] = new Node();
            }
            insert(key.substring(1), node.child[key.charAt(0) -'a'], val);
        }
        public int sum(String prefix) {
            return sum(prefix, root);
        }

        private int sum(String prefix, Node node){
            if(node == null)
                return 0;
            if(prefix.length() != 0){
                return sum(prefix.substring(1), node.child[prefix.charAt(0) - 'a']);
            }
            int sum = node.value;
            for(Node child: node.child){
                sum += sum(prefix, child);
            }
            return sum;
        }
    }
}
