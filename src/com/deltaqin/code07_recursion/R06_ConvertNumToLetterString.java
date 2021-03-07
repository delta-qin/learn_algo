package com.deltaqin.code07_recursion;

/**
 * @author deltaqin
 * @date 2021/3/7 10:23 上午
 */

// 规定1和A对应、2和B对应、3和C对应... 那么一个数字字符串比如"111"，
// 就可以转化为"AAA"、"KA"和"AK"。 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。

// 从左往右的尝试方法，0~i-1是确定的，i 到最后是可以自由转换的，问最后有多少种，就转换为了子问题
//
public class R06_ConvertNumToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // i 之前的位置如何转换已经做过决定了
    // i 之后有多少种转换结果
    public static int process(char[] chs, int i) {

//        之前做的一个决定来到了最后的位置，就表示找到了一个有效的组合
        if (i == chs.length) {
            return 1;
        }

//      之前做的决定让现在调入了没法转的状态，所以在后续看来就是0种有效的
        // 即使前面的存在，但是切成这样后面不可能有0开头对应的字符，我们对应的是 1-26， 所以这种小段开头的全盘不成立
//        不能以这个 i 为切分，
        if (chs[i] == '0') {
            return 0;
        }

//        只要 i 位置上不是0 ，要做的决定就可以分为三种。

//        i 是 1 的话，后面的i+1（1-9） 和后面的 i+2（10-19） 都是可以去试有多少有效的
        if (chs[i] == '1') {
            // i自己作为单独的部分，后续有多少种方法
            int res = process(chs, i + 1);

            // i和i+1 共同作为单独的部分，后续从i+2有多少种方法
            if (i + 1 < chs.length) {
                res += process(chs, i + 2);
            }
            return res;
        }

//        i 是 2 的话，后面的i+1 只能取 0~6
        if (chs[i] == '2') {
            // i自己作为单独的部分，后续有多少种方法，往后做决定
            int res = process(chs, i + 1);

            // i和i+1 共同作为单独的部分，后续从i+2有多少种方法,且 不能超过26，才能往后做决定
            if (i + 1 < chs.length && (chs[i + 1] >= '0' && chs[i + 1] <= '6')) {
                res += process(chs, i + 2);
            }
            return res;
        }
        // i 是 3-9之间，没有其他决定，只有当前这一个字符去转换，后续让 i+1 去试有多少有效的
        return process(chs, i + 1);
    }

    public static void main(String[] args) {
        System.out.println(number("11111"));
    }

}

