package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book theSunsetCrowd = new Book();
            theSunsetCrowd.setTitle("The Sunset Crowd ");
            theSunsetCrowd.setAuthor(" Karin Tanabe");
            theSunsetCrowd.setPrice(BigDecimal.valueOf(16));
            theSunsetCrowd.setDescription("In this novel, Evra Scott,"
                    + " the daughter of an Oscar-winning director "
                    + "and a Brazilian bombshell actress, is L.A.’s reigning style queen."
                    + " Currently, she’s romantically linked to famous screenwriter "
                    + "Kai de la Faire.");
            theSunsetCrowd.setIsbn("isbn");
            theSunsetCrowd.setCoverImage("A girl on the bitch");

            Book familyLore = new Book();
            familyLore.setTitle("Family Lore ");
            familyLore.setAuthor(" Elizabeth Acevedo");
            familyLore.setPrice(BigDecimal.valueOf(19));
            familyLore.setDescription("In this novel, Evra Scott,"
                    + " the daughter of an Oscar-winning director "
                    + "and a Brazilian bombshell actress, is L.A.’s reigning style queen."
                    + " Currently, she’s romantically linked to famous screenwriter "
                    + "Kai de la Faire.");
            familyLore.setIsbn("isbn");
            familyLore.setCoverImage("A chair");
            bookService.save(theSunsetCrowd);
            bookService.findAll();
        };

    }
}
