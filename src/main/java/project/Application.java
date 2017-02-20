package project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import project.persistence.entities.Exercise;
import project.persistence.entities.User;
import project.persistence.repositories.ExerciseRepository;
import project.persistence.repositories.UserRepository;
import project.service.ExerciseService;

import java.util.Date;
import java.util.List;

/**
 * The main class of the project.
 * By running the main class of {@link Application} then you start the Spring Boot system
 */
@SpringBootApplication
@EnableJpaRepositories
public class Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(Application.class);

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    // A method that creates exercises when the application is run
    @Bean
    public CommandLineRunner makeExercises(ExerciseRepository repository) {
        return (args) -> {
            List exerciseList = repository.findAll();
            // if exercise table is empty then input exercises
            if(exerciseList.size()==0) {
                repository.save(new Exercise("Back Squat", "Weightlifting"));
                repository.save(new Exercise("Bench Press", "Weightlifting"));
                repository.save(new Exercise("Deadlift", "Weightlifting"));
                repository.save(new Exercise("Front Squat", "Weightlifting"));
                repository.save(new Exercise("Power Clean", "Weightlifting"));
                repository.save(new Exercise("Push Jerk", "Weightlifting"));
                repository.save(new Exercise("Push Press", "Weightlifting"));
                repository.save(new Exercise("Shoulder Press", "Weightlifting"));
                repository.save(new Exercise("Snatch", "Weightlifting"));
                repository.save(new Exercise("Split Jerk", "Weightlifting"));
            }
        };
    }

    // A method that creates mock users when the application is run
    @Bean
    public CommandLineRunner makeMockUser(UserRepository repository) {
        return (args) -> {
            List usersList = repository.findAll();
            // if user table is empty then input mock users
            if(usersList.size()==0) {
                repository.save(new User("tester1", "123", "Tester Testson", new Date(), 180, 80));
            }

        };
    }
}
