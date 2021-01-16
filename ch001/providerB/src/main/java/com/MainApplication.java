package com;

import com.google.gson.Gson;
import com.zk.RegistryServer;
import com.zk.Status;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jayying
 * @since 2021/1/15
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        //启动成功，上报到注册中心
        RegistryServer server = RegistryServer.getInstance();

        Status status = new Status();
        status.setServerName("UserService");
        status.setIP("127.0.0.1");
        status.setPort("8020");
        status.setPath("/user/info");

        Gson gson = new Gson();
        server.register(gson.toJson(status));
    }
}
