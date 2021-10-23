/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz16
 * @Description: [合并两个排序的链表]
 * @Author: [clh]
 * @Date: 2021/10/12 9:57
 **/
public class Jz25 {
    //输入2个递增的链表
    //solution 1: from github 递归
    public ListNode solution1(ListNode l1, ListNode l2){
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        if(l1.val <= l2.val){
            //l1: 1 l2: 2
            l1.next = solution1(l1.next, l2);
            return l1;
        }else{
            //l1: 1-3 l2: 0-4
            //l2.next = (4,1-3)
            //(4, 1-3) 4 > 1 ----> 1-(3,4)
            //(3,4) 4>3 --> 3->4
            //1->3->4
            //0->1->3->4
            l2.next = solution1(l1, l2.next);
            return l2;
        }
    }

    //solution 2: from github 用迭代
    public ListNode solution2(ListNode l1, ListNode l2){
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if(l1 != null)
            cur.next = l1;
        if(l2 != null)
            cur.next = l2;
        return head.next;
    }
}
