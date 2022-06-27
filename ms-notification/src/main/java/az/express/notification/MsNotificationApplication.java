package az.express.notification;

import az.express.notification.client.MsUserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = MsUserClient.class)
@EnableDiscoveryClient
public class MsNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsNotificationApplication.class, args);
    }

}
