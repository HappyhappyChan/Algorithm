/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Lc647
 * @Description: [647. Palindromic Substrings (Medium) 回文子字符串个数]
 * @Author: [clh]
 * @Date: 2021/11/24 15:34
 **/
public class Lc647 {
    //1 github
    private int cnt = 0;
    public int countSubstrings(String s) {
        for(int i = 0; i < s.length(); i++){
            extendsubstring(s, i, i);   // 奇数长度
            extendsubstring(s, i, i+1); //偶数长度
        }
        return cnt;
    }

    private void extendsubstring(String s, int l, int r){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
            cnt++;
        }
    }

    //2 leetcode-cn
    public int countSubstrings2(String s) {
        int n = s.length(), ans = 0;
        for(int i = 0; i < 2 * n - 1; i++){
            int l = i / 2, r = i / 2 + i % 2;
            while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
                l--;
                r++;
                ans++;
            }
        }
        return ans;
    }

    //3 leetcode-cn manacher
    public int countSubstrings4(String s) {
        int n = s.length();
        StringBuffer t = new StringBuffer("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');  // 加入'$'和'!'是为了循环时避免越界

        int[] f = new int[n];
        // iMax,rMax 分别是回文中心和最远的回文右半径端点
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // 初始化 f[i]
            //min(对称位置的回文半径, 到rMax的距离)
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }

        return ans;
    }

}
