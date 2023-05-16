package pers.qlc.leetcode.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author QLC
 * @Date 2022/12/1 10:11
 * @Description
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;

    private Date birthday;

    private Integer age;

    @Override
    public String toString() {
        return "pers.qlc.leetcode.entity.Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
