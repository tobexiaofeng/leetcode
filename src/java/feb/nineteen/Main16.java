package src.java.feb.nineteen;

import java.util.Arrays;

/**
 * 这道题乍一看双指针即可解决，实际不太好固定两个指针，因为第三个数，不一定在数组中存在
 * 做这题两次错误：
 * 1、min 的初始值赋值错误，min = target
 * 2、双指针外面循环，首尾相加等于0的时候，如果中间存在两个值和target值距离同，错误用例：{4,0,5,-5,3,3,0,-4,-5}
 * <p>
 * <p>
 * 看了labuladong的思路，先固定一个数，然后两个数双指针。我开始的错误思路是，先固定两个数；
 * 就算固定了一个数，后面双指针的循环，也得留意指针移动的条件，是根据三数之和与target的大小，而不是res与target的大小
 */
public class Main16 {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        // 最小间隔数
        int min = Integer.MAX_VALUE;
        int res = target;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0, k = nums.length - 1; j < k; ) {
                if (i == j) {
                    j++;
                }
                if (i == k) {
                    k--;
                }
                if (j == k) {
                    continue;
                }
                int temp = nums[i] + nums[j] + nums[k];
                if (Math.abs(temp - target) < min) {
                    min = Math.abs(temp - target);
                    res = temp;
                }
                // 左右移动的值，应该根据temp，而不是res
                if (temp < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }

    /**
     * 第二次做题的错误思路
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        // 最小间隔数，开始取最大值，因为第一组值是有效值，然后再去找最优解
        int min = Integer.MAX_VALUE;
        int res = target;
        for (int i = 0, j = nums.length - 1; i < j; ) {
            int startAndEnd = nums[i] + nums[j];
            for (int k = i + 1; k < j; k++) {
                int temp = nums[k] + startAndEnd;
                if (Math.abs(temp - target) < min) {
                    res = temp;
                    min = Math.abs(temp - target);
                }
            }
            // 错误在此处，开始写的res < target。但是修改错误之后，依旧无法通过全部用例，思路还是有问题
            if (startAndEnd > target) {
                j--;
            } else {
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 0, 5, -5, 3, 3, 0, -4, -5};
        System.out.println(threeSumClosest(nums, -2));
    }
}
