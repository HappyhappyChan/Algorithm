import java.util.Stack;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz62
 * @Description: [二叉搜索树的第K个结点]
 * @Author: [clh]
 * @Date: 2021/10/13 15:49
 **/
public class Jz62 {
    //给定一棵结点数为 n 二叉搜索树，请找出其中的第 k 小的TreeNode结点。
    //from github
    private TreeNode ret;
    private int cnt = 0;
    TreeNode KthNode(TreeNode pRoot, int k) {
        inOrder(pRoot, k);
        return ret;
    }

    private void inOrder(TreeNode root, int k) {
        if(root == null || cnt >= k)
            return;
        inOrder(root.left, k);
        cnt++;
        if(cnt == k){
            ret = root;
        }
        inOrder(root.right, k);
    }

    //from newcoder 非递归用栈实现
    TreeNode solution(TreeNode root, int k){
        if(root == null || k <= 0)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        //中序遍历
        while(!stack.isEmpty() || cur != null){ //因为有可能左子树+根结点都弹出去还是没到k
            if(cur != null){
                stack.push(cur); //不为空 寻找左孩子
                cur = cur.left;
            }else{
                cur = stack.pop(); //当前结点null 则弹出栈内元素 相当于按顺序输出最小值
                if(--k == 0){
                    return cur;
                }
                cur = cur.right;
            }
        }
        return null;
    }

}
