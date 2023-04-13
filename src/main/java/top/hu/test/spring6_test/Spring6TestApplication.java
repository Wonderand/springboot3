package top.hu.test.spring6_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import top.hu.test.spring6_test.modules.sys.common.MyRealm;
import top.hu.test.spring6_test.service.GoodsServiceImpl;

//@MapperScan("top.hu.test.spring6_test.modules.sys.mapper")
//@EnableJpaRepositories(basePackages={"top.hu.test.spring6_test.repository"})
//@EntityScan("top.hu.test.spring6_test.entity")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Spring6TestApplication {


    public static void main(String[] args) {
        SpringApplication.run(Spring6TestApplication.class, args);
    }

    @Bean
    public GoodsServiceImpl service(){
        return new GoodsServiceImpl();
    }

//    @Bean
//    public MyRealm realm(){
//        return new MyRealm();
//    }

}
