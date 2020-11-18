package pl.com.mazniak.astydz7cars.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.com.mazniak.astydz7cars.controller.CarsController;
import pl.com.mazniak.astydz7cars.model.Car;
import pl.com.mazniak.astydz7cars.model.Color;

import javax.sql.DataSource;

@Configuration
public class CarsDBConfig {
    private DataSource dataSource;

    @Autowired
    public CarsDBConfig(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }


}
