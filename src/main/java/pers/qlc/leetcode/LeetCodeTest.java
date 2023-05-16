package pers.qlc.leetcode;

import org.testng.annotations.Test;
import pers.qlc.leetcode.entity.Person;
import pers.qlc.leetcode.enums.UsageStatus;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author QLC
 * @Date 2022/9/20 17:37
 * @Description LeetCode单元测试
 */
public class LeetCodeTest {

    @Test
    public void t0() {
        String s = " practice   makes   perfect";
        int count;
        count = s.length() - s.replace(" ", "").length();
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
        int[] arr1 = new int[]{2, 21, 43, 38, 0, 42, 33, 7, 24, 13, 12, 27, 12, 24, 5, 23, 29, 48, 30, 31};
        int[] arr2 = new int[]{2, 42, 38, 0, 43, 21};
        Map<Integer, Integer> map = new HashMap<>(16);
        int[] res = new int[arr1.length];
        for (int num : arr1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int pos1 = 0;
        for (int num : arr2) {
            if (map.get(num) != 0) {
                for (int m = 0; m < map.get(num); m++) {
                    res[pos1++] = num;
                }
            }
            map.put(num, 0);
        }
        int[] ans = new int[arr1.length - pos1];
        int pos2 = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                for (int i = 0; i < entry.getValue(); i++) {
                    ans[pos2++] = entry.getKey();
                }
            }
        }
        Arrays.sort(ans);
        for (int num : ans) {
            res[pos1++] = num;
        }
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void t4() {
        String text = "Keep calm and code on";
        String[] s = text.toLowerCase().split(" ");
        Arrays.sort(s, Comparator.comparingInt(String::length));
        char first = s[0].charAt(0);
        first = (char) (first - 32);
        String temp = first + s[0].substring(1);
        s[0] = temp;
        String res;
        res = String.join(" ", s);
        System.out.println(res);
    }

    @Test
    public void t5() {
        int[] nums = new int[]{1, 100, 200, 1, 100, 1, 300, 1, 100, 1, 200, 1, 500};
        int key = 1;
        int[] list = new int[1001];
        int result = 0;
        int maxSum = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] == key) {
                ++list[nums[i]];
                if (list[nums[i]] > maxSum) {
                    result = nums[i];
                    maxSum = list[nums[i]];
                }
            }
        }
        System.out.println(result);
    }

    @Test
    public void t6() {
        String version = "V1.6";
        version = version.substring(1);
        String[] split = version.split("\\.");
        String majorVersion = split[0];
        String minjorVersion = split[1];
        System.out.println(majorVersion + minjorVersion);
    }

    @Test
    public void t7() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = dateFormat.parse("2000-12-1");
        Date d2 = dateFormat.parse("2001-12-1");
        Date d3 = dateFormat.parse("2002-12-1");
        List<Person> people = new ArrayList<Person>() {{
            add(new Person("张三", d1, 23));
            add(new Person("李四", d2, 22));
            add(new Person("王五", d3, 21));
        }};
        System.out.println(people.stream().min(Comparator.comparing(Person::getBirthday, Comparator.nullsFirst(Comparator.naturalOrder()))).orElse(people.get(0)));
    }

    @Test
    public void t8() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = dateFormat.parse("2000-12-1");
        Date d2 = dateFormat.parse("2001-12-1");
        Date d3 = dateFormat.parse("2002-12-1");
        List<Person> people = new ArrayList<Person>() {{
            add(new Person("张三", d1, 23));
            add(new Person("李四", d2, 22));
            add(new Person("王五", d3, 21));
        }};
        System.out.println(people.toString().replace("[", "").replace("]", "").replace(", ", "，"));
    }

    @Test
    public void t9() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = dateFormat.parse("2000-12-1");
        Date d2 = dateFormat.parse("2001-12-1");
        Date d3 = dateFormat.parse("2002-12-1");
        List<Person> people1 = new ArrayList<Person>() {{
            add(new Person("张三", d1, 23));
            add(new Person("李四", d2, 22));
            add(new Person("王五", d3, 21));
        }};
        List<Person> people2 = new ArrayList<Person>() {{
            add(new Person("张三", d1, 23));
            add(new Person("李四", d2, 22));
        }};
        System.out.println(people1 + "\n" + people2);
        System.out.println("----------------------");
        List<Person> collect = people1.stream().filter(p -> {
            for (Person person : people2) {
                if (p.getName().equals(person.getName())) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void t10() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = dateFormat.parse("2000-12-1");
            Date d2 = dateFormat.parse("2001-12-1");
            Date d3 = dateFormat.parse("2002-12-1");
            List<Person> people1 = new ArrayList<Person>() {{
                add(new Person("张三", d1, 23));
                add(new Person("李四", d2, 22));
                add(new Person("王五", d3, 21));
            }};
            System.out.println(people1);
            System.out.println(TimeUnit.SECONDS.toMillis(5));
        } catch (Exception ignore) {
        }
    }

    @Test
    public void t11() {
        UsageStatus used = UsageStatus.getByCode("Y");
        System.out.println(used.getDesc());
    }

    @Test
    public void t12() {
        String roleId = "A010200000000006".substring(0, 5);
        System.out.println(roleId);
    }

    @Test
    public void t13() {
        BigDecimal bigDecimal = new BigDecimal(1000);
        int i = Integer.parseInt(bigDecimal.toString());
        Integer integer = 1123;
        System.out.println(bigDecimal);
        System.out.println(integer);
    }

    @Test
    public void t14() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = dateFormat.parse("2000-12-1");
        Person person = new Person("张三", d1, 23);
        person.setAge(null);
        System.out.println(person.getAge());
    }

    @Test
    public void t15() {
        List<Person> people = new ArrayList<>();
        System.out.println(people);
    }

    @Test
    public void t16() {
        int integer = 100000;
        BigDecimal bigDecimal = new BigDecimal(integer);
        System.out.println(bigDecimal);
    }

    @Test
    public void t17() {
        System.out.println(7 / 8);
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
