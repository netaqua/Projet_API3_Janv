package com.example.projet_api3_janv;

import com.example.projet_api3_janv.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfiguration {
    @Value("${server.mode}")
    private String mode;
    @Bean
    InterfEmployeService apiemployeServiceImpl() {
        System.out.println("création du service employé en mode : "+mode);
        switch (mode){
            case "PROD" : return new EmployeServiceImpl();
            case "STUB" : return new EmployeServiceStub();
            case "MOCK" : return new EmployeServiceMock();
            default: return new EmployeServiceStub();
        }
    }
    @Bean
    InterfProjetService apiprojetServiceImpl() {
        System.out.println("création du service projet en mode : "+mode);
        switch (mode){
            case "PROD" : return new ProjetServiceImpl();
            case "STUB" : return new ProjetServiceStub();
            case "MOCK" : return new ProjetServiceMock();
            default: return new ProjetServiceStub();
        }
    }





}
