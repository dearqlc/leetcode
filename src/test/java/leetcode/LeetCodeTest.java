package leetcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.fsi.insurance.agreement.facade.dto.AgreementDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import pers.qlc.leetcode.dto.Coinsurance;
import pers.qlc.leetcode.dto.request.AgreementInfo;
import pers.qlc.leetcode.dto.response.AgreementResponseAppDTO;
import pers.qlc.leetcode.dto.response.AgreementResponseDTO;
import pers.qlc.leetcode.entity.Person;
import pers.qlc.leetcode.enums.UsageStatus;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author QLC
 * @Date 2022/9/20 17:37
 * @Description LeetCode单元测试
 */
@Slf4j
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

    /**
     * LocalDate使用方法
     */
    @Test
    public void t18() {
        LocalDate now = LocalDate.now();
        LocalDate birthday = LocalDate.of(2000, 7, 17);
        int year = now.get(ChronoField.YEAR);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localDateTime + "   " + localDate + "   " + localTime);
        System.out.println(birthday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // 计算两个日子之间隔了几天
        System.out.println(ChronoUnit.DAYS.between(LocalDate.of(2000, 7, 17), LocalDate.now()));
    }

    @Test
    public void t19() {
        List<String> stringList = Arrays.asList("1", "2", "2");
        System.out.println(stringList);
        HashSet<String> strings = new HashSet<>(stringList);
        System.out.println(strings);
    }

    @Test
    public void t20() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = dateFormat.parse("2000-12-1");
        Date d2 = dateFormat.parse("2001-12-1");
        Date d3 = dateFormat.parse("2002-12-1");
        List<Person> people1 = new ArrayList<Person>() {{
            add(new Person("张三", d1, 23));
            add(new Person("李四", d2, 22));
            add(new Person("王五", d3, 21));
            add(new Person("王五", d3, 21));
        }};
        System.out.println(people1);
        HashSet<Person> people = new HashSet<>(people1);
        System.out.println(people);
    }

    @Test
    public void t21() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = dateFormat.parse("2000-12-1");
        Person zhangThree = new Person("张三", d1, 23);
        Person liFour = new Person("张三", d1, 23);
        List<Person> people = Arrays.asList(zhangThree, liFour);
    }

    @Test
    public void t22() {
        int[] intArray = {1, 2, 3};
        List<Integer> collect = Arrays.stream(intArray).boxed().collect(Collectors.toList());
        collect.add(4);
        System.out.println(collect);
        System.out.println(Arrays.toString(intArray));
    }

    @Test
    public void t23() {
        System.out.println(StringUtils.isNotBlank(" "));
        System.out.println(StringUtils.isNotEmpty(" "));
        System.out.println(StringUtils.isNotBlank(""));
        System.out.println(StringUtils.isNotEmpty(""));
    }

    @Test
    public void t24() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        System.out.println(res + "\n" + res.size());
    }

    @Test
    public void t25() {
        Coinsurance coinsurance = new Coinsurance("D", "D03", "D0301", "D030100");
        Coinsurance coinsurance2 = new Coinsurance("D", "D03", "D0301", "D030200");
        Coinsurance coinsurance3 = new Coinsurance("D", "D03", "D0301", "D030300");
        ArrayList<String> strings = new ArrayList<String>() {{
            add("D030200");
            add("D030300");
        }};
        List<Coinsurance> coinsurances = new ArrayList<Coinsurance>() {{
            add(coinsurance);
            add(coinsurance2);
            add(coinsurance3);
        }};
        System.out.println(coinsurances.stream().filter(dto -> strings.contains(dto.getLv4())).collect(Collectors.toList()));
    }

    @Test
    public void t26() {
        String agrJson = "{\n" +
                "    \"agreement\": {\n" +
                "        \"agreementElement\": {\n" +
                "            \"period\": {\n" +
                "                \"contractYear\": 2023,\n" +
                "                \"dateFormat\": \"yyyy-MM-dd\",\n" +
                "                \"signDt\": 1686844800000,\n" +
                "                \"startDt\": 1686844800000,\n" +
                "                \"endDt\": 1696844800000,\n" +
                "                \"firstPartySignDt\": 1666844800000\n" +
                "            },\n" +
                "            \"extendInfo\": {\n" +
                "                \"belongOrgCode\": \"210000000000\",\n" +
                "                \"belongOrgName\": \"中华财险\"\n" +
                "            },\n" +
                "            \"documents\": [\n" +
                "                {\n" +
                "                    \"fileName\": \"8618499.jpg\",\n" +
                "                    \"fileOssId\": \"230619114126320761167\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"gmtModified\": 1686844800000,\n" +
                "        \"authorizationScope\": [],\n" +
                "        \"histories\": [],\n" +
                "        \"items\": [],\n" +
                "        \"jointInsuranceTypeCd\": \"coinsurance\",\n" +
                "        \"gmtCreate\": 1686844800000,\n" +
                "        \"creator\": \"undefined-undefined\",\n" +
                "        \"resolveTypeCd\": \"02\",\n" +
                "        \"agreementDesc\": \"备注\",\n" +
                "        \"agreementName\": \"合同名称11\",\n" +
                "        \"signature\": \"signed\",\n" +
                "        \"agreementTypeCd\": \"17\",\n" +
                "        \"participants\": [\n" +
                "            {\n" +
                "                \"belongOrgCode\": \"a\",\n" +
                "                \"belongOrg\": \"a公司\",\n" +
                "                \"orgCode\": \"213300000000\",\n" +
                "                \"participantName\": \"机构1\",\n" +
                "                \"agent\": \"张三\",\n" +
                "                \"thirdSalesman\": \"签署人\",\n" +
                "                \"mobilePhoneNo\": \"19876665778\",\n" +
                "                \"detailAddress\": \"地址1\",\n" +
                "                \"email\": \"1@qq.com\",\n" +
                "                \"coinsurance\": {\n" +
                "                    \"isMajorInsurer\": true,\n" +
                "                    \"isIssuer\": true\n" +
                "                },\n" +
                "                \"contactPersonList\": [\n" +
                "                    {\n" +
                "                        \"orgCode\": \"213300000000\",\n" +
                "                        \"detailAddress\": \"地址1\",\n" +
                "                        \"contactsType\": \"99\",\n" +
                "                        \"contacts\": \"联系人\",\n" +
                "                        \"post\": \"总裁\",\n" +
                "                        \"mobilePhoneNo\": \"19876887678\",\n" +
                "                        \"email\": \"11@qq.com\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"participantNo\": 1,\n" +
                "                \"participantTypeCd\": \"6\",\n" +
                "                \"isSingleRole\": false,\n" +
                "                \"relationships\": []\n" +
                "            }\n" +
                "        ],\n" +
                "        \"paymentSettlement\": {\n" +
                "            \"bankAccountList\": [],\n" +
                "            \"currencyCd\": \"CNY\",\n" +
                "            \"feeCalculateModeCd\": \"withoutTax\",\n" +
                "            \"issueExpenseModeCd\": \"withTax\",\n" +
                "            \"settlementPeriodCd\": \"week\",\n" +
                "            \"preminumStyle\": \"03\",\n" +
                "            \"preminumEnvironmentCd\": \"payFirstThenInvoice\",\n" +
                "            \"feeCalculateStyle\": \"03\",\n" +
                "            \"settlementEnvironmentCd\": \"payFirstThenInvoice\",\n" +
                "            \"issueExpensetEnvironmentCd\": \"payWithoutInvoice\"\n" +
                "        },\n" +
                "        \"claims\": {\n" +
                "            \"serviceTime\": \"2\",\n" +
                "            \"compensationTime\": \"3\",\n" +
                "            \"noticeLimitationTime\": \"4\",\n" +
                "            \"lossAmount\": \"2\",\n" +
                "            \"paymentMethodClaims\": \"1\",\n" +
                "            \"contentPackage\": \"摊赔材料包内容\",\n" +
                "            \"recognizeds\": [\n" +
                "                {\n" +
                "                    \"itemSequenceNo\": 1,\n" +
                "                    \"recognizedLawFirms\": \"共保体认可律所\",\n" +
                "                    \"recognizedLawyer\": \"共保体认可律师\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";
        JSONObject agr = JSONObject.parseObject(agrJson);
        JSONObject agreement = agr.getJSONObject("agreement");
        JSONObject jsonObject = agreement.getJSONObject("123");
        JSONObject a1gr = JSONObject.parseObject(null);
        System.out.println(agr);
    }

    @Test
    public void t27() {
        String json = "{\"agreementElement\":{\"period\":{\"contractYear\":2023,\"dateFormat\":\"yyyy-MM-dd\",\"signDt\":\"2023-06-20 00:00:00\",\"startDt\":\"2023-07-11 00:00:00\",\"endDt\":\"2023-07-12 23:59:59\",\"firstPartySignDt\":\"2023-06-01 00:00:00\"},\"extendInfo\":{\"belongOrgCode\":\"210000000000\",\"belongOrgName\":\"中华财险\"},\"documents\":[{\"fileName\":\"8618499.jpg\",\"fileOssId\":\"230620161612651109855\"}]},\"gmtModified\":\"2023-06-20 16:16:21\",\"authorizationScope\":[],\"histories\":[],\"jointInsuranceTypeCd\":\"coinsurance\",\"gmtCreate\":\"2023-06-20 00:00:00\",\"creator\":\"1010001393-童建飞\",\"resolveTypeCd\":\"02\",\"agreementDesc\":\"我\",\"agreementName\":\"波罗蜜\",\"signature\":\"signed\",\"agreementTypeCd\":\"17\",\"participants\":[{\"belongOrgCode\":\"D030100000000006\",\"belongOrg\":\"共保测试4\",\"customerNo\":\"1088291146196264\",\"orgCode\":\"D030300000000002\",\"participantName\":\"共保中支\",\"agent\":null,\"detailAddress\":\"共保地址\",\"coinsurance\":{\"isMajorInsurer\":true,\"isIssuer\":true},\"contactPersonList\":[],\"participantNo\":1,\"participantTypeCd\":\"6\",\"isSingleRole\":false,\"relationships\":[]}],\"paymentSettlement\":{\"bankAccountList\":[{\"accountBranchAreaCode\":\"3301\",\"accountBranchAreaName\":\"浙江省_杭州市\",\"accountBranchCode\":\"402331003642\",\"accountBranchName\":\"浙江杭州余杭农村商业银行股份有限公司崇贤支行老鸦桥分理处\",\"accountName\":\"A11\",\"accountNo\":\"6222028195788928661\",\"accountUseTypeCd\":\"03\",\"bankName\":\"4023312\",\"realBankName\":\"浙江杭州余杭农村商业银行\",\"orgCode\":\"D030300000000002\",\"customerNo\":\"1088291146224525\"}],\"currencyCd\":\"CNY\",\"feeCalculateModeCd\":\"withoutTax\",\"settlementPeriodCd\":\"week\",\"preminumStyle\":\"03\",\"preminumEnvironmentCd\":\"payWithoutInvoice\",\"feeCalculateStyle\":\"03\",\"settlementEnvironmentCd\":\"payWithoutInvoice\",\"issueExpensetEnvironmentCd\":\"payWithoutInvoice\",\"issueExpenseModeCd\":\"withoutTax\"},\"claims\":{\"compensationTime\":\"2\",\"paymentMethodClaims\":\"1\"}}";
        AgreementDTO agreementDTO = JSONObject.parseObject(json, AgreementDTO.class);
//        System.out.println(JSON.toJSONString(agreementDTO, true));
        String json1 = "{\n" +
                "            \"modifier\": \"1010001393-童建飞\",\n" +
                "            \"gmtModified\": \"2023-06-20 16:16:21\",\n" +
                "            \"creator\": \"1010001393-童建飞\",\n" +
                "            \"gmtCreate\": \"2023-06-20 16:16:21\",\n" +
                "            \"agreementInstanceId\": \"202306211010050001095600200000000001334\",\n" +
                "            \"agreementMajorVersionNo\": 1,\n" +
                "            \"agreementMinorVersionNo\": 0,\n" +
                "            \"agreementNo\": \"H230621000000000000000001\",\n" +
                "            \"agreementTypeCd\": \"18\",\n" +
                "            \"agreementStatusCd\": \"1\",\n" +
                "            \"agreementName\": \"波罗蜜\",\n" +
                "            \"isGeneralAgreement\": false,\n" +
                "            \"isAgreement\": true,\n" +
                "            \"bizLinkModeCd\": null,\n" +
                "            \"resolveTypeCd\": \"02\",\n" +
                "            \"resolveOrgName\": null,\n" +
                "            \"items\": [],\n" +
                "            \"participants\": [\n" +
                "                {\n" +
                "                    \"id\": null,\n" +
                "                    \"roleName\": null,\n" +
                "                    \"isSingleRole\": null,\n" +
                "                    \"shareDutyRate\": null,\n" +
                "                    \"participantNo\": 1,\n" +
                "                    \"participantTypeCd\": \"1\",\n" +
                "                    \"participantProperty\": null,\n" +
                "                    \"agreementId\": null,\n" +
                "                    \"participantName\": null,\n" +
                "                    \"provinceCode\": null,\n" +
                "                    \"cityCode\": null,\n" +
                "                    \"countyCode\": null,\n" +
                "                    \"streetCode\": null,\n" +
                "                    \"mobilePhoneNo\": null,\n" +
                "                    \"telephoneNo\": null,\n" +
                "                    \"permanentAddress\": null,\n" +
                "                    \"detailAddress\": null,\n" +
                "                    \"accountBranchCode\": null,\n" +
                "                    \"bankAccountNo\": null,\n" +
                "                    \"certTypeCd\": null,\n" +
                "                    \"certNo\": null,\n" +
                "                    \"certEffectiveStartDt\": null,\n" +
                "                    \"certEffectiveEndDt\": null,\n" +
                "                    \"signOrg\": null,\n" +
                "                    \"isCertLongTerm\": null,\n" +
                "                    \"genderCd\": null,\n" +
                "                    \"age\": null,\n" +
                "                    \"nation\": null,\n" +
                "                    \"countryCode\": null,\n" +
                "                    \"birthDt\": null,\n" +
                "                    \"employeeNo\": null,\n" +
                "                    \"customerNo\": null,\n" +
                "                    \"custMlRiskCd\": null,\n" +
                "                    \"orgCode\": null,\n" +
                "                    \"fleetNo\": null,\n" +
                "                    \"belongOrg\": null,\n" +
                "                    \"agent\": null,\n" +
                "                    \"agentCode\": null,\n" +
                "                    \"agencyAgreement\": null,\n" +
                "                    \"bizRiskTypeCd\": null,\n" +
                "                    \"bizCode\": null,\n" +
                "                    \"bizCompany\": null,\n" +
                "                    \"thirdSalesman\": null,\n" +
                "                    \"postalCode\": null,\n" +
                "                    \"email\": null,\n" +
                "                    \"orgTypeCd\": null,\n" +
                "                    \"agreementElement\": {\n" +
                "                        \"documents\": [],\n" +
                "                        \"extendInfo\": {},\n" +
                "                        \"left\": null,\n" +
                "                        \"terms\": [],\n" +
                "                        \"balance\": null,\n" +
                "                        \"strategy\": [],\n" +
                "                        \"period\": null\n" +
                "                    },\n" +
                "                    \"relationships\": [],\n" +
                "                    \"coinsurance\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": null,\n" +
                "                    \"roleName\": null,\n" +
                "                    \"isSingleRole\": null,\n" +
                "                    \"shareDutyRate\": null,\n" +
                "                    \"participantNo\": 2,\n" +
                "                    \"participantTypeCd\": \"3\",\n" +
                "                    \"participantProperty\": null,\n" +
                "                    \"agreementId\": null,\n" +
                "                    \"participantName\": \"中华联合财产保险股份有限公司\",\n" +
                "                    \"provinceCode\": null,\n" +
                "                    \"cityCode\": null,\n" +
                "                    \"countyCode\": null,\n" +
                "                    \"streetCode\": null,\n" +
                "                    \"mobilePhoneNo\": null,\n" +
                "                    \"telephoneNo\": null,\n" +
                "                    \"permanentAddress\": null,\n" +
                "                    \"detailAddress\": null,\n" +
                "                    \"accountBranchCode\": null,\n" +
                "                    \"bankAccountNo\": null,\n" +
                "                    \"certTypeCd\": null,\n" +
                "                    \"certNo\": null,\n" +
                "                    \"certEffectiveStartDt\": null,\n" +
                "                    \"certEffectiveEndDt\": null,\n" +
                "                    \"signOrg\": null,\n" +
                "                    \"isCertLongTerm\": null,\n" +
                "                    \"genderCd\": null,\n" +
                "                    \"age\": null,\n" +
                "                    \"nation\": null,\n" +
                "                    \"countryCode\": null,\n" +
                "                    \"birthDt\": null,\n" +
                "                    \"employeeNo\": null,\n" +
                "                    \"customerNo\": null,\n" +
                "                    \"custMlRiskCd\": null,\n" +
                "                    \"orgCode\": \"210000000000\",\n" +
                "                    \"fleetNo\": null,\n" +
                "                    \"belongOrg\": null,\n" +
                "                    \"agent\": null,\n" +
                "                    \"agentCode\": null,\n" +
                "                    \"agencyAgreement\": null,\n" +
                "                    \"bizRiskTypeCd\": null,\n" +
                "                    \"bizCode\": null,\n" +
                "                    \"bizCompany\": null,\n" +
                "                    \"thirdSalesman\": null,\n" +
                "                    \"postalCode\": null,\n" +
                "                    \"email\": null,\n" +
                "                    \"orgTypeCd\": null,\n" +
                "                    \"agreementElement\": {\n" +
                "                        \"documents\": [],\n" +
                "                        \"extendInfo\": {},\n" +
                "                        \"left\": null,\n" +
                "                        \"terms\": [],\n" +
                "                        \"balance\": null,\n" +
                "                        \"strategy\": [],\n" +
                "                        \"period\": null\n" +
                "                    },\n" +
                "                    \"relationships\": [],\n" +
                "                    \"coinsurance\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": null,\n" +
                "                    \"roleName\": null,\n" +
                "                    \"isSingleRole\": null,\n" +
                "                    \"shareDutyRate\": null,\n" +
                "                    \"participantNo\": 3,\n" +
                "                    \"participantTypeCd\": \"4\",\n" +
                "                    \"participantProperty\": null,\n" +
                "                    \"agreementId\": null,\n" +
                "                    \"participantName\": null,\n" +
                "                    \"provinceCode\": null,\n" +
                "                    \"cityCode\": null,\n" +
                "                    \"countyCode\": null,\n" +
                "                    \"streetCode\": null,\n" +
                "                    \"mobilePhoneNo\": null,\n" +
                "                    \"telephoneNo\": null,\n" +
                "                    \"permanentAddress\": null,\n" +
                "                    \"detailAddress\": null,\n" +
                "                    \"accountBranchCode\": null,\n" +
                "                    \"bankAccountNo\": null,\n" +
                "                    \"certTypeCd\": null,\n" +
                "                    \"certNo\": null,\n" +
                "                    \"certEffectiveStartDt\": null,\n" +
                "                    \"certEffectiveEndDt\": null,\n" +
                "                    \"signOrg\": null,\n" +
                "                    \"isCertLongTerm\": null,\n" +
                "                    \"genderCd\": null,\n" +
                "                    \"age\": null,\n" +
                "                    \"nation\": null,\n" +
                "                    \"countryCode\": null,\n" +
                "                    \"birthDt\": null,\n" +
                "                    \"employeeNo\": null,\n" +
                "                    \"customerNo\": null,\n" +
                "                    \"custMlRiskCd\": null,\n" +
                "                    \"orgCode\": \"D030100000000007\",\n" +
                "                    \"fleetNo\": null,\n" +
                "                    \"belongOrg\": null,\n" +
                "                    \"agent\": null,\n" +
                "                    \"agentCode\": null,\n" +
                "                    \"agencyAgreement\": null,\n" +
                "                    \"bizRiskTypeCd\": null,\n" +
                "                    \"bizCode\": null,\n" +
                "                    \"bizCompany\": null,\n" +
                "                    \"thirdSalesman\": null,\n" +
                "                    \"postalCode\": null,\n" +
                "                    \"email\": null,\n" +
                "                    \"orgTypeCd\": null,\n" +
                "                    \"agreementElement\": {\n" +
                "                        \"documents\": [],\n" +
                "                        \"extendInfo\": {},\n" +
                "                        \"left\": null,\n" +
                "                        \"terms\": [],\n" +
                "                        \"balance\": null,\n" +
                "                        \"strategy\": [],\n" +
                "                        \"period\": null\n" +
                "                    },\n" +
                "                    \"relationships\": [],\n" +
                "                    \"coinsurance\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"histories\": [\n" +
                "                {\n" +
                "                    \"id\": \"202306211010050001095600200000000001022\",\n" +
                "                    \"agreementMajorVersionNo\": 1,\n" +
                "                    \"agreementMinorVersionNo\": 0,\n" +
                "                    \"agreementNo\": \"H230621000000000000000001\",\n" +
                "                    \"agreementStatusCd\": \"1\",\n" +
                "                    \"effectDt\": \"2023-07-11 00:00:00\",\n" +
                "                    \"modifier\": \"1010001393-童建飞\",\n" +
                "                    \"gmtModified\": \"2023-06-20 16:16:21\",\n" +
                "                    \"auditBy\": \"agreement\",\n" +
                "                    \"auditDt\": \"2023-06-21 09:56:31\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"paymentSettlement\": {\n" +
                "                \"settlementPeriodCd\": \"week\",\n" +
                "                \"settlementDay\": null,\n" +
                "                \"bankAccount\": null,\n" +
                "                \"settlementMethodCd\": null,\n" +
                "                \"settlementEnvironmentCd\": \"payWithoutInvoice\",\n" +
                "                \"invoiceModeCd\": null,\n" +
                "                \"paymentModeCd\": null,\n" +
                "                \"feeCalculateModeCd\": \"withoutTax\",\n" +
                "                \"currencyCd\": \"CNY\"\n" +
                "            },\n" +
                "            \"tax\": null,\n" +
                "            \"agreementDesc\": \"我\",\n" +
                "            \"socialCreditCode\": null,\n" +
                "            \"authorizationScope\": [],\n" +
                "            \"areas\": [],\n" +
                "            \"agreementSignAddress\": null,\n" +
                "            \"jointInsuranceTypeCd\": null,\n" +
                "            \"isValid\": true,\n" +
                "            \"agreementElement\": {\n" +
                "                \"documents\": [\n" +
                "                    {\n" +
                "                        \"fileOssId\": \"230620161612651109855\",\n" +
                "                        \"fileName\": \"8618499.jpg\",\n" +
                "                        \"filePath\": null,\n" +
                "                        \"tags\": []\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"extendInfo\": {\n" +
                "                    \"belongOrgName\": \"中华财险\",\n" +
                "                    \"belongOrgCode\": \"210000000000\"\n" +
                "                },\n" +
                "                \"left\": null,\n" +
                "                \"terms\": [],\n" +
                "                \"balance\": null,\n" +
                "                \"strategy\": [],\n" +
                "                \"period\": {\n" +
                "                    \"firstPartySignDt\": \"2023-06-01 00:00:00\",\n" +
                "                    \"signDt\": \"2023-06-20 00:00:00\",\n" +
                "                    \"startDt\": \"2023-07-11 00:00:00\",\n" +
                "                    \"endDt\": \"2023-07-12 23:59:59\",\n" +
                "                    \"agreementModifiedDt\": \"2023-07-11 00:00:00\",\n" +
                "                    \"dateFormat\": \"yyyy-MM-dd\",\n" +
                "                    \"contractYear\": 2023\n" +
                "                }\n" +
                "            },\n" +
                "            \"relationships\": []\n" +
                "        }";
        AgreementDTO agreementDTO1 = JSONObject.parseObject(json1, AgreementDTO.class);
//        System.out.println(JSON.toJSONString(agreementDTO1, true));
        String json2 = "{\"agreementElement\":{\"period\":{\"contractYear\":2023,\"dateFormat\":\"yyyy-MM-dd\",\"signDt\":\"2023-06-21 00:00:00\",\"startDt\":\"2023-07-19 00:00:00\",\"endDt\":\"2023-07-26 23:59:59\",\"firstPartySignDt\":\"2023-06-01 00:00:00\"},\"extendInfo\":{\"belongOrgCode\":\"210000000000\",\"belongOrgName\":\"中华财险\"},\"documents\":[{\"fileName\":\"8618499.jpg\",\"fileOssId\":\"230621153112172520579\"}]},\"gmtModified\":\"2023-06-21 15:31:16\",\"authorizationScope\":[],\"histories\":[],\"jointInsuranceTypeCd\":\"coinsurance\",\"gmtCreate\":\"2023-06-21 00:00:00\",\"creator\":\"1010001393-童建飞\",\"resolveTypeCd\":\"02\",\"agreementName\":\"裘\",\"signature\":\"signed\",\"agreementTypeCd\":\"17\",\"participants\":[{\"belongOrgCode\":\"D030100000000006\",\"belongOrg\":\"共保测试4\",\"customerNo\":\"1088291146196264\",\"orgCode\":\"D030300000000002\",\"participantName\":\"共保中支\",\"agent\":null,\"detailAddress\":\"共保地址\",\"coinsurance\":{\"isMajorInsurer\":true,\"isIssuer\":true},\"contactPersonList\":[],\"participantNo\":1,\"participantTypeCd\":\"6\",\"isSingleRole\":false,\"relationships\":[]},{\"belongOrgCode\":\"D030100000000006\",\"belongOrg\":\"共保测试4\",\"customerNo\":\"1088291146196264\",\"orgCode\":\"D030300000000002\",\"participantName\":\"共保中支\",\"agent\":null,\"detailAddress\":\"共保地址\",\"coinsurance\":{\"isMajorInsurer\":true,\"isIssuer\":true},\"contactPersonList\":[],\"participantNo\":1,\"participantTypeCd\":\"6\",\"isSingleRole\":false,\"relationships\":[]}],\"paymentSettlement\":{\"bankAccountList\":[],\"currencyCd\":\"CNY\",\"feeCalculateModeCd\":\"withoutTax\",\"preminumStyle\":\"02\",\"settlementPeriodCd\":\"month\",\"preminumEnvironmentCd\":\"payWithoutInvoice\",\"feeCalculateStyle\":\"01\",\"settlementEnvironmentCd\":\"payWithoutInvoice\",\"issueExpensetEnvironmentCd\":\"payWithoutInvoice\",\"issueExpenseModeCd\":\"withoutTax\"},\"claims\":{\"paymentMethodClaims\":\"2\",\"compensationTime\":\"2\"}}";
        AgreementResponseDTO agreementDTO2 = JSONObject.parseObject(json2, AgreementResponseDTO.class);
        System.out.println(JSON.toJSONString(agreementDTO2, true));
    }

    @Test
    public void t28() {
        String json = "{\"agrEndDate\":1687708799000,\"agrName\":\"QQ\",\"agrSignComCode\":\"210000000000\",\"agrSignComName\":\"中华联合财产保险股份有限公司\",\"agrSignComPath\":\"210000000000\",\"agrSignDate\":1685721600000,\"agrStartDate\":1686412800000,\"agreementExtendJson\":\"{\\\"agreementElement\\\":{\\\"period\\\":{\\\"contractYear\\\":2023,\\\"dateFormat\\\":\\\"yyyy-MM-dd\\\",\\\"signDt\\\":\\\"2023-06-21 00:00:00\\\",\\\"startDt\\\":\\\"2023-06-11 00:00:00\\\",\\\"endDt\\\":\\\"2023-06-25 23:59:59\\\",\\\"firstPartySignDt\\\":\\\"2023-06-03 00:00:00\\\"},\\\"extendInfo\\\":{\\\"belongOrgCode\\\":\\\"210000000000\\\",\\\"belongOrgName\\\":\\\"中华财险\\\"},\\\"documents\\\":[{\\\"fileName\\\":\\\"a.jpg\\\",\\\"fileOssId\\\":\\\"23062119145747684934\\\"}]},\\\"gmtModified\\\":\\\"2023-06-21 19:14:59\\\",\\\"authorizationScope\\\":[{\\\"orgCode\\\":\\\"210000000000\\\",\\\"orgName\\\":\\\"中华财险\\\"}],\\\"histories\\\":[],\\\"jointInsuranceTypeCd\\\":\\\"coinsurance\\\",\\\"gmtCreate\\\":\\\"2023-06-21 00:00:00\\\",\\\"creator\\\":\\\"1010001510-葛圣明\\\",\\\"resolveTypeCd\\\":\\\"02\\\",\\\"agreementName\\\":\\\"QQ\\\",\\\"signature\\\":\\\"signed\\\",\\\"agreementTypeCd\\\":\\\"17\\\",\\\"participants\\\":[{\\\"belongOrgCode\\\":\\\"D030100000000003\\\",\\\"belongOrg\\\":\\\"DFT共保总公司测试2\\\",\\\"customerNo\\\":\\\"1088011008732021\\\",\\\"orgCode\\\":\\\"D030300000000007\\\",\\\"participantName\\\":\\\"测试共保机构\\\",\\\"agent\\\":null,\\\"thirdSalesman\\\":\\\"Q\\\",\\\"detailAddress\\\":\\\"某地址\\\",\\\"coinsurance\\\":{\\\"isMajorInsurer\\\":true,\\\"isIssuer\\\":true},\\\"contactPersonList\\\":[],\\\"participantNo\\\":1,\\\"participantTypeCd\\\":\\\"6\\\",\\\"isSingleRole\\\":false,\\\"relationships\\\":[]}],\\\"paymentSettlement\\\":{\\\"bankAccountList\\\":[],\\\"currencyCd\\\":\\\"CNY\\\",\\\"feeCalculateModeCd\\\":\\\"withoutTax\\\",\\\"settlementPeriodCd\\\":\\\"week\\\",\\\"preminumStyle\\\":\\\"03\\\",\\\"preminumEnvironmentCd\\\":\\\"payWithoutInvoice\\\",\\\"issueExpensetEnvironmentCd\\\":\\\"payWithoutInvoice\\\",\\\"settlementEnvironmentCd\\\":\\\"payWithoutInvoice\\\",\\\"feeCalculateStyle\\\":\\\"03\\\",\\\"issueExpenseModeCd\\\":\\\"withoutTax\\\"},\\\"claims\\\":{\\\"compensationTime\\\":\\\"1\\\",\\\"paymentMethodClaims\\\":\\\"2\\\"}}\",\"agreementTypeCode\":\"17\",\"isExpandBusiness\":false}";
        AgreementInfo agreementInfo = JSONObject.parseObject(json, AgreementInfo.class);
        AgreementDTO agreementDTO = JSONObject.parseObject(agreementInfo.getAgreementExtendJson(), AgreementDTO.class);
        System.out.println(JSON.toJSONString(agreementInfo, true));
        System.out.println("---------------------------------");
        System.out.println(JSON.toJSONString(agreementDTO, true));
    }

    @Test
    public void t29() {
        String json = "{\"agreementElement\":{\"period\":{\"contractYear\":2023,\"dateFormat\":\"yyyy-MM-dd\",\"signDt\":\"2023-06-04 00:00:00\",\"startDt\":\"2023-06-04 00:00:00\",\"endDt\":\"2023-06-11 23:59:59\",\"firstPartySignDt\":\"2023-06-01 00:00:00\"},\"extendInfo\":{\"belongOrgCode\":\"211100000000\",\"projectName\":\"QQ\"},\"documents\":[{\"fileName\":\"a.jpg\",\"fileOssId\":\"230627112325943338783\"}]},\"gmtModified\":\"2023-06-27 11:23:27\",\"histories\":[],\"items\":[],\"jointInsuranceTypeCd\":\"coinsurance\",\"gmtCreate\":\"2023-06-27 00:00:00\",\"creator\":\"1010001393-童建飞\",\"resolveTypeCd\":\"02\",\"agreementName\":\"QQ\",\"signature\":\"signed\",\"authorizationScope\":[],\"agreementTypeCd\":\"17\",\"participants\":[{\"belongOrgCode\":\"210000000000\",\"belongOrg\":\"中华财产保险有限公司\",\"orgCode\":\"210000000000\",\"participantName\":\"中华财险\",\"coinsurance\":{\"isMajorInsurer\":true,\"isIssuer\":true},\"contactPersonList\":[],\"participantNo\":1,\"participantTypeCd\":\"6\",\"isSingleRole\":false,\"relationships\":[],\"isCic\":true}],\"paymentSettlement\":{\"bankAccountList\":[],\"currencyCd\":\"CNY\",\"feeCalculateModeCd\":\"withoutTax\",\"settlementPeriodCd\":\"week\",\"preminumStyle\":\"03\",\"preminumEnvironmentCd\":\"payWithoutInvoice\",\"issueExpensetEnvironmentCd\":\"payFirstThenInvoice\",\"settlementEnvironmentCd\":\"payWithoutInvoice\",\"feeCalculateStyle\":\"03\",\"issueExpenseModeCd\":\"withoutTax\"},\"claims\":{\"paymentMethodClaims\":\"2\",\"compensationTime\":\"1\"}}";
        AgreementDTO agreementDTO = JSONObject.parseObject(json, AgreementDTO.class);
        System.out.println(JSON.toJSONString(agreementDTO, true));
    }

    @Test
    public void t30() {
        String json = "{\"agreementElement\":{\"documents\":[{\"fileName\":\"a.jpg\",\"fileOssId\":\"230628094643696869304\"}],\"extendInfo\":{\"belongOrgCode\":\"211100000000\",\"projectName\":\"QW\"},\"period\":{\"contractYear\":2023,\"dateFormat\":\"yyyy-MM-dd\",\"endDt\":\"2023-06-18T23:59:59\",\"firstPartySignDt\":\"2023-06-04T00:00:00\",\"signDt\":\"2023-06-11T00:00:00\",\"startDt\":\"2023-06-11T00:00:00\"}},\"agreementName\":\"QW\",\"agreementStatusCd\":\"1\",\"agreementTypeCd\":\"17\",\"areas\":[],\"claims\":{\"compensationTime\":1,\"paymentMethodClaims\":\"2\"},\"creator\":\"1010001074-穆建桥\",\"gmtCreate\":\"2023-06-28T00:00:00\",\"gmtModified\":\"2023-06-28T09:46:44\",\"histories\":[],\"items\":[{\"areas\":[],\"departments\":[],\"feeRates\":[{\"orgCode\":\"210000000000\",\"shareRate\":0.20000000},{\"orgCode\":\"D030300000000007\",\"shareRate\":0.80000000}],\"groupByKey\":\"0\",\"itemSequenceNo\":1,\"products\":[{\"insuranceClassCode\":\"01\",\"insuranceClassName\":\"意外险\",\"productCode\":\"D01003\",\"productName\":\"个人意外伤害保险（A款）\"}],\"regions\":[],\"relationships\":[]}],\"jointInsuranceTypeCd\":\"coinsurance\",\"participants\":[{\"belongOrg\":\"中华财产保险有限公司\",\"belongOrgCode\":\"210000000000\",\"coinsurance\":{\"isIssuer\":false,\"isMajorInsurer\":false},\"contactPersonList\":[],\"isCic\":true,\"isSingleRole\":false,\"orgCode\":\"210000000000\",\"participantName\":\"中华财险\",\"participantNo\":1,\"participantTypeCd\":\"6\",\"relationships\":[]},{\"belongOrg\":\"DFT共保总公司测试2\",\"belongOrgCode\":\"D030100000000003\",\"coinsurance\":{\"isIssuer\":true,\"isMajorInsurer\":true},\"contactPersonList\":[],\"customerNo\":\"1088011008732021\",\"detailAddress\":\"某地址\",\"isCic\":false,\"isSingleRole\":false,\"orgCode\":\"D030300000000007\",\"participantName\":\"测试共保机构\",\"participantNo\":1,\"participantTypeCd\":\"6\",\"relationships\":[]}],\"paymentSettlement\":{\"bankAccountList\":[],\"currencyCd\":\"CNY\",\"feeCalculateModeCd\":\"withoutTax\",\"feeCalculateStyle\":\"03\",\"issueExpenseModeCd\":\"withoutTax\",\"issueExpensetEnvironmentCd\":\"payFirstThenInvoice\",\"preminumEnvironmentCd\":\"payWithoutInvoice\",\"preminumStyle\":\"03\",\"settlementEnvironmentCd\":\"payWithoutInvoice\",\"settlementPeriodCd\":\"week\"},\"relationships\":[],\"resolveTypeCd\":\"02\",\"signature\":\"signed\"}\n";
        AgreementResponseDTO agreementDTO = JSONObject.parseObject(json, AgreementResponseDTO.class);
        System.out.println(JSON.toJSONString(agreementDTO, true));
    }

    @Test
    public void t31() {
        String agrNo = "";
        String[] split = agrNo.split("\\.");
        if (agrNo.split("\\.") != null && agrNo.split("\\.")[0].length() > 0) {
            System.out.println(agrNo.split("\\.")[0].substring(1));
        }
    }

    @Test
    public void t32() {
        String json = "{\"agreementElement\":{\"documents\":[{\"fileOssId\":\"230704163824758167569\",\"tags\":[]}],\"extendInfo\":{\"belongOrgCode\":\"210000000000\",\"belongOrgName\":\"中华财险\",\"projectName\":\"改名\"},\"period\":{\"agreementModifiedDt\":\"2023-07-16T00:00:00\",\"contractYear\":2023,\"dateFormat\":\"yyyy-MM-dd\",\"endDt\":\"2023-07-23T23:59:59\",\"firstPartySignDt\":\"2023-07-06T00:00:00\",\"signDt\":\"2023-07-16T00:00:00\",\"startDt\":\"2023-07-16T00:00:00\"},\"strategy\":[],\"terms\":[]},\"agreementMajorVersionNo\":3,\"agreementMinorVersionNo\":0,\"agreementName\":\"74改名后后\",\"agreementNo\":\"H230704000000000000000006\",\"agreementStatusCd\":\"1\",\"agreementTypeCd\":\"17\",\"areas\":[],\"authorizationScope\":[],\"claims\":{\"compensationTime\":1,\"paymentMethodClaims\":\"2\",\"recognizeds\":[]},\"creator\":\"1010001074-穆建桥\",\"gmtCreate\":\"2023-07-04T17:15:02\",\"gmtModified\":\"2023-07-04T17:15:02\",\"histories\":[{\"agreementMajorVersionNo\":1,\"agreementMinorVersionNo\":0,\"agreementNo\":\"H230704000000000000000006\",\"agreementStatusCd\":\"1\",\"auditBy\":\"agreement\",\"auditDt\":\"2023-07-04T16:38:30\",\"effectDt\":\"2023-07-16T00:00:00\",\"gmtModified\":\"2023-07-04T16:38:26\",\"id\":\"202307041010050001163800200000000042469\",\"modifier\":\"1010001074-穆建桥\"},{\"agreementMajorVersionNo\":2,\"agreementMinorVersionNo\":0,\"agreementNo\":\"H230704000000000000000006\",\"agreementStatusCd\":\"1\",\"auditBy\":\"agreement\",\"auditDt\":\"2023-07-04T16:39:10\",\"effectDt\":\"2023-07-16T00:00:00\",\"gmtModified\":\"2023-07-04T16:38:58\",\"id\":\"202307041010050001163900200000000042470\",\"modifier\":\"1010001074-穆建桥\"},{\"agreementMajorVersionNo\":3,\"agreementMinorVersionNo\":0,\"agreementNo\":\"H230704000000000000000006\",\"agreementStatusCd\":\"1\",\"auditBy\":\"agreement\",\"auditDt\":\"2023-07-04T17:15:26\",\"effectDt\":\"2023-07-16T00:00:00\",\"gmtModified\":\"2023-07-04T17:15:02\",\"id\":\"202307041010050001171500200000000042471\",\"modifier\":\"1010001074-穆建桥\"}],\"id\":\"202307041010050001171500200000000067022\",\"isAgreement\":true,\"isGeneralAgreement\":false,\"isValid\":true,\"items\":[{\"agreementElement\":{\"documents\":[],\"extendInfo\":{},\"strategy\":[],\"terms\":[]},\"areas\":[],\"departments\":[],\"feeRates\":[{\"orgCode\":\"210000000000\",\"shareRate\":0.900000000000},{\"orgCode\":\"D030200000000001\",\"shareRate\":0.100000000000}],\"groupByKey\":\"0\",\"isFix\":false,\"itemSequenceNo\":1,\"products\":[{\"insuranceClassCode\":\"01\",\"insuranceClassName\":\"意外险\",\"productCode\":\"D01003\",\"productName\":\"个人意外伤害保险（A款）\"}],\"regions\":[],\"relationships\":[]}],\"jointInsuranceTypeCd\":\"coinsurance\",\"modifier\":\"1010001074-穆建桥\",\"participants\":[{\"agreementElement\":{\"documents\":[],\"extendInfo\":{},\"strategy\":[],\"terms\":[]},\"belongOrg\":\"中华财产保险有限公司\",\"belongOrgCode\":\"210000000000\",\"coinsurance\":{\"isIssuer\":true,\"isMajorInsurer\":true},\"isCic\":true,\"isSingleRole\":false,\"orgCode\":\"210000000000\",\"participantName\":\"中华财险\",\"participantNo\":1,\"participantTypeCd\":\"6\",\"relationships\":[]},{\"agent\":\"法人\",\"agreementElement\":{\"documents\":[],\"extendInfo\":{},\"strategy\":[],\"terms\":[]},\"belongOrg\":\"DT共保总\",\"belongOrgCode\":\"D030100000000001\",\"coinsurance\":{\"isIssuer\":false,\"isMajorInsurer\":false},\"customerNo\":\"1088061543203595\",\"detailAddress\":\"地址\",\"isCic\":false,\"isSingleRole\":false,\"orgCode\":\"D030200000000001\",\"participantName\":\"DT共保分\",\"participantNo\":1,\"participantTypeCd\":\"6\",\"relationships\":[]}],\"paymentSettlement\":{\"bankAccountList\":[],\"currencyCd\":\"CNY\",\"feeCalculateModeCd\":\"withoutTax\",\"feeCalculateStyle\":\"03\",\"issueExpenseModeCd\":\"withoutTax\",\"issueExpensetEnvironmentCd\":\"payWithoutInvoice\",\"preminumEnvironmentCd\":\"payWithoutInvoice\",\"preminumStyle\":\"03\",\"settlementEnvironmentCd\":\"payWithoutInvoice\",\"settlementPeriodCd\":\"month\"},\"relationships\":[],\"resolveTypeCd\":\"02\",\"signature\":\"signed\"}";
        AgreementResponseAppDTO agreementDTO = JSONObject.parseObject(json, AgreementResponseAppDTO.class);
        System.out.println(agreementDTO);
    }

    @Test
    public void t33() {
        String json = "{\"agreementElement\":{\"documents\":[{\"fileOssId\":\"230809163711424493219\",\"tags\":[]}],\"extendInfo\":{\"belongOrgName\":\"中华财险\",\"belongOrgCode\":\"210000000000\",\"projectName\":\"qwe\"},\"period\":{\"agreementModifiedDt\":\"2023-09-11T00:00:00\",\"contractYear\":2023,\"dateFormat\":\"yyyy-MM-dd\",\"endDt\":\"2023-09-28T23:59:59\",\"firstPartySignDt\":\"2023-08-18T00:00:00\",\"signDt\":\"2023-09-11T00:00:00\",\"startDt\":\"2023-09-11T00:00:00\"},\"strategy\":[],\"terms\":[]},\"agreementMajorVersionNo\":3,\"agreementMinorVersionNo\":0,\"agreementName\":\"共保同步测试\",\"agreementNo\":\"H230809000000000000001047\",\"agreementStatusCd\":\"1\",\"agreementTypeCd\":\"17\",\"areas\":[],\"authorizationScope\":[],\"histories\":[{\"agreementMajorVersionNo\":1,\"agreementMinorVersionNo\":0,\"agreementNo\":\"H230809000000000000001047\",\"agreementStatusCd\":\"1\",\"auditBy\":\"agreement\",\"auditDt\":\"2023-08-09T16:37:21\",\"effectDt\":\"2023-09-11T00:00:00\",\"gmtModified\":\"2023-08-09T16:37:15\",\"id\":\"202308091010050001163700200000000012988\",\"modifier\":\"1010001393-童建飞\"},{\"agreementMajorVersionNo\":2,\"agreementMinorVersionNo\":0,\"agreementNo\":\"H230809000000000000001047\",\"agreementStatusCd\":\"1\",\"auditBy\":\"agreement\",\"auditDt\":\"2023-08-09T16:39:13\",\"effectDt\":\"2023-09-11T00:00:00\",\"gmtModified\":\"2023-08-09T16:39:08\",\"id\":\"202308091010050001163900200000000012989\",\"modifier\":\"1010001393-童建飞\"},{\"agreementMajorVersionNo\":3,\"agreementMinorVersionNo\":0,\"agreementNo\":\"H230809000000000000001047\",\"agreementStatusCd\":\"1\",\"auditBy\":\"agreement\",\"auditDt\":\"2023-08-09T16:43:08\",\"effectDt\":\"2023-09-11T00:00:00\",\"gmtModified\":\"2023-08-09T16:43:03\",\"id\":\"202308091010050001164300200000000012991\",\"modifier\":\"1010001393-童建飞\"}],\"isAgreement\":true,\"isGeneralAgreement\":false,\"isValid\":true,\"items\":[{\"agreementElement\":{\"documents\":[],\"extendInfo\":{},\"strategy\":[],\"terms\":[]},\"areas\":[],\"departments\":[],\"isFix\":false,\"itemSequenceNo\":1,\"products\":[{\"insuranceClassCode\":\"07\",\"insuranceClassName\":\"货物运输保险\",\"productCode\":\"D07001\",\"productName\":\"国内公路随车行李物品损失定额保险\"}],\"regions\":[],\"relationships\":[]}],\"jointInsuranceTypeCd\":\"coinsurance\",\"participants\":[{\"agreementElement\":{\"documents\":[],\"extendInfo\":{},\"strategy\":[],\"terms\":[]},\"belongOrg\":\"中华财产保险有限公司\",\"coinsurance\":{\"isIssuer\":true,\"isMajorInsurer\":true},\"isSingleRole\":false,\"orgCode\":\"211100000000\",\"participantName\":\"北京分公司\",\"participantNo\":1,\"participantTypeCd\":\"6\",\"relationships\":[]},{\"agent\":\"法人\",\"agreementElement\":{\"documents\":[],\"extendInfo\":{},\"strategy\":[],\"terms\":[]},\"belongOrg\":\"PT共保总公司\",\"coinsurance\":{\"isIssuer\":false,\"isMajorInsurer\":false},\"customerNo\":\"1088291148157432\",\"detailAddress\":\"地址\",\"isSingleRole\":false,\"orgCode\":\"D020102000000103\",\"participantName\":\"PT共保分公司\",\"participantNo\":1,\"participantTypeCd\":\"6\",\"relationships\":[]}],\"paymentSettlement\":{\"currencyCd\":\"CNY\",\"feeCalculateModeCd\":\"withoutTax\",\"settlementEnvironmentCd\":\"payAfterInvoice\",\"settlementPeriodCd\":\"week\"},\"relationships\":[],\"resolveTypeCd\":\"02\"}\n";
        AgreementResponseAppDTO agreementDTO = JSONObject.parseObject(json, AgreementResponseAppDTO.class);
        System.out.println((String) agreementDTO.getAgreementElement().getExtendInfo().get("belongOrgCode"));
        System.out.println((String) agreementDTO.getAgreementElement().getExtendInfo().get("belongOrgName"));
    }

    @Test
    public void t34() {
        String json = "{\"agreementElement\":{\"period\":{\"contractYear\":2023,\"dateFormat\":\"yyyy-MM-dd\",\"signDt\":\"2023-09-11 00:00:00\",\"startDt\":\"2023-09-11 00:00:00\",\"endDt\":\"2023-09-28 23:59:59\",\"firstPartySignDt\":\"2023-08-18 00:00:00\"},\"extendInfo\":{\"belongOrgCode\":\"210000000000\",\"belongOrgName\":\"中华财险\",\"serviceObjectCd\":\"1088291146269827\",\"serviceObjectName\":\"123凯旋代步（厦门）实业有限公司\",\"projectName\":\"qwert\"},\"documents\":[{\"fileOssId\":\"230809163711424493219\"}]},\"gmtModified\":\"2023-08-10 14:58:14\",\"histories\":[],\"items\":[{\"groupByKey\":0,\"itemSequenceNo\":1,\"products\":[{\"insuranceClassCode\":\"07\",\"insuranceClassName\":\"货物运输保险\",\"productCode\":\"D07001\",\"productName\":\"国内公路随车行李物品损失定额保险\"}],\"feeRates\":[{\"shareRate\":\"0.90000000\",\"orgCode\":\"211100000000\"},{\"shareRate\":\"0.10000000\",\"orgCode\":\"D020102000000103\"}]}],\"jointInsuranceTypeCd\":\"coinsurance\",\"gmtCreate\":\"2023-08-10 00:00:00\",\"creator\":\"1010001393-童建飞\",\"resolveTypeCd\":\"02\",\"agreementStatusCd\":\"1\",\"agreementName\":\"共保同步测试\",\"signature\":\"notSigned\",\"authorizationScope\":[{\"orgCode\":\"210000000000\",\"orgName\":\"中华财险\"}],\"agreementTypeCd\":\"17\",\"participants\":[{\"belongOrgCode\":\"210000000000\",\"belongOrg\":\"中华财产保险有限公司\",\"customerNo\":null,\"belongCustomerNo\":null,\"orgCode\":\"211100000000\",\"participantName\":\"北京分公司\",\"agent\":null,\"thirdSalesman\":null,\"mobilePhoneNo\":null,\"detailAddress\":null,\"email\":null,\"coinsurance\":{\"isMajorInsurer\":true,\"isIssuer\":true},\"contactPersonList\":[],\"participantNo\":1,\"participantTypeCd\":\"6\",\"isSingleRole\":false,\"relationships\":[],\"isCic\":true},{\"belongOrgCode\":\"D020101000000103\",\"belongOrg\":\"PT共保总公司\",\"customerNo\":\"1088291148157432\",\"belongCustomerNo\":\"1088291148157386\",\"orgCode\":\"D020102000000103\",\"participantName\":\"PT共保分公司\",\"agent\":\"法人\",\"thirdSalesman\":null,\"mobilePhoneNo\":null,\"detailAddress\":\"地址\",\"email\":null,\"coinsurance\":{\"isMajorInsurer\":false,\"isIssuer\":false},\"contactPersonList\":[],\"participantNo\":1,\"participantTypeCd\":\"6\",\"isSingleRole\":false,\"relationships\":[],\"isCic\":false}],\"paymentSettlement\":{\"bankAccountList\":[],\"currencyCd\":\"CNY\",\"feeCalculateModeCd\":\"withoutTax\",\"settlementPeriodCd\":\"week\",\"settlementDay\":null,\"bankAccount\":null,\"settlementMethodCd\":null,\"settlementEnvironmentCd\":\"payAfterInvoice\",\"invoiceModeCd\":null,\"paymentModeCd\":null,\"preminumStyle\":\"02\",\"feeCalculateStyle\":\"01\",\"issueExpensetEnvironmentCd\":\"payAfterInvoice\",\"preminumEnvironmentCd\":\"payAfterInvoice\",\"issueExpenseModeCd\":\"withoutTax\"},\"claims\":{\"serviceTime\":null,\"compensationTime\":1,\"noticeLimitationTime\":null,\"lossAmount\":null,\"paymentMethodClaims\":\"1\",\"contentPackage\":null,\"recognizeds\":[]},\"modifier\":\"1010001393-童建飞\",\"id\":\"202308101010050001145400200000000014220\"}";
        AgreementDTO agreementDTO = JSONObject.parseObject(json, AgreementDTO.class);
        System.out.println(agreementDTO);
    }

    @Test
    public void t35() {
        int hold = 0;
        int change = 0;
        for (int i = 0; i < 1000000; i++) {
            if ((int) (Math.random() * 3) == (int) (Math.random() * 3)) {
                hold++;
            } else {
                change++;
            }
        }
        System.out.println("不换门的中奖率：" + (double) hold / 1000000 + "\n换过门的中奖率：" + (double) change / 1000000);
    }

    @Test
    public void t36() {
        int[] arr = new int[]{10, 1, 4, 134, 6, 5};
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        Map<String, Integer> map = new HashMap<>();
        map.put("123", null);
        System.out.println(map);
    }

    public boolean isCardID(String sId) {
        String pattern = "^(\\d{15}$)|(\\d{17}(\\d|X|x)$)";
        if (!Pattern.matches(pattern, sId)) {
            return false;
        }
        // 身份证号码校验
        int sum = 0;
        int[] weights = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        String codes = "10X98765432";
        for (int i = 0; i < sId.length() - 1; i++) {
            sum += Character.getNumericValue(sId.charAt(i)) * weights[i];
        }
        // 计算出来的最后一位身份证号码
        char last = codes.charAt(sum % 11);
        return sId.charAt(sId.length() - 1) == last;
    }

    @Test
    public void t37() {
        System.out.println(isCardID("330621200007178950"));
    }
}
