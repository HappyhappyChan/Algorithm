/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc24
 * @Description: [24. Swap Nodes in Pairs (Medium) 交换链表中的相邻结点]
 * @Author: [clh]
 * @Date: 2021/11/16 22:05
 **/
public class Lc24 {
    //1 我自己写的
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode phead = new ListNode(-1);
        phead.next = head;
        ListNode pre = phead, p1 = head, p2 = head.next, p3 = p2.next;
        while(p1 != null && p2 != null){
            p1.next = p3;
            p2.next = p1;
            pre.next = p2;
            //[1,2,3,4]
            //[1,2,3]
            if(p3 == null || p3.next == null)
                break;
            pre = p1;
            p2 = p3.next;
            p1 = p3;
            p3 = p2.next;
        }
        return phead.next;
    }

    //2 github
    public ListNode swapPairs2(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while(pre.next != null && pre.next.next != null){
            //将要交换的两个结点放在循环里面定义
            //循环的条件就是判断要交换的两个结点是否存在
            ListNode l1 = pre.next, l2 = pre.next.next;
            ListNode next = l2.next;
            l1.next = next;
            l2.next = l1;
            pre.next = l2;
            pre = l1;
        }
        return node.next;
    }

    //3 尝试递归
    public ListNode swapPairs3(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode newhead = head.next;
        head.next = swapPairs3(head.next.next);
        newhead.next = head;
        return newhead;
    }
}
