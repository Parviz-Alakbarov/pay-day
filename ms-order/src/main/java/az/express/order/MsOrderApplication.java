package az.express.order;

import az.express.order.client.account.MsAccountClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {MsAccountClient.class})
public class MsOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsOrderApplication.class, args);
    }

}
