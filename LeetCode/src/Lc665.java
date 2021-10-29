/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc665
 * @Description: [665. Non-decreasing Array 修改一个数成为非递减数组]
 * @Author: [clh]
 * @Date: 2021/10/29 9:14
 **/
public class Lc665 {
    //不管了 先尝试用n[i]<=n[i+1]判断
    //还是有误
    public boolean checkPossibility(int[] nums) {
        int[] visited = new int[nums.length]; //0表示没看过
        boolean flag = true;
        int cnt = 1;
        int ind0 = 0;
        for(int i = 0; i < nums.length - 1; i++){
            if(cnt < 0) return false;
            if(visited[i] == 1) continue;
            ind0 = i;
            int next = i + 1;
            while(visited[next] == 1){
                next++;
            }
            if(visited[i] == 0 &&nums[ind0] > nums[next] ){
                visited[next] = 1; // 3 4 2 3 删除后是3和4比 不是3和2比
                cnt--;
                i--; //因为删完之后还是4和3比 3用next记录
            }
        }
        return (cnt < 0 )? false : true;
    }

    //2 删除的时候可以删除本身或者是下一个 然后判断是否符合
    //我自己写的 搞了半天终于通过了！！！感激涕零
    public boolean checkPossibility2(int[] nums){
        int cnt = 1;
        int[] del = new int[nums.length]; //记录是否删除
        for(int i = 0; i < nums.length - 1; i++){
            if(del[i] == 1)
                continue;
            if(nums[i] > nums[i+1]){
                cnt--;
                if(cnt < 0) return false;
                //删除当前结点
                boolean f1 = judge(nums, i);
                //删除后一个结点
                boolean f2 = judge(nums, i+1);
                if(f1){
                    del[i] = 1;
                }else if(f2){
                    del[i+1] = 1;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    private boolean judge(int[] nums, int x) {
        if(x == nums.length - 1)
            return true;
        for(int i = 0; i < nums.length - 1; i++){
            if(i < x - 1 && nums[i] > nums[i+1]){
                return false;
            }
            if(i > x && nums[i] > nums[i+1])
                return false;
            if(i == x-1){
                if(nums[i] > nums[x+1])
                    return false;
                i++;
            }
        }
        return true;
    }

    //3 我的优化
    //也通过了！！！
    public boolean checkPossibility3(int[] nums){
        int cnt = 1;
        int[] del = new int[nums.length]; //记录是否删除
        for(int i = 0; i < nums.length - 1; i++){
            if(del[i] == 1)
                continue;
            if(nums[i] > nums[i+1]){
                cnt--;
                if(cnt < 0) return false;
                //删除当前结点
                boolean f1 = judge(nums, i);
                //删除后一个结点
                boolean f2 = judge(nums, i+1);
                return f1 || f2; //因为judge里面已经判断了
            }
        }
        return true;
    }

    //4 github
    public boolean checkPossibility4(int[] nums){
        int cnt = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] >= nums[i - 1])
                continue;
            cnt++;
            // 3 4 2 5 --> 3 4 4 5
            if(i - 2 >= 0 && nums[i - 2] > nums[i]){
                nums[i] = nums[i - 1]; //让nums[i]变大
            }else{
                // 1 4 2 3 --> 1 2 2 3
                //如果将2变成4 就有可能大于i+1 影响后面 所以尽量不要改后面
                nums[i - 1] = nums[i];
            }
        }
        return cnt <= 1;
    }

    //5 leetcode-cn
    public boolean checkPossibility5(int[] nums) {
        if (nums.length == 1) return true;
        boolean flag = nums[0] <= nums[1] ? true : false;
        for(int i = 1; i < nums.length-1; i++) {
            if(nums[i] > nums[i+1]) {
                if(flag) {
                    if(nums[i+1] >= nums[i-1]) {
                        nums[i] = nums[i+1];
                    } else {
                        nums[i+1] = nums[i];
                    }
                    flag = false;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}
