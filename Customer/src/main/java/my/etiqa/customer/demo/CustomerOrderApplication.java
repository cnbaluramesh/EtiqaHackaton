package my.etiqa.customer.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "Etiqa Hackaton Customer Test ",
		version = "1.0",
		description = "Etiqa Hackaton Customer Test API Document"
))
@SpringBootApplication
public class CustomerOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerOrderApplication.class, args);
	}

}
