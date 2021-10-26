/**
 * @ProjectName: Algorithm
 * @Package: PACKAGE_NAME
 * @ClassName: Jz1902
 * @Description: [19 正则表达式匹配]
 * @Author: [clh]
 * @Date: 2021/10/25 20:57
 **/
public class Jz1902 {
    // 1 nowcoder 递归
    public boolean match (String str, String pattern) {
        // write code here
        //若s p都空 一定匹配
        if(str == "" && pattern == "")
            return true;
        else if(str != "" && pattern == "")
            return false;
        else if(str == "" && pattern != ""){
            //如果p长度大于2，并且p[1]是个*，才能符合上面可以变成空串的形式
            if(pattern.length() >= 2 && pattern.charAt(0) == '*'){
                //继续递归检查p[2:]后面的是否符合；
                return match(str, pattern.substring(2));
            }else{
                return false;
            }
        }

        if(pattern.length() >= 2 && pattern.charAt(1) == '*') {
            // 第一个元素匹配成功，有三种继续进行的选择，都要尝试一下；
            if (str.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.') {
                // 第一种是为了防止 s="bbba", p=".*a"这种情况，这个我们一看就是匹配的；
                // 但是它有可能一个".*"就把"bbba"都匹配掉了，p剩下个"a"，会认为匹配失败。
                // 第二种是匹配成功，并且s、p都移动；
                // 第三种是，s移动，p的*留着。
                return match(str, pattern.substring(2)) ||
                        match(str.substring(1), pattern.substring(2)) ||
                        match(str.substring(1), pattern);
            } else {
                //如果第一个元素匹配失败，p后移2个，相当于(*=0)
                return match(str, pattern.substring(2));
            }
        }
       //没有*的情况，直接匹配第一个元素看是否成功；
        else if((str.charAt(0) == pattern.charAt(0)) || pattern.charAt(0) == '.'){
            return match(str.substring(1), pattern.substring(1));
        } else{
            return false;
        }

    }


    //不知道为什么上面报错
    //字符串比较不能用== 要用.equals()
    //终于通过了
    public boolean match2 (String str, String pattern) {
        // write code here
        //若s p都空 一定匹配
        if(str.equals("") && pattern.equals(""))
            return true;
        else if(!str.equals("") && pattern.equals(""))
            return false;
        else if(str.equals("") && !pattern.equals("")){
            //如果p长度大于2，并且p[1]是个*，才能符合上面可以变成空串的形式
            if(pattern.length() >= 2 && pattern.charAt(1) == '*'){
                //继续递归检查p[2:]后面的是否符合；
                return match(str, pattern.substring(2));
            }else{
                return false;
            }
        }

        if(pattern.length() >= 2 && pattern.charAt(1) == '*') {
            // 第一个元素匹配成功，有三种继续进行的选择，都要尝试一下；
            if (str.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.') {
                // 第一种是为了防止 s="bbba", p=".*a"这种情况，这个我们一看就是匹配的；
                // 但是它有可能一个".*"就把"bbba"都匹配掉了，p剩下个"a"，会认为匹配失败。
                // 第二种是匹配成功，并且s、p都移动；
                // 第三种是，s移动，p的*留着。
                return match(str, pattern.substring(2)) ||
                        match(str.substring(1), pattern.substring(2)) ||
                        match(str.substring(1), pattern);
            } else {
                //如果第一个元素匹配失败，p后移2个，相当于(*=0)
                return match(str, pattern.substring(2));
            }
        }
        //没有*的情况，直接匹配第一个元素看是否成功；
        else if((str.charAt(0) == pattern.charAt(0)) || pattern.charAt(0) == '.'){
            return match(str.substring(1), pattern.substring(1));
        } else{
            return false;
        }

    }

    // 3 dp nowcoder
    public boolean match3 (String str, String pattern) {
        // write code here
        int n = str.length();
        int m = pattern.length();
        boolean[][] f = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //分成空正则和非空正则两种
                if (j == 0) {
                    f[i][j] = i == 0;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    if (pattern.charAt(j - 1) != '*') {
                        if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        //碰到 * 了，分为看和不看两种情况
                        //不看
                        if (j >= 2) {
                            f[i][j] |= f[i][j - 2];
                        }
                        //看
                        if (i >= 1 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }


    // 4 dp leetcode-cn
    public boolean isMatch(String s, String p) {

        boolean table[][] = new boolean[s.length() + 1][p.length() + 1];

        table[0][0] = true;

        for (int col=1; col<table[0].length; col++) {
            char ch = p.charAt(col-1);
            if (col > 1) {
                if (ch == '*') {
                    table[0][col] = table[0][col-2];
                } else {
                    table[0][col] = false;
                }
            } else {
                if (ch == '*') {
                    table[0][col] = true;
                }
            }
        }

        for (int row=1; row<table.length; row++) {
            char ch1 = s.charAt(row-1);
            for (int col=1; col<table[row].length; col++) {
                char ch2 = p.charAt(col-1);
                if (ch1==ch2 || ch2 == '.') {
                    table[row][col] = table[row-1][col-1];
                } else if (ch2 == '*') {
                    if(col > 1) {
                        if (table[row][col-2]) {
                            table[row][col] = true; // * 前面的字符出现0次
                        } else {
                            char prev = p.charAt(col-2);
                            if (prev== ch1 || prev == '.') {
                                table[row][col] = table[row - 1][col]; // * 前面的字符出现多次
                            }
                        }

                    }
                }
            }
        }


        boolean lastRow[] = table[table.length-1];
        return lastRow[lastRow.length-1];
    }
}
