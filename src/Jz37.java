import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz61
 * @Description: [序列化二叉树]
 * @Author: [clh]
 * @Date: 2021/10/13 9:43
 **/
public class Jz37 {

    //自己写的 搞了几个钟 终于调试成功了！！！虽然反序列化没写出来
    static String Serialize(TreeNode root) {
        StringBuffer buf = new StringBuffer();
        if (root == null)
            return null;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        int cnt = 1; //用来记录结点个数
        while (!que.isEmpty()) {
            TreeNode node = que.poll();

             if(node.val == -1){
             buf.append('#');
             continue;
             }
             else{
             buf.append(String.valueOf(node.val));
             }

            //buf.append(String.valueOf(node.val));

             if(node.left != null){
                 que.add(node.left);
                 cnt++;
             }else{
             //                TreeNode tmp = new TreeNode();
             //                tmp.val = -1;
             //                que.add(tmp);
                que.add(new TreeNode(-1));
             }
             if(node.right != null){
                 que.add(node.right);
                 cnt++;
             }else{
             //                TreeNode tmp = new TreeNode();
             //                tmp.val = -1;
             //                que.add(tmp);
                que.add(new TreeNode(-1));
             }


            /**
            if (node.left != null) {
                que.add(node.left);
                cnt++;
            } else {
                buf.append('#');
            }
            if (node.right != null) {
                que.add(node.right);
                cnt++;
            } else {
                buf.append('#');
            }
             */
        }
        int high = (int) (Math.log(cnt)/Math.log(2) + 1);
        int size = (int) (Math.pow(2, high) - 1);
        String s = buf.toString();
        //public String substring(int beginIndex, int endIndex)--> [)
        return s.substring(0, size);

    }
//    TreeNode Deserialize(String str) {
//        int len = str.length();
//        if(len == 0)
//            return null;
//        TreeNode root = new TreeNode(str.charAt(0));
//        TreeNode cur = root;
//        TreeNode bro = null;
//        //遍历
//        for(int i = 1; i < len - 1; i++){
//            if(cur == null){
//                cur = bro;
//            }
//            if(bro == null){
//                continue;
//            }
//            char l = str.charAt(i);
//            char r = str.charAt(i + 1);
//            if(l == '#'){
//                cur.left = null;
//            }else{
//                cur.left = new TreeNode(l);
//            }
//            if(r == '#'){
//                cur.right = null;
//            }else{
//                cur.right = new TreeNode(r);
//            }
//            cur = cur.left;
//            bro = cur.right;
//        }
//    }


    //solution 1 from github
    //这种写法是我觉得像是dfs 我的是bfs
    //感觉他这个是序列化 反序列化都可以自己定义遍历方法 只要序列化和反序列化对应即可

    //这个像是先序遍历
    public String solution1(TreeNode root){
        if(root == null)
            return "#";
        return root.val + " " + solution1(root.left) + " " + solution1(root.right);
    }
    private static String deserialStr;

    public static TreeNode Deserialize(String str){
        deserialStr = str;
        return Deserialize();
    }

    private static TreeNode Deserialize() {
        if(deserialStr.length() == 0)
            return null;
        int index = deserialStr.indexOf(" ");
        //如果找不到" " index = -1 说明字符串为空 因为如果是只有一个结点的话 返回的序列化也应该是 1 # #
        //找得到" "说明利用substring可以找到头结点
        String node = index == -1 ? deserialStr : deserialStr.substring(0, index);
        //如果找到头结点 就将头结点排除 继续反序列化剩下的字符串即可
        deserialStr = index == -1 ? "" : deserialStr.substring(index + 1);
        //遇到# 说明为空
        if(node.equals("#"))
            return null;
        //非空 说明第一个是左结点
        int val = Integer.valueOf(node);
        TreeNode t = new TreeNode(val);
        t.left = Deserialize();
        t.right = Deserialize();
        return t;
    }

    public static void main(String[] args){
        /**
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        root.left = n2;
        root.right = n3;
        n3.left = n6;
        n3.right = n7;
        String s = Serialize(root);
        String s2 = solution1(root);
        System.out.println(s);
        System.out.println(s2);
        */
        String s = "1 2 # # 3 6 # # 7 # #";
        TreeNode node = Deserialize(s);
    }
}
