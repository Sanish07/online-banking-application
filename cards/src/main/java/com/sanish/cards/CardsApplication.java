package com.sanish.cards;

import com.sanish.cards.entities.Cards;
import com.sanish.cards.repositories.CardsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@SpringBootApplication
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

//    @Bean
    public CommandLineRunner commandLineRunner(CardsRepository cardsRepository) {
        return args -> {
            Cards cards = Cards.builder()
                    .mobileNumber("123456")
                    .cardNumber("900020001230")
                    .cardType("Visa")
                    .totalLimit(100000)
                    .amountUsed(23000)
                    .availableAmount(77000)
                    .build();

            cardsRepository.save(cards);

            Optional<Cards> cardsOptional = cardsRepository.findByMobileNumber("123456");
            cardsOptional.ifPresent(System.out::println);
        };
    }
}
