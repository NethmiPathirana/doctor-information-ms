package com.doctors.information;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com/doctors/information")
public class InformationApplication {

//	echo "# doctor-information-ms" >> README.md
//	git init
//	git add README.md
//	git commit -m "first commit"
//	git branch -M main
//	git remote add origin https://github.com/NethmiPathirana/doctor-information-ms.git
//	git push -u origin main
	public static void main(String[] args) {
		SpringApplication.run(InformationApplication.class, args);
	}

}
