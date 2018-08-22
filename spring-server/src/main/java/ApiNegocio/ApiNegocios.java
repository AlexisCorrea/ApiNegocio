package ApiNegocio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ApiNegocios  {
    public static void main(String[] args) throws Exception {
        new SpringApplication(ApiNegocios.class).run(args);
    }

}
