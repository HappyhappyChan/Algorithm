import java.util.HashSet;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc217
 * @Description: [217. Contains Duplicate (Easy) 判断数组是否含有重复元素]
 * @Author: [clh]
 * @Date: 2021/11/23 16:34
 **/
public class Lc217 {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0)
            return false;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int x: nums){
            if(set.contains(x)){
                return true;
            }
            set.add(x);
        }
        return false;
    }

    //2 github
    //效率远不如我写的
    public boolean containsDuplicate2(int[] nums) {
        if(nums == null || nums.length == 0)
            return false;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int x: nums){
            set.add(x);
        }
        return set.size() < nums.length;
    }

}
