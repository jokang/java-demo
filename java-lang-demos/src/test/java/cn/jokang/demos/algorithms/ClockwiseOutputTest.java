package cn.jokang.demos.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoukang
 * @date 2020/9/8
 */
public class ClockwiseOutputTest {

    @Test
    public void test() {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
//         int[][] a={{1,2,3,4,5},{6, 7, 8, 9, 10},{11, 12, 13, 14, 15},{16, 17, 18, 19, 20}};
//        int[][] a = {{1, 2}, {3, 4}};
        int[][] a = {{1}, {2}};
//        List<Integer> res = ClockwiseOutputTest.printLuoxuan(a);
//        System.out.println(res);

        method2(a);
    }

    private static List<Integer> printLuoxuan(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length == 0) {
            return new ArrayList<>();
        }
        int m = nums.length;
        int n = nums[0].length;
        ArrayList<Integer> res = new ArrayList<>();
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int a = 0;
        int b = 0;
        boolean[][] used = new boolean[m][n];
        for (int i = 0, j = 0, k = 0, p = 0; i < m * n; i++) {
            res.add(nums[j][k]);
            used[j][k] = true;
            a = j + dx[p];
            b = k + dy[p];
            if (a >= m || a < 0 || b >= n || b < 0 || used[a][b] == true) {
                p = (p + 1) % 4;
                a = j + dx[p];
                b = k + dy[p];
            }
            j = a;
            k = b;
        }
        return res;
    }

    public static void method2(int[][] nums){
        if(nums == null || nums.length == 0) return;
        int n = nums.length, m = nums[0].length;
        int left = 0, right = m-1;
        int up = 0, down = n-1;
        int sum = n * m;
        int index = 0;
        while(index < sum){
            //上边 ，从左往右
            for(int i = left; i <= right && index < sum; i ++){
                System.out.println(nums[up][i]);
                index ++;
            }
            //右边 从上往下
            for(int i = up + 1; i <= down-1 && index < sum; i ++){
                System.out.println(nums[i][right]);
                index ++;
            }
            //下边 从右往左
            for(int i = right; i >= left && index < sum; i --){
                System.out.println(nums[down][i]);
                index ++;
            }
            //左边  从下往上
            for(int i = down - 1; i >= up+1 && index < sum; i --){
                System.out.println(nums[i][left]);
                index ++;
            }
            left ++;
            right --;
            up ++;
            down --;
        }
    }


}
