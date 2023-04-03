package com.markobozic.parkinglots;

import com.markobozic.parkinglots.config.FlywayConfig;
import com.markobozic.parkinglots.config.RepositoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
        RepositoryConfig.class
})
@SpringBootApplication
public class ParkingLotsApplication {

    public static void main(String[] args) {
        FlywayConfig.migrate();
        SpringApplication.run(ParkingLotsApplication.class, args);
    }

}
