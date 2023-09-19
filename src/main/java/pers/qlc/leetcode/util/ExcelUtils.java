package pers.qlc.leetcode.util;

import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    /**
     * 解析EXCEL 并根据注解完成必填校验及正则规则校验
     *
     * @param clazz 需解析成的对象
     * @param file  EXCEL文件
     * @param <T>   需解析成的对象类型
     * @return
     */
    public static <T> List<T> readExcelWithCheck(Class<T> clazz, File file) {
        List<T> t = new ArrayList<>();
        ExcelListener excelListener = new ExcelListener(clazz);
        EasyExcel.read(file, clazz, excelListener).sheet().doReadSync().forEach(o -> {
            T data = (T) o;
            t.add(data);
        });
        return t;
    }

}
