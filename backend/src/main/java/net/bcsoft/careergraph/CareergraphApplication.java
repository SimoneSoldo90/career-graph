package net.bcsoft.careergraph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = { "net.bcsoft.careergraph.mapper" })
public class CareergraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareergraphApplication.class, args);
	}

}
