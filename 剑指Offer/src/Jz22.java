import java.util.Stack;

/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Jz14
 * @Description: [链表中倒数最后k个结点]
 * @Author: [clh]
 * @Date: 2021/10/11 20:32
 **/
public class Jz22 {
    //solution 1 from github
    //运用两个指针 p1 p2 k和n-k的关系
    public ListNode solution1(ListNode head, int k){
        if(head == null)
            return null;
        ListNode p1 = head;
        while(p1 != null && k-- > 0){
            p1 = p1.next;
        }
        //k>0 就退出循环 说明p1 == null
        if(k > 0)
            return null;
        ListNode p2 = head;
        while(p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    //solution 2: from newcoder
    //运用栈解决
    //全部压栈 然后最上面K个结点出栈 出栈后串成链表即可
    public ListNode solution2(ListNode phead, int k){
        Stack<ListNode> stack = new Stack<>();
        //链表结点压栈
        int cnt = 0;
        while(phead != null) {
            stack.push(phead);
            phead = phead.next;
            //这里还是要数的 用来下面的判断
            cnt++;
        }

        if(cnt < k || k == 0)
            return null;
        //出栈形成新的链表
        ListNode head = stack.pop();
        //这里的--k因为已经提前弹出一个了
        while(--k > 0){
            ListNode tmp = stack.pop();
            tmp.next = head;
            head = tmp;
        }
        return head;
    }

    //solution 3: from newcoder 递归求解
    //思路跟起前面从尾到头打印链表一样
    //但这个地方好难想懂
    //这里用递归是有问题的，题目里面n的大小为0≤n≤10^5，使用递归会导致栈帧过多从而栈溢出StackOverflowError
    int cnt = 0;
    public ListNode solution3(ListNode head, int k){
        if(head == null)
            return head;
        ListNode node =  solution3(head.next, k);
        if(++cnt == k)
            return head;

        //这里return node是因为{1,2,3,4,5},6
        //如果是return head 会输出12345, return node就是{}
        return node;


    }
}
