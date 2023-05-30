package com.example.SSUtudyLogin;
import com.example.SSUtudyLogin.domain.Category;
import com.example.SSUtudyLogin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SsUtudyLoginApplication  { //implements CommandLineRunner

//	@Lazy
//	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SsUtudyLoginApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		final List<Category> categoryList = Arrays.asList(
//				new Category(1L,"os"),
//				new Category(2L),
//				new Category(3L)
//
//		);
//		List<Category> savedCategory = categoryRepository.saveAll(categoryList);
//		savedCategory.forEach(System.out::println);
//
//	}
}




