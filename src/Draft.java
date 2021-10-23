import java.util.*;


/**
 * @ProjectName: Coding
 * @Package: PACKAGE_NAME
 * @ClassName: Draft
 * @Description: [用来当草稿]
 * @Author: [clh]
 * @Date: 2021/10/13 16:51
 **/
public class Draft {
    public ArrayList<Integer> GetLeastNumbers_Solution1(int [] input, int k) {
        if(input == null || k > input.length)
            return new ArrayList<>();
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i : input){
            que.add(i);
//            if(i <= que.peek() && que.size() > k){
            //上面的写法没必要 因为本身poll就会弹出最大的一个 不用进行判断
            if(que.size() > k){
                que.poll();
            }
        }
        return new ArrayList<>(que);
    }

    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] nums, int k){
        if(k <= 0 || nums.length < k)
            return new ArrayList<>();
        //找到最小的k个数 这里的k-1是表示找到k-1位置 即第k个
        findSmallestK(nums, k - 1);
        ArrayList<Integer> ret = new ArrayList<>();
        //因为前面已经遍历好了 数组是有序的，直接取即可
        for(int i = 0; i < k; i++){
            ret.add(nums[i]);
        }
        return ret;
    }

    private void findSmallestK(int[] nums, int k){
        int l = 0;
        int h = nums.length - 1;
        int p = l;
        while(l < h){
            int j = partition(nums, l, h);
            if (j == k)
                break;
            if (j > k)
                h = j - 1;
            else
                l = j + 1;
        }
    }

    private int partition(int[] nums, int l, int h) {
        int p = nums[l];
        int i = l;
        int j = h + 1;
        while(true){
            while(i != h && nums[++i] < p);
            while(j != l && nums[--j] > p);
            if(i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;

    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void sort(int[] arr, int left, int right){
        if(left > right)
            return;
        int base = arr[left];
        int i = left;
        int j = right;
        while(i != j){
            while(arr[j] >= base && j > i){
                j--;
            }
            while(arr[i] <= base && i < j){
                i++;
            }
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        arr[left] = arr[i];
        arr[i] = base;
        sort(arr, left, i - 1);
        sort(arr, i + 1, right);
    }

    //63 数据流中的中位数

    //大顶堆
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    //小顶堆
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    //用来计数
    int cnt = 0;
    public void Insert(Integer num) {
        if(cnt % 2 == 1){
            left.add(num);
            right.add(left.poll());
        }else{
            right.add(num);
            left.add(right.poll());
        }
        cnt++;
    }

    public Double GetMedian() {
        /**
         * 这种写法也可以
        int cnt1 = left.size();
        int cnt2 = right.size();
        if(cnt1 == cnt2){
            return (left.peek() + right.peek())/2.0;
        }else if(cnt2 > cnt1){
            return (double)right.peek();
        }else{
            return (double)left.peek();
        }
         */

        //这样也ok
        if(cnt % 2 == 1)
            return (double)left.peek();
        else
            return (left.peek() + right.peek())/2.0;
    }

    //75 字符流中第一个不重复的字符
    //在文件里面写 不在draft里面写

    //57 和为S的两个数字
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> ret = new ArrayList<>();
        //用一个数记录当前的和
        int tmp = 0;
        //左指针
        int left = 0;
        //右指针
        int right = array.length - 1;
        while(left < right){
            tmp = array[left] + array[right];
            if(tmp < sum){
                left++;
                continue;
            }
            if(tmp > sum){
                right--;
                continue;
            }
            if(tmp == sum){
                ret.add(array[left]);
                ret.add(array[right]);
                break;
            }
        }
        return ret;
    }

    //74 和为S的连续正数序列
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        //用于存储所有满足条件的序列
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1;
        int end = 2;
        int tmp = 3;

        while(start < end && end < sum){
            if(tmp < sum){
                end++;
                tmp += end;
            }

            if(tmp > sum){
                tmp -= start;
                start++;
            }
            if(tmp == sum){
                //用来存储找到的序列
                ArrayList<Integer> ans = new ArrayList<>();
                for(int i = start; i <= end; i++){
                    ans.add(i);
                }
                ret.add(ans);
                start++;
                end = start + 1;
                tmp = start + end;
            }
        }
        return ret;
    }

    //73 翻转单词序列
    //solution 1 运用split吧 然后直接重新拼接
    public String ReverseSentence1(String str) {
        StringBuilder ret = new StringBuilder();
        String[] arr = str.split(" ");
        for(int i = arr.length - 1; i > 0; i--){
            ret.append(arr[i]+" ");
        }
        ret.append(arr[0]);
        return ret.toString();
    }

    //solution 2 翻转整个句子再翻转每个单词
    public String ReverseSentence2(String str){
        char[] arr = str.toCharArray();

        //翻转整个句子
        for(int i = 0, j = arr.length - 1; i < j; i++, j--){
            swap2(arr, i, j);
        }

        //翻转单词
        int left = 0;
        int right = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == ' '){
                right = i;
                if(left == 0){
                    swap3(arr, left, right-1);
                }
                else{
                    swap3(arr, left + 1, right-1);
                }
                left = right;
            }
        }
        //这里犯了一个很蠢的错误 只是交换了两个字母 没有交换整个单词
        //swap2(arr, left, arr.length - 1);
        //全部改成swap3
        swap3(arr, left + 1, arr.length - 1);
        return new String(arr);
    }

    private void swap3(char[] arr, int left, int right) {
        //防止left对应的不是空格而是首字母
        if(left == 1) left--;
        while(left < right){
            swap2(arr, left, right);
            left++;
            right--;
        }
    }

    private void swap2(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 58 左旋转字符串 对应文件Jz43
    public String LeftRotateString(String str,int k) {
        int n = str.length();
        if(k > n)
            return "";
        //用队列存放需要左移的部分
        Queue<Character> que = new LinkedList<Character>();
        char[] arr = str.toCharArray();
        for(int i = 0; i < n; i++){
            if(i < k){
                que.add(arr[i]);
            }else{
                arr[i - k] = arr[i];
            }
        }
        for(int i = n - k; i < n; i++){
            arr[i] = que.poll();
        }
        return new String(arr);
    }

    //6 从尾到头打印链表  运用递归
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(listNode.next != null){
            ret.addAll(printListFromTailToHead(listNode.next));
            ret.add(listNode.val);
        }
        return ret;

    }

    //6 运用头结点插入
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ListNode head = new ListNode(-1);
        while(listNode != null){
            //o-1-2-3
            ListNode tmp = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode  = tmp;
        }
        ArrayList<Integer> ret = new ArrayList<Integer>();
        head = head.next;
        while(head != null){
            ret.add(head.val);
            head = head.next;
        }
        return ret;
    }

    //6 使用栈
    public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<>();
        while(listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()){
            ret.add(stack.pop());
        }
        return ret;
    }

    //18.1 在 O(1) 时间内删除链表节点
    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if(head == null || tobeDelete == null)
            return null;
        ListNode cur = head;
        if(cur == tobeDelete)
            return head.next;
        while(cur != null){
            if(cur.next == tobeDelete){
                ListNode tmp = tobeDelete.next;
                cur.next = tmp;
                break;
            }
            cur = cur.next;
        }
        return head;
    }
    //18.2 删除链表中重复的结点 JZ76
    //我自己写的！ 通过了
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode head = new ListNode(-1);
        head.next = pHead;
        ListNode pre = head;
        ListNode cur = pre.next;
        while(cur != null){
            if(cur.next != null && cur.val == cur.next.val){
                int tmp = cur.val;
                while(cur.next != null && cur.next.val == tmp){
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
                continue;
            }
            pre = cur;
            cur = cur.next;
        }
        return head.next;
    }

    //22 Jz22  * @Description: [链表中倒数最后k个结点]
    public ListNode FindKthToTail (ListNode pHead, int k) {
        if(k < 1)
            return null;
        //一个走K步的指针
        int c1 = 0;
        //创建头结点
        ListNode head = new ListNode(-1);
        ListNode n1 = head;
        while(n1 != null && c1 < k){
            c1++;
            n1 = n1.next;
        }
        if(n1 == null)
            return null;
        //另一个指针
        ListNode n2 = pHead;
        while(n1 != null){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n2;

    }

    //用栈解决
    public ListNode FindKthToTail2 (ListNode pHead, int k){
        if(k < 1)
            return null;
        Stack<Integer> s = new Stack<>();
        ListNode cur = pHead;
        while(cur != null){
            s.push(cur.val);
            cur = cur.next;
        }
        int cnt = 0;
        ListNode head = new ListNode(-1);
        ListNode pre;
        while(cnt < k && !s.isEmpty()){
            ListNode tmp = new ListNode(s.pop());
            tmp.next = head.next;
            head.next = tmp;
            cnt++;
        }
        if(cnt < k)
            return null;
        return head.next;
    }

    //23 Jz23 链表中环的入口结点
   //利用双指针 我自己写的
    public ListNode EntryNodeOfLoop(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
//        if(slow == null || fast.next == null)
//            return null;
        while(slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        //因为有可能不是slow==fast才退出的 要判断是否有环
        if(slow == null || fast == null || fast.next == null)
            return null;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        if(slow == fast)
            return slow;
        return null;
    }

    //24 Jz24 反转链表
    //利用头结点插入法
    public ListNode ReverseList(ListNode head) {
        ListNode phead = new ListNode(-1);
        //下面这个不能加 要不然就陷入死循环
//        phead.next = head;
        ListNode cur = head;
        ListNode tmp;
        while(cur != null){
            tmp = cur.next;
            cur.next = phead.next;
            phead.next = cur;
            cur = tmp;
        }
        return phead.next;
    }

    //利用递归
    public ListNode ReverseList2(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode post = head.next;
        ListNode tmp = ReverseList2(post);
        head.next = null;
        post.next = head;
        return tmp;
    }

    // 25 Jz25 合并两个排序的链表
    //运用递归
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        if(list1.val <= list2.val){
            list1.next = Merge(list1.next, list2);
            return list1;
        }else{
            list2.next = Merge(list2.next, list1);
            return list2;
        }
    }

    public ListNode Merge2(ListNode list1,ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(cur != null && (list1 != null || list2 != null)){
            if(list1 == null && list2 != null){
                cur.next = list2;
                list2 = list2.next;
                cur = cur.next;
                continue;
            }
            if(list2 == null && list1 != null){
                cur.next = list1;
                list1 = list1.next;
                cur = cur.next;
                continue;
            }
            if(list1.val <= list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        return head.next;
    }

    //35 Jz35 复杂链表的复制
    //下面代码有误
    //放弃了 看解析抄的下面的
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null)
            return null;
        RandomListNode cur = pHead;
        //复制链表
        while(cur != null){
            RandomListNode curcp = new RandomListNode(cur.label);
            RandomListNode curnext = cur.next;
            curcp.next = curnext;
            cur.next = curcp;
            cur = curnext;
        }

        //创建结点联系
        cur = pHead;
        while(cur != null){
            cur.next.random = cur.random == null? null : cur.random.next;
            cur = cur.next.next;
        }

        //拆分
        cur = pHead;
        RandomListNode ch = pHead.next;
        while(cur != null){
            RandomListNode cp = cur.next;
            cur.next = cp.next;
            cp.next = cp.next == null ? null : cp.next.next;
            cur = cur.next;
        }
        return ch;
    }

    //52 jz52 两个链表的第一个公共结点
    //我自己写的 通过了！
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Set<ListNode> set = new HashSet<ListNode>();
        while(pHead1 != null || pHead2 != null){
            if(pHead1 == pHead2)
                return pHead1;
            if(pHead1 != null){
                if(set.contains(pHead1)){
                    return pHead1;
                }else{
                    set.add(pHead1);
                }
                pHead1 = pHead1.next;
            }
            if(pHead2 != null){
                if(set.contains(pHead2)){
                    return pHead2;
                }else{
                    set.add(pHead2);
                }
                pHead2 = pHead2.next;
            }
        }
        return null;

    }

    //solution 2 from github
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }

    //7 jz7 重建二叉树
    //我自己写的！通过了
    public TreeNode reConstructBinaryTree(int [] pre,int [] vin) {
        int n = vin.length;
        if(n ==  0)
            return null;
        if(pre.length == 0)
            return null;
        TreeNode root = new TreeNode(pre[0]);
        int rt = 0;
        for(int i = 0; i < n; i++){
            if(vin[i] == pre[0]){
                rt = i;
                break;
            }
        }
        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, rt+1), Arrays.copyOfRange(vin, 0, rt));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, rt+1, n), Arrays.copyOfRange(vin, rt+1, n));
        return root;
    }

    //8 jz8 二叉树的下一个结点
    //我自己写的!
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if(pNode.right != null){
            TreeLinkNode tmp = pNode.right;
            while(tmp.left != null){
                tmp = tmp.left;
            }
            return tmp;
        }
        TreeLinkNode par = pNode.next;
        TreeLinkNode tmp = pNode;
        while(par != null){
            if(par.left == tmp)
                return par;
            if(par.right == tmp){
                tmp = par;
                par = par.next;
            }
        }
        return null;

    }

    //26 jz26 树的子结构
    //下面是我写的，理解错了，子结构和子树不是同一个东西，我写的是子树
    //题目问的是子结构
    //{8,8,7,9,2,#,#,#,#,4,7},{8,9,2} 这种情况应该是true的
    //不仅理解错了 而且tmp那里也错了
    //看解析吧
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root2 == null)
            return false;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        if(root1 == null)
            return false;
        que.add(root1);
        while(!que.isEmpty()){
            //这个时候取出的tmp只是val和当初add进去的一样，左右子树都没了
            TreeNode tmp = que.poll();
            if(tmp == root2){
                return true;
            }
            if(tmp.left != null){
                que.add(tmp.left);
            }
            if(tmp.right != null){
                que.add(tmp.right);
            }
        }
        return false;

    }

    //27 jz27 二叉树镜像
    //yeah 自己写的
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if(pRoot == null)
            return null;
        if(pRoot.left != null || pRoot.right != null){
            TreeNode tmp= pRoot.left;
            pRoot.left = pRoot.right;
            pRoot.right = tmp;
        }
        pRoot.left = Mirror(pRoot.left);
        pRoot.right = Mirror(pRoot.right);
        return pRoot;
    }

    //28 jz28 对称的二叉树
    //我自己写的 对了！
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null)
            return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode l, TreeNode r){
        if(l == null && r == null)
            return true;
        if(l == null && r != null)
            return false;
        if(l != null && r == null)
            return false;
        if(l.val != r.val)
            return false;
        return isSymmetrical(l.left, r.right) && isSymmetrical(l.right, r.left);
    }

    //32 jz32 从上往下打印二叉树
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        if(root == null)
            return ret;
        que.add(root);
        while(!que.isEmpty()){
            TreeNode t = que.poll();
            ret.add(t.val);
            if(t.left != null)
                que.add(t.left);
            if(t.right != null)
                que.add(t.right);
        }
        return ret;
    }

    //32.2 jz78 把二叉树打印成多行
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        if(pRoot == null) {
            return ret;
        }
        que.add(pRoot);
        while(!que.isEmpty()){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            int cnt = que.size();
            while(cnt>0){
                TreeNode node = que.poll();
                if(node == null){
                    cnt--;
                    continue;
                }
                tmp.add(node.val);
                que.add(node.left);
                que.add(node.right);
                cnt--;
            }
            if(tmp.size() != 0)
                ret.add(tmp);
        }
        return ret;
    }

    //32.3 jz77 按之字形顺序打印二叉树
    //我想着是按前面的打印多行 + list.add(index,ele)操作
    public ArrayList<ArrayList<Integer> > PrintZhi(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > ret = new ArrayList<>();
        if(pRoot == null)
            return ret;
        Queue<TreeNode> que = new LinkedList<>();
        que.add(pRoot);
        boolean flag = false;
        while(!que.isEmpty()){
            ArrayList<Integer> tmp = new ArrayList<>();
            int cnt = que.size();
            flag = !flag;
            while(cnt-->0){
                TreeNode t = que.poll();
                if(t == null)
                    continue;
                que.add(t.left);
                que.add(t.right);
                if(flag == true){
                    tmp.add(t.val);
                }else{
                    tmp.add(0, t.val);
                }
            }
            if(tmp.size() > 0)
                ret.add(tmp);
        }
        return ret;
    }

    //33 jz33 二叉搜索树的后序遍历序列
    //还是有bug
    public boolean VerifySquenceOfBST(int [] sequence) {
        //这里其实应该返回true，但是这样的话就不能判断空树不是二叉搜索树了
        if(sequence.length == 0)
            return false;
        int n = sequence.length;
        if(n == 1)
            return true;
        int left = -1, right = n - 2;
        int root = sequence[n-1];
        for(int i = 0; i < n; i++){
            if(sequence[i] >= root){
                left = i - 1;
                break;
            }
        }
        /**
        //只有右子树
        if(left < 0){
            for(int i = 0; i < n; i++){
                if(sequence[i] < root){
                    return false;
                }
            }
            return true;
        }
        //只有左子树
        if(right < left){
            for(int i = 0; i < n; i++){
                if(sequence[i] > root){
                    return false;
                }
            }
            return true;
        }

        //左右子树都有
        for(int i = 0; i < n; i++){
            if(sequence[i] > root && i <=left)
                return false;
            if(sequence[i] < root && i > left)
                return false;
        }
        */
        if(left >= 0){
            if(sequence[left] > root)
                return false;
        }
        if(right > left && sequence[right] < root)
            return false;
        return VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, left+1)) &&
                VerifySquenceOfBST(Arrays.copyOfRange(sequence, left+1, n));

    }

    //34 jz34 二叉树中和为某一值的路径
    //下面是我写的 乱七八糟……
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int expectNumber) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if(root == null)
            return ret;
        int cnt = 0;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode tmp = stack.peek();
            cnt += tmp.val;
            ArrayList<Integer> tlist = new ArrayList<>();
            if(cnt == expectNumber && tmp.right == null && tmp.left == null){
                while(!stack.isEmpty()){
                    int v = stack.pop().val;
                    tlist.add(v);
                    cnt -= v;
                }
                ret.add(tlist);
            }
            if(cnt > expectNumber){
                int v = stack.pop().val;
                cnt -= v;
                tmp = stack.peek();
            }
            while(tmp != null){
                if(tmp.left != null){
                    cnt += tmp.left.val;
                    stack.push(tmp.left);
                }else{
                    cnt += tmp.right.val;
                    stack.push(tmp.right);
                }
                if(cnt == expectNumber){
                    while(!stack.isEmpty()){
                        int v = stack.pop().val;
                        tlist.add(v);
                        cnt -= v;
                    }
                    ret.add(tlist);
                }
                if(cnt > expectNumber){
                    int v = stack.pop().val;
                    cnt -= v;
                    tmp = stack.peek();
                }
                if(cnt < expectNumber){

                }
            }
        }
        return ret;
    }

    //36 jz36 二叉搜索树与双向链表
    //这个好难啊
//    public TreeNode Convert(TreeNode pRootOfTree) {
//        return null;
//    }

    //37 jz37 序列化二叉树
    String Serialize(TreeNode root) {
        if(root == null)
            return "#";
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }

    //54 jz54 二叉搜索树的第k个结点
    //看我的jz54文件

    //55.1 55 二叉树的深度
    public int TreeDepth(TreeNode root) {
        return root == null? 0 : 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }

    //55.2 jz79 平衡二叉树
    //我自己写的 终于对了！
    private boolean balanceFlag = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null)
            return true;
        balanceDepth(root);
        return balanceFlag;
    }

    private int balanceDepth(TreeNode node) {
        if(node == null){
            return 0;
        }
        int l = balanceDepth(node.left);
        int r = balanceDepth(node.right);
        if( Math.abs(l-r) > 1){
            balanceFlag = false;
        }

        return 1 + Math.max(l, r);
    }

    //68.1 二叉查找树两个节点的最低公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root ==  null)
            return root;
        if(root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        if(root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }
    //68.2 普通二叉树两个节点的最低公共祖先

}
