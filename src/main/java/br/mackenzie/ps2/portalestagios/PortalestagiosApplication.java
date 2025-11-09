package br.mackenzie.ps2.portalestagios;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class PortalestagiosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalestagiosApplication.class, args);
    }

    @Bean
    public CommandLineRunner testDatabaseConnection(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            }
        };
    }
}