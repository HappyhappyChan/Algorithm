import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz45
 * @Description: [把数组排成最小的数]
 * @Author: [clh]
 * @Date: 2021/10/23 20:26
 **/
public class Jz45 {
    //solution nowcoder 和github也是一样
    public String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length == 0)
            return "";
        int n = numbers.length;
        String[] nums = new String[n];
        //先把整型数组转化为字符串型数组
        for(int i = 0; i < n; i++){
            nums[i] = numbers[i] + "";
        }
        //用定义的排序规则对字符串型数组进行排序
        //这个语法没看懂
        Arrays.sort(nums, (s1, s2)->{
            return (s1 + s2).compareTo(s2 + s1);
        });
        StringBuffer sb = new StringBuffer();
        //将字符串型数组中的每个元素拼接起来
        for(String num: nums){
            sb.append(num);
        }
        return sb.toString();
    }

    //solution 2 bilibili 道理和github一样 语法有点差别
    public String solution2(int[] numbers){
        ArrayList<String> ans = new ArrayList<String>();
        for(int k : numbers){
            ans.add(k+"");
        }
        //new Comparator<String>() 可以替换成lamda表达式
        //替换的lamda表达式为
        //(o1, o2) ->{String a1=o1+o2; String a2=o2+o1;return a1.compareTo(a2);});
        ans.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String a1 = o1 + o2;
                String a2 = o2 + o1;
                return a1.compareTo(a2);
            }
        });
//        return ans.toString(); 直接这样是错的 会返回"[123]"
        StringBuffer sb = new StringBuffer();
        for(String x: ans){
            sb.append(x);
        }
        return sb.toString();
    }
}
