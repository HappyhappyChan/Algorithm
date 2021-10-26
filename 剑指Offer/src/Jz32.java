import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz22
 * @Description: [从上往下打印二叉树]
 * @Author: [clh]
 * @Date: 2021/10/12 16:53
 **/
public class Jz32 {
    //solution 1 from github 通过队列进行层次遍历
    //我看了思路然后自己写的
    //在Java中Queue是和List、Map同等级别的接口，LinkedList中也实现了Queue接口
    // Queue操作
    //容量不够或队列为空时不会抛异常：offer（添加队尾元素）、peek（访问队头元素）、poll（访问队头元素并移除）
    //容量不够或队列为空时抛异常：add、element（访问队列元素）、remove（访问队头元素并移除）
    public ArrayList<Integer> solution1(TreeNode root){
        if(root == null)
            //return null; 不能返回空
            return new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        ArrayList<Integer> ret = new ArrayList<>();
        que.add(root);
        while(!que.isEmpty()){
            TreeNode node = que.poll();
            ret.add(node.val);
            //如果当前结点左子树不为空 则把左子树加入队列
            if(node.left != null)
                que.add(node.left);
            //如果右子树不为空 把右子树加入队列
            if(node.right != null)
                que.add(node.right);
        }
        return ret;
    }
}
