/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz15
 * @Description: [反转链表]
 * @Author: [clh]
 * @Date: 2021/10/11 22:49
 **/
public class Jz15 {

    //solution 1: by recurion
    public ListNode solution1(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode next = head.next;
        head.next = null;
        ListNode node = solution1(next);
        next.next = head;
        return node;
    }

    //solution 2: from github
    //头插法
    //多看！
    public ListNode solution2(ListNode head){
        ListNode newlist = new ListNode(-1);
        while(head != null){
            //o-1-2-3 先标记o 2 3 然后断开2-3
            ListNode t = head.next;
            //将2插入o后面先将2-1
            head.next = newlist.next;
            //再将o-2连
            newlist.next = head;
            //变成o-2-1 3 将3变成h
            head = t;
        }
        return newlist;
    }
}
