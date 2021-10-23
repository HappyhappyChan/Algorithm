/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz4
 * @Description: [重建二叉树]
 * @Author: [clh]
 * @Date: 2021/10/12 10:46
 **/

import java.util.Arrays;
import java.util.HashMap;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Jz7 {
    //给定节点数为 n 二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
    //前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分
    // 左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。
    // 然后分别对左右子树递归地求解。

    //solution 1 : from newcoder
    public TreeNode solution1(int[] pre, int[] in){
        if(pre.length == 0 || in.length == 0)
            return null;
        TreeNode root = new TreeNode(pre[0]);
        //在中序中找到前序的根
        for(int i = 0; i < in.length; i++){
            if(in[i] == pre[0]){
                //结合github的解释图例看更容易看懂
                //左子树 copyOfRange函数 左闭右开
                //copyOfRange(array,int from,int to),original为原始的int型数组，from为开始角标值，to为终止角标值。
                // （其中包括from角标，不包括to角标。即处于[from,to)状态）
                root.left = solution1(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = solution1(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }

    //solution 2: from github
    //缓冲中序遍历数组每个值对应的索引
    private HashMap<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in){
        for(int i = 0; i < in.length; i++){
            indexForInOrders.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL){
        if(preL > preR)
            return null;
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }

}
