package az.express.notification.client;

import az.express.notification.client.model.UserDto;
import az.express.notification.error.model.RestResponse;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "ms-user",
        configuration = MsUserClient.FeignConfiguration.class,
        primary = false)
public interface MsUserClient {

    @GetMapping(value = "/users", params = "userId")
    RestResponse<UserDto> findById(@RequestParam("userId") Long userId);

    class FeignConfiguration {

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

    }
}
