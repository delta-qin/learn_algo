package com.deltaqin.code00_test;

import java.util.HashMap;

/**
 * @author deltaqin
 * @date 2021/3/22 5:22 下午
 */
public class TwoSum {
        public static int[] twoSum(int[] nums, int target) {

            HashMap<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }

            for(int j = 0; j < nums.length/2+1; j++) {
                Integer tmp = target - nums[j];
                if(map.containsKey(tmp) && map.get(tmp) != j) {
                    return new int[]{j, map.get(tmp)};
                }
            }

            return null;
        }

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{3, 2, 4}, 6);
        //System.out.println(ints);
    }
}
