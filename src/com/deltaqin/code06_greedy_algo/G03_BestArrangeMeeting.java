package com.deltaqin.code06_greedy_algo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author deltaqin
 * @date 2021/3/6 9:44 下午
 */

// 安排宣讲的日程，要求会议室进行的宣讲的场次最多。 返回这个最多的宣讲场次。

// 全排列暴力枚举也可以，是一定对的，但是笔试不能过，可以使用对数器来验证策略
public class G03_BestArrangeMeeting {

    // 会议开始时间结束时间
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 根据某标准建立一个比较器来排序
    // 这里的标准可以是会议结束的时间早晚，开始的时间的早晚，持续的时间长短，具体选择需要使用对数器测试
    public static class ProgramComparator implements Comparator<Program> {

//        选择了会议结束时间的早晚
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }

    }

    // 所有项目，以及开始时间点
    public static int bestArrange(Program[] programs, int start) {

        // 按照会议的结束时间从早到晚排序
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        //
        for (int i = 0; i < programs.length; i++) {
            // 可接受的开始时间点小于等于需要安排的会议（注意结束时间从早到晚排好序了）
            if (start <= programs[i].start) {
                // 安排当前的会议
                result++;
                // 当前会议结束时间作为下一个会议的开始时间
                start = programs[i].end;
            }
        }
        return result;
    }
}
