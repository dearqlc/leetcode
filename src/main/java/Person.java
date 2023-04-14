import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author QLC
 * @Date 2022/12/1 10:11
 * @Description
 */
@Data
@AllArgsConstructor
@Builder
public class Person {

    private String name;

    private Date birthday;

    private Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
