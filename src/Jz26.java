/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz17
 * @Description: [树的子结构]
 * @Author: [clh]
 * @Date: 2021/10/12 15:24
 **/

//输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Jz26 {

    //solution 1 from newcoder
    //github 思路和这个一样
    //写一个方法 传入两棵根节点值相同的树，判断t1 t2结构是否一样
    public boolean doesTree1HasTree2(TreeNode t1, TreeNode t2){
        //子结构已经循环完毕，代表全部匹配
        if(t2 == null){
            return true;
        }
        //这里没看懂
        //B的孩子结点是否是空都不影响B是A的子结构，所以返回‘true'，
        // 但是在B的孩子结点不为空if（root2 == null）这个条件没有符合的前提下，A的孩子结点出现空了 那B必然不是A的子结构 所以返回’false‘
        //而且在主函数就排除空树的情况了
        //大树已经循环完毕，并未成功匹配
        if(t1 == null){
            return false;
        }

        if(t1.val != t2.val)
            return false;
        return doesTree1HasTree2(t1.left, t2.left) && doesTree1HasTree2(t1.right, t2.right);
    }

    //再写一个方法遍历大树
    //找到一个跟小树根节点值相同的节点
    //再调用上面的方法
    public boolean solution1(TreeNode root1, TreeNode root2){
        //在这里就已经排除空树的情况了
        if(root2 == null || root1 == null)
            return false;
        // ||前面如果是true就不会运行后面的部分，这个和|不一样 短路算法
        return doesTree1HasTree2(root1, root2) || solution1(root1.left, root2) || solution1(root1.right, root2);
    }
}
