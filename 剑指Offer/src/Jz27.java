import java.util.*;
/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz18
 * @Description: [二叉树的镜像]
 * @Author: [clh]
 * @Date: 2021/10/12 15:36
 **/
/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 *   public TreeNode(int val) {
 *     this.val = val;
 *   }
 * }
 */
public class Jz27 {

    //mine 通过了！
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if(pRoot == null)
            return null;
        //先转左子树
        if(pRoot.left != null)
            pRoot.left = Mirror(pRoot.left);
        //再转右子树
        if(pRoot.right != null)
            pRoot.right = Mirror(pRoot.right);
        if(pRoot.left == null && pRoot.right == null)
            return pRoot;
        //再将左右子树兑换
        TreeNode t = pRoot.left;
        pRoot.left = pRoot.right;
        pRoot.right = t;
        return pRoot;
    }

    //solution from github
    public TreeNode solution1(TreeNode root){
        if(root == null)
            return root;
        swap(root);
        solution1(root.left);
        solution1(root.right);
        return root;
    }

    private void swap(TreeNode root) {
        TreeNode t = root.left;
        root.left = root;
        root.right = t;
    }

    //solution 2 from newcoder by using bfs
    //bfs algorithm
    public static void treeBFS(TreeNode root){
        //if is null return
        if(root == null)
            return;
        //queue
        Queue<TreeNode> que = new LinkedList<>();
        //add root into que
        que.add(root);
        //队列不为空继续循环
        while(!que.isEmpty()){
            TreeNode node = que.poll();
            //打印当前结点
            System.out.println(node.val);
            //如果当前结点左子树不为空 则把左子树加入队列
            if(node.left != null)
                que.add(node.left);
            //如果右子树不为空 把右子树加入队列
            if(node.right != null)
                que.add(node.right);
        }
    }

    //遍历每个结点 交换结点 一直循环下去
    public TreeNode solution2(TreeNode root){
        if(root == null)
            return null;
        final Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode node = que.poll();
            //交换node 两个节点
            TreeNode t = node.left;
            node.left = node.right;
            node.right = t;
            //左子树不空则把左子树加入
            if(node.left != null)
                que.add(node.left);
            if(node.right != null)
                que.add(node.right);
        }
        return root;
    }

    //solution 3 from newcoder by using dfs
    //访问每个结点都交换他的左右子节点 直到所有节点都访问完
    //dfs 对于每个节点来说，先遍历当前节点，然后把右节点压栈，再压左节点(这样弹栈的时候会先拿到左节点遍历，符合深度优先遍历要求)。
    public TreeNode solution3(TreeNode root){
        if(root == null)
            return null;
        //dfs用栈
        //bfs用队列
        Stack<TreeNode> stack = new Stack<>();
        //根结点压栈
        stack.push(root);
        //栈不为空就继续循环
        while(!stack.isEmpty()){
            //出栈
            TreeNode node = stack.pop();
            //子节点交换
            TreeNode t = node.left;
            node.left = node.right;
            node.right = t;
            //左子节点不为空入栈
            if (node.left != null)
                stack.push(node.left);
            //右子节点不为空入栈
            if (node.right != null)
                stack.push(node.right);
        }
        return root;
    }
}
