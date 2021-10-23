/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz36
 * @Description: [两个链表的第一个公共结点]
 * @Author: [clh]
 * @Date: 2021/10/12 10:37
 **/
public class Jz52 {
    //无环单向链表
    //solution 1 from github
    //设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，
    // 可知 a + c + b = b + c + a。
    public ListNode solution1(ListNode ph1, ListNode ph2){
        ListNode l1 = ph1;
        ListNode l2 = ph2;
        while(l1 != l2){
    //当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B
            l1 = (l1 == null)? ph2 : l1.next;
    //当访问链表 B 的指针访问到链表尾部时，令它从链表 A 的头部重新开始访问链表 A
            l2 = (l2 == null)? ph1 : l2.next;
        }
        return l1;
    }
}
