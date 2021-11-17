/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc725
 * @Description: [725. Split Linked List in Parts 分隔链表]
 * @Author: [clh]
 * @Date: 2021/11/17 15:59
 **/
public class Lc725 {
    //我这个代码可以实现相邻的两个部分差距不大于1，但是不能实现任意两个部分差距不大于1
    //将原先的
    //if(i==0) avg += more;
    //改成了下面那样就通过了！
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ret = new ListNode[k];
        ListNode cur = head;
        int len = 0;
        while(cur != null){
            len++;
            cur = cur.next;
        }
        cur = head;
        if(k >= len){
            for(int i = 0; i < len; i++){
                ret[i] = new ListNode(cur.val);
                cur = cur.next;
            }
            return ret;
        }
        int avg = len / k;
        int more = len % k;
        ListNode start = head;
        for(int i = 0; i < k; i++){
            int cnt = avg;
            if(more > 0){
                cnt += 1;
                more--;
            }
            ListNode tmp = start;
            while(tmp != null && cnt > 1){
                tmp = tmp.next;
                cnt--;
            }
            ListNode next = tmp == null ? null : tmp.next;
            if(tmp != null){
                tmp.next = null;
                ret[i] = start;
                start = next;
            }else{
                ret[i] = start;
                break;
            }
        }
        return ret;
    }


    // 2 leetcode create new list
    public ListNode[] splitListToParts2(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }

        int width = N / k, rem = N % k;

        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = new ListNode(0), write = head;
            for (int j = 0; j < width + (i < rem ? 1 : 0); ++j) {
                write = write.next = new ListNode(cur.val);
                if (cur != null) cur = cur.next;
            }
            ans[i] = head.next;
        }
        return ans;
    }

    // 3 spilt input list
    public ListNode[] splitListToParts3(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }

        int width = N / k, rem = N % k;

        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            //这就相当于我的start
            ListNode head = cur;
            for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; ++j) {
                if (cur != null) cur = cur.next;
            }
            if (cur != null) {
                ListNode prev = cur;
                cur = cur.next;
                prev.next = null;
            }
            ans[i] = head;
        }
        return ans;
    }
}
