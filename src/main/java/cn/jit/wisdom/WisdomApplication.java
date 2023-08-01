package cn.jit.wisdom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.jit.wisdom.mapper")
public class WisdomApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisdomApplication.class, args);
    }

}
