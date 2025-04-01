package programmerzamannow.springdata.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class BelajarSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringDataJpaApplication.class, args);
	}

}
