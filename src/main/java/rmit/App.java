package rmit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class App{
    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args

        );
    }


}
