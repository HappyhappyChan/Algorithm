/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc744
 * @Description: [744. Find Smallest Letter Greater Than Target 大于给定元素的最小元素]
 * @Author: [clh]
 * @Date: 2021/10/29 19:41
 **/
public class Lc744 {
    //1 brute force
    public char nextGreatestLetter(char[] letters, char target) {
        //因为有说letter数组至少包含2个不同的数字，而且是按照非递减顺序排列
        for(char x : letters){
            //a < c
            if(x > target)
                return x;
        }
        //如果找不到就返回第一个字符
        return letters[0];
    }

    //2 二分查找
    public char nextGreatestLetter2(char[] letters, char target){
        int l = 0;
        int h = letters.length - 1;
        int mid = l + (h - l)/2;
        while(l < h){
            mid = l + (h - l)/2;
            if(letters[mid] > target){
                h = mid;
            }else if(letters[mid] <= target){
                l = mid + 1;
            }
        }
        if(letters[l] > target) return letters[l];
        return letters[0];
    }

    //3 github
    public char nextGreatestLetter3(char[] letters, char target) {
        int n = letters.length;
        int l = 0, h = n - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (letters[m] <= target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l < n ? letters[l] : letters[0];
    }

    //4 leetcode
    public char nextGreatestLetter4(char[] letters, char target) {
        boolean[] seen = new boolean[26];
        for (char c: letters)
            seen[c - 'a'] = true;

        while (true) {
            target++;
            if (target > 'z') target = 'a';
            if (seen[target - 'a']) return target;
        }
    }
}
