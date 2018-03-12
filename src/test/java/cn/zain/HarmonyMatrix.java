package cn.zain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by yongz on 2018/3/3.
 */
public class HarmonyMatrix {

    static Set<String> valueSet = new HashSet<>();

    public static void main(String[] args) {
        args =new String[]{"2","1"};
        if (null == args || args.length < 2) {
            System.out.println("输入参数有误，矩阵至少需要2个参数，args：" + args.length);
            System.exit(1);
        }

        int rowNum = 0;
        int colNum = 0;

        try {
            rowNum = Integer.parseInt(args[0]);
            colNum = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("输入参数错误，矩阵必须是2个整数,args：" + args[0] + " " + args[1]);
            System.exit(1);
        }

        if (rowNum * colNum < 2) {
            System.out.println("输入参数错误，矩阵至少需要2个数,args：" + args[0] + " " + args[1]);
            System.exit(1);
        }

        if (rowNum * colNum > 30) {
            System.out.println("输入参数错误，矩阵数量过多不支持,args：" + args[0] + " " + args[1]);
            System.exit(1);
        }


        long maxNum = calMax(rowNum *colNum);

        System.out.println(maxNum);

        while (valueSet.size() < maxNum) {
            //每加入一次判断是否是和谐矩阵
            int[][] matrix = createMatrix(rowNum, colNum);
//            printMatrix(matrix);

//            System.out.println( valueSet.size() + "pppp " +maxNum   );
//
//            System.exit(0);
            if (isHarmonyMatrix(matrix)) {
                System.out.println("找到和谐矩阵,args：" + args[0] + " " + args[1]);
                printMatrix(matrix);
                System.exit(0);
            }
        }

        System.out.println("找不到和谐矩阵,args：" + args[0] + " " + args[1]);
        System.exit(0);

    }

    private static void printMatrix(int[][] matrix) {
        if(null == matrix){
            System.out.println("输出矩阵:"  +  null );
            return ;
        }
        System.out.println("输出矩阵:" + matrix.length + "  " + matrix[0].length );
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static boolean isHarmonyMatrix(int[][] matrix) {
        if(null == matrix){
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int x00 = matrix[i][j];
                int x0_1 = j - 1 >= 0 ? matrix[i][j - 1] : 0;
                int x01 = j + 1 < matrix[0].length ? matrix[i][j + 1] : 0;
                int x_10 = i - 1 >= 0 ? matrix[i - 1][j] : 0;
                int x10 = i + 1 < matrix.length ? matrix[i + 1][j] : 0;
                int sum = x00 + x0_1 + x01 + x_10 + x10;
                if (sum ==0 || sum % 2 != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static long calMax(int num) {

        int total = 1;
        for (int i = 0; i < num; i++) {
            total  = total *2;
        }
        return total;
    }

    private static int[][] createMatrix(int rowNum, int colNum) {
        int[][] tmpMatrix = new int[rowNum][colNum];
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                Random random = new Random();
                tmpMatrix[i][j] = random.nextInt(2);
                sb.append(tmpMatrix[i][j]);
            }
        }
        if (valueSet.contains(sb.toString())) {
            System.out.println("矩阵已存在，" + sb.toString() + "跳过..");
            return null;
        }
        valueSet.add(sb.toString());
        return tmpMatrix;
    }

}
