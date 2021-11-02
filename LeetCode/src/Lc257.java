import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc257
 * @Description: [257. Binary Tree Paths (Easy) 输出二叉树中所有从根到叶子的路径]
 * @Author: [clh]
 * @Date: 2021/11/1 16:39
 **/
public class Lc257 {
    //我自己写的 搞了贼久……
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if(root == null)
            return ans;
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        backtracking(sb, root, ans);
        return ans;
    }

    private void backtracking(StringBuilder sb,TreeNode node, List<String> ans) {
//        sb.append(node.val);
        if(node.left == null && node.right == null){
//            sb.append(node.val);
            ans.add(sb.toString());
            return;
        }
//        sb.append("->");
        if(node.left != null){
            /*sb.append("->");
            sb.append(node.left.val);*/
            String tmp = "->"+node.left.val;
            int cnt = tmp.length();
            sb.append(tmp);
            backtracking(sb, node.left, ans);
            //因为有可能出现多位数啊
            /*sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);*/
            while(cnt-- > 0)
                sb.deleteCharAt(sb.length() - 1);
        }
        if(node.right != null){
            String tmp = "->"+node.right.val;
            int cnt = tmp.length();
//            sb.append("->");
//            sb.append(node.right.val);
            sb.append(tmp);
            backtracking(sb, node.right, ans);
            while(cnt-- > 0)
                sb.deleteCharAt(sb.length() - 1);
            //因为有可能出现多位数啊
//            sb.deleteCharAt(sb.length() - 1);
//            sb.deleteCharAt(sb.length() - 1);
        }
//        sb.deleteCharAt(sb.length() - 1);
//        if(sb.length() > 0)
//            sb.deleteCharAt(sb.length() - 1);
//        else
//            return;
    }

    //2 github
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        List<Integer> values = new ArrayList<>();
        backtracking2(root, values, paths);
        return paths;
    }

    private void backtracking2(TreeNode node, List<Integer> values, List<String> paths) {
        if (node == null) {
            return;
        }
        values.add(node.val);
        if (isLeaf(node)) {
            paths.add(buildPath(values));
        } else {
            backtracking2(node.left, values, paths);
            backtracking2(node.right, values, paths);
        }
        values.remove(values.size() - 1);
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private String buildPath(List<Integer> values) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            str.append(values.get(i));
            if (i != values.size() - 1) {
                str.append("->");
            }
        }
        return str.toString();
    }

}
