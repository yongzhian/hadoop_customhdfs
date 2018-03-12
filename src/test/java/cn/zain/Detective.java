package cn.zain;

import java.util.*;

/**
 * Created by yongz on 2018/3/3.
 */
public class Detective {

    static String[] choice = {"A", "B", "C", "D"};
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {


        long num = (long) Math.pow(4, 10);
        initSet(num);
        System.out.println(" total :" + set.size());

        for (String str : set) {

            if (validateRight(str)) {
                System.out.println(str);
            }
            continue;
        }
    }

    private static boolean validateRight(String str) {
        if (!judege2(str.charAt(1), str)) {
            return false;
        }

        if (!judege3(str.charAt(2), str.charAt(2), str.charAt(5), str.charAt(1), str.charAt(3))) {
            return false;
        }
        if (!judege4(str.charAt(3), str)) {
            return false;
        }

        if (!judege5(str.charAt(4), str)) {
            return false;
        }
        if (!judege6(str.charAt(5), str)) {
            return false;
        }
        if (!judege7(str.charAt(6), str)) {
            return false;
        }
        if (!judege8(str.charAt(7), str)) {
            return false;
        }
        if (!judege9(str.charAt(8), str)) {
            return false;
        }

        if (!judege10(str.charAt(9), str)) {
            return false;
        }

        return true;
    }

    private static boolean judege2(char c1, String str) {
        if (c1 == 'A') {
            return str.charAt(4) == 'C';
        }
        if (c1 == 'B') {
            return str.charAt(4) == 'D';
        }
        if (c1 == 'C') {
            return str.charAt(4) == 'A';
        }
        if (c1 == 'D') {
            return str.charAt(4) == 'B';
        }
        return false;
    }

    private static boolean judege10(char c1, String str) {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < 10; i++) {
            if (str.charAt(i) == 'A') {
                a++;
            }
            if (str.charAt(i) == 'B') {
                b++;
            }
            if (str.charAt(i) == 'C') {
                c++;
            }
            if (str.charAt(i) == 'D') {
                d++;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        Collections.sort(list);

        int r = 0;
        if (c1 == 'A') {
            r = 3;
        }
        if (c1 == 'B') {
            r = 2;
        }
        if (c1 == 'C') {
            r = 4;
        }
        if (c1 == 'D') {
            r = 1;
        }

        return r == list.get(3) - list.get(0);
    }

    private static boolean judege9(char c1, String str) {
        char m = c1;
        if (c1 == 'A') {
            m = str.charAt(5);
        }
        if (c1 == 'B') {
            m = str.charAt(9);
        }
        if (c1 == 'C') {
            m = str.charAt(1);
        }
        if (c1 == 'D') {
            m = str.charAt(8);
        }

        if (str.charAt(0) == str.charAt(5)) {
            return str.charAt(4) != m;
        } else {
            return str.charAt(4) == m;
        }
    }

    private static boolean judege8(char c1, String str) {
        char m = c1;
        if (c1 == 'A') {
            m = str.charAt(6);
        }
        if (c1 == 'B') {
            m = str.charAt(4);
        }
        if (c1 == 'C') {
            m = str.charAt(1);
        }
        if (c1 == 'D') {
            m = str.charAt(9);
        }
        return Math.abs((int) c1 - (int) m) != 1;
    }

    private static boolean judege7(char c1, String str) {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < 10; i++) {
            if (str.charAt(i) == 'A') {
                a++;
            }
            if (str.charAt(i) == 'B') {
                b++;
            }
            if (str.charAt(i) == 'C') {
                c++;
            }
            if (str.charAt(i) == 'D') {
                d++;
            }
        }
        if (c1 == 'A') {
            return a <= b && a <= b && a <= d;
        }
        if (c1 == 'B') {
            return b <= a && b <= c && b <= d;
        }
        if (c1 == 'C') {
            return c <= a && c <= b && c <= d;
        }
        if (c1 == 'D') {
            return d <= a && d <= b && d <= c;
        }
        return false;

    }

    private static boolean judege6(char c, String str) {
        char c8 = str.charAt(7);
        if (c == 'A') {
            return c8 == str.charAt(1) && c8 == str.charAt(3);
        }
        if (c == 'B') {
            return c8 == str.charAt(0) && c8 == str.charAt(5);
        }
        if (c == 'C') {
            return c8 == str.charAt(2) && c8 == str.charAt(9);
        }
        if (c == 'D') {
            return c8 == str.charAt(4) && c8 == str.charAt(8);
        }
        return false;
    }

    private static boolean judege5(char c, String str) {
        if (c == 'A') {
            return str.charAt(7) == c;
        }
        if (c == 'B') {
            return str.charAt(3) == c;
        }
        if (c == 'C') {
            return str.charAt(8) == c;
        }
        if (c == 'D') {
            return str.charAt(6) == c;
        }
        return false;
    }

    private static boolean judege4(char c, String str) {
        if (c == 'A') {
            return str.charAt(0) == str.charAt(4);
        }
        if (c == 'B') {
            return str.charAt(1) == str.charAt(7);
        }
        if (c == 'C') {
            return str.charAt(0) == str.charAt(8);
        }
        if (c == 'D') {
            return str.charAt(5) == str.charAt(9);
        }
        return false;
    }

    private static boolean judege3(char c, char c1, char c2, char c3, char c4) {
        if (c == 'A') {
            return c1 != c2 && c1 != c3 && c1 != c4;
        }
        if (c == 'B') {
            return c2 != c1 && c2 != c3 && c2 != c4;
        }
        if (c == 'C') {
            return c3 != c1 && c3 != c2 && c3 != c4;
        }
        if (c == 'D') {
            return c4 != c1 && c4 != c2 && c4 != c3;
        }
        return false;
    }

    private static void initSet(long num) {
        Random random = new Random();
        while (set.size() < num) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 10; i++) {
                sb.append(choice[random.nextInt(4)]);
            }
            if (!set.contains(sb)) {
                set.add(sb.toString());
            }
        }
        System.out.println("初始化完成..");
    }


}
