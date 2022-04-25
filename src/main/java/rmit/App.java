package rmit;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args

        );
    }

    @Override
    public void run(String... args) throws Exception {
        String script = "src/main/resources/createAccountPgAdminScript.sql";
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.runScript(new BufferedReader(new FileReader(script)));
    }
}
