import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author QLC
 * @Date 2022/9/20 17:37
 * @Description LeetCode测试单元
 */
public class LeetCodeTest {

    @Test
    public void t0() {
        String s = " practice   makes   perfect";
        int count;
        count = s.length() - s.replace(" ","").length();
        String[] words = s.trim().split("\\s+");
        StringBuilder ss = new StringBuilder();
        int n = count / (words.length - 1);
        int m = count - (words.length - 1) * n;
        for (int i = 0; i < words.length; i++) {
            ss.append(words[i]);
            if (i != words.length - 1) {
                for (int j = 0; j < n; j++) {
                    ss.append(" ");
                }
            }
        }
        for (int i = 0; i < m; i++) {
            ss.append(" ");
        }
        System.out.println(ss);
    }

    @Test
    public void t1() {
        String text = "alice is a good girl she is a good student";
        String first = "a";
        String second = "good";
        String[] words = text.split(" ");
        List<String> list = new ArrayList<>();
        int length = words.length - 2;
        for (int i = 0; i < length; i++) {
            if (words[i].equals(first) && words[i + 1].equals(second)) {
                list.add(words[i + 2]);
            }
        }
        System.out.println(Arrays.toString(list.toArray(new String[0])));
    }

    @Test
    public void t2() {
        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        int length = arr.length;
        for (int i = 0; i < length - 1; ++i) {
            if (arr[i] == 0) {
                System.arraycopy(arr, i, arr, i + 1, length - 1 - i);
                ++i;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void t3() {

    }

    @Test
    public void t4() {

    }

    @Test
    public void t5() {

    }

    @Test
    public void t6() {

    }

    @Test
    public void t7() {

    }

    @Test
    public void t8() {

    }

    @Test
    public void t9() {

    }

    @Test
    public void t10() {

    }

    @Test
    public void t11() {

    }

    @Test
    public void t12() {

    }

    @Test
    public void t13() {

    }

    @Test
    public void t14() {

    }

    @Test
    public void t15() {

    }

    @Test
    public void t16() {

    }

    @Test
    public void t17() {

    }

    @Test
    public void t18() {

    }

    @Test
    public void t19() {

    }

    @Test
    public void t20() {

    }

    @Test
    public void t21() {

    }

    @Test
    public void t22() {

    }

    @Test
    public void t23() {

    }

}
