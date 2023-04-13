package top.hu.test.spring6_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class User {
    @Id // @Id注解指明这个属性映射为数据库的主键。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 名称
    private String userName;
    // 年龄
    private Integer age;
    // 地址
    private String address;
}
