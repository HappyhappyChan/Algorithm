import java.util.HashSet;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz56
 * @Description: [删除链表中重复节点]
 * @Author: [clh]
 * @Date: 2021/10/11 19:53
 **/

/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/

    //链表1->2->3->3->4->4->5 处理后为 1->2->5
    //所给的链表是排序链表

public class Jz76 {
    //这里运用递归
    //solution 1 from github
    public ListNode deleteDuplication(ListNode pHead) {
        if(pHead == null || pHead.next == null)
            return pHead;
        ListNode next = pHead.next;
        if(pHead.val == next.val){
            while(next != null && pHead.val == next.val)
                next = next.next;
            return deleteDuplication(pHead);
        }else{
            pHead.next = deleteDuplication(pHead.next);
        }
        return pHead;
    }

    //solution 2 from newcoder
    //用多次遍历 + set 实现
    public ListNode solution2(ListNode phead){
        if(phead == null || phead.next == null)
            return phead;
        //找出相同节点 存入set
        //HashSet基哈希表实现，查找效率O(1) 总效率O(n)
        //最坏的情况存一半节点O(n/2) 最好的是一个不存O(1)
        HashSet<Integer> set = new HashSet<>();
        ListNode pre = phead;
        ListNode cur = phead.next;
        while(cur != null){
            if(cur.val == pre.val)
                set.add(cur.val);
            pre = cur;
            cur = cur.next;
        }
        //再根据相同节点删除
        //先删头部
        while(phead != null && set.contains(phead.val)){
            phead = phead.next;
        }
        if(phead == null){
            return null;
        }
        //再删除中间节点
        pre = phead;
        cur = phead.next;
        while(cur != null){
            if(set.contains(cur.val)){
                pre.next = cur.next;
                cur = cur.next;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return phead;
    }


    //solution 3: from newcoder
    //遍历的同时删除
    //借助辅助头结点，设置2个结点
    public ListNode solution3(ListNode phead){
        //当 cur 和 cur.next 值相等 cur = cur.next
        //直到不等退出循环 这时cur的值还是重复值，调整cur和pre
        if(phead == null || phead.next == null){
            return phead;
        }
        //构造头结点
        ListNode head = new ListNode(Integer.MIN_VALUE);
        head.next = phead;
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            if(cur.next != null && cur.next.val == cur.val){
                //相同结点一直前进
                while(cur.next != null && cur.val == cur.next.val){
                    cur = cur.next;
                }
                //退出循环时，cur 还是指向重复值 也要删除
                cur = cur.next;
                //pre连接新结点
                pre.next =  cur;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;

    }
}
