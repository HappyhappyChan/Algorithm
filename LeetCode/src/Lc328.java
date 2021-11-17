/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc328
 * @Description: [328. Odd Even Linked List (Medium) 链表元素按奇偶聚集]
 * @Author: [clh]
 * @Date: 2021/11/17 19:53
 **/
public class Lc328 {
    //1 我自己写的
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode mark = head;
        ListNode pre = head;
        int len = 0;
        while(mark != null){
            len++;
            if(mark.next == null){
                break;
            }
            if(mark.next.next == null){
                pre = mark;
            }
            mark = mark.next;
        }
        int cnt = len / 2;
        if((len & 1) == 1){
            pre = head;
            ListNode cur = head.next;
            //原本想用cur.next != end 来判断到哪就跳出循环的
            ListNode end = mark;
            //这里的条件改成while(pre != end)是可以通过的
            while(cnt > 0){
                pre.next = cur.next;
                mark.next = cur;
                //少了这个就进入环了
                cur.next = null;
                mark = mark.next;
                pre = pre.next;
                cur = pre.next;
                cnt--;
            }
        }else{
            ListNode before = head;
            ListNode cur = head.next;
            ListNode end = mark;
            ListNode start = pre;
            //这里条件改成 while(before != start) 可以通过 而且比cnt这种判断方式空间效率更高！
            while(cnt > 1){
                before.next = cur.next;
                pre.next = cur;
                cur.next = mark;
                before = before.next;
                cur = before.next;
                pre = pre.next;
                cnt--;
            }
        }
        return head;
    }

    //2 leetcode
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}
