/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz3
 * @Description: [从尾到头打印链表]
 * @Author: [clh]
 * @Date: 2021/10/11 16:26
 **/

import java.util.ArrayList;
import java.util.Stack;

/**
 *    public class ListNode {
 *        int val;
 *        ListNode next = null;
 *
 *        ListNode(int val) {
 *            this.val = val;
 *        }
 *    }
 *
 */

public class Jz6 {

    //solution 1: from github by using recursion
    //要打印1-2-3, 先逆序打印2-3,再打印1
    //要打印2-3 先逆序打印3 再打印2
    public ArrayList<Integer> solution1(ListNode listnode){
        ArrayList<Integer> ret = new ArrayList<>();
        if(listnode != null){
            ret.addAll(solution1(listnode.next));
            ret.add(listnode.val);
        }
        return ret;
    }
    /**
     * arraylist.addAll(int index, Collection c)
     * index（可选参数）- 表示集合元素插入处的索引值 如果 index 没有传入实际参数，元素将追加至数组的最末尾。
     * c 要插入的集合元素
     */

    //solution 2: from github 头插入法
    // 把节点插入到头部，遍历原始链表时，把当前节点插入新链表的头部，使其成为第一个节点
    // 要引入头结点
    public ArrayList<Integer> solution2(ListNode listNode){
        //头插法构建逆序链表
        ListNode head = new ListNode(-1);
        // 这一段没看懂……
        while(listNode != null){
            ListNode memo = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = memo;
        }

        /**
        //我按照自己理解改
        while(listNode != null){
            //发现居然一样 自己想着写就清楚了
            ListNode tmp = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = tmp;
        }
         */

        ArrayList<Integer> ret = new ArrayList<>();
        head = head.next;
        while(head != null){
            ret.add(head.val);
            head = head.next;
        }
        return ret;
    }

    // solution 3: from github 利用栈
    public ArrayList<Integer> solution3(ListNode listnode){
        Stack<Integer> stack = new Stack<>();
        while(listnode != null){
            stack.add(listnode.val);
            listnode = listnode.next;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        while(!stack.isEmpty()){
            ret.add(stack.pop());
        }
        return ret;
    }

    //solution 4: from newcoder 利用ArrayList本身的函数add
    public ArrayList<Integer> solution4(ListNode listnode){
        ArrayList<Integer> list = new ArrayList<>();
        while(listnode != null){
            list.add(0, listnode.val);
            listnode = listnode.next;
        }
        return list;
    }

}
