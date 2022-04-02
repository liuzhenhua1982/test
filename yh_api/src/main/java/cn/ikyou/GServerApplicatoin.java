package cn.ikyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cn.ikyou.**.dao")
@EnableScheduling
public class GServerApplicatoin {

    public static void main(String[] args) {
        SpringApplication.run(GServerApplicatoin.class, args);
    }
}
