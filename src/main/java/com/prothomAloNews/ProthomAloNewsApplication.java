package com.prothomAloNews;

import com.prothomAloNews.Service.ProthomAloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProthomAloNewsApplication {

	public static void main(String[] args) {

		ApplicationContext context =  SpringApplication.run(ProthomAloNewsApplication.class, args);

		ProthomAloService service = context.getBean(ProthomAloService.class);

		service.findNews();

	}

}
