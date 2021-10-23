import java.util.*;


/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz77
 * @Description: [按之字形顺序打印二叉树]
 * @Author: [clh]
 * @Date: 2021/10/12 19:45
 **/
public class Jz77 {
    //给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）

    //mine 啊啊啊啊  又错了
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
//        int cnt = 0;
        int lev = 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(pRoot);
        while(!que.isEmpty()){
            ArrayList<Integer> tmp = new ArrayList<>();
            int cnt = que.size();
            lev++;
            while(cnt-- > 0){
                TreeNode node = que.poll();
                if(node == null)
                    continue;
                tmp.add(node.val);
                if(lev % 2 == 0){
                    que.add(node.left);
                    que.add(node.right);
                }else{
                    que.add(node.right);
                    que.add(node.left);
                }
            }
            ret.add(tmp);
        }
        return ret;
    }

    //solution from github
    //没有办法用队列走出之字形 只能顺序遍历然后翻转
    public ArrayList<ArrayList<Integer>> solution1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if (reverse)
                Collections.reverse(list);
            reverse = !reverse;
            if (list.size() != 0)
                ret.add(list);
        }
        return ret;
    }

    //solution 2 from newcoder by using stack
    //用了2个栈
    public ArrayList<ArrayList<Integer> > solution3(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(pRoot == null) return result;
        Stack<TreeNode> tmp = new Stack<>();
        Stack<TreeNode> tmp1 = new Stack<>();
        tmp.add(pRoot);
        while(tmp.size() > 0 || tmp1.size() > 0) {
            ArrayList<Integer> flood = new ArrayList<>();
            if(tmp.size() > 0) {
                int size = tmp.size();
                for(int i=0; i<size; i++) {
                    TreeNode pop = tmp.pop();
                    flood.add(pop.val);
                    if(pop.left != null) {
                        tmp1.add(pop.left);
                    }
                    if(pop.right != null) {
                        tmp1.add(pop.right);
                    }

                }
                result.add(flood);
                continue;
            }
            if(tmp1.size() > 0) {
                int size = tmp1.size();
                for(int i=0; i<size; i++) {
                    TreeNode pop = tmp1.pop();
                    flood.add(pop.val);
                    if(pop.right != null) {
                        tmp.add(pop.right);
                    }
                    if(pop.left != null) {
                        tmp.add(pop.left);
                    }
                }
                result.add(flood);
                continue;
            }
        }
        return result;
    }
}
