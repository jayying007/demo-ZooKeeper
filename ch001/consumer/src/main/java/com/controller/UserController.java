package com.controller;

import com.zk.ConsumerServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

/**
 * @author jayying
 * @since 2021/1/16
 */
@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping
    public Result getUser() {
        RestTemplate restTemplate = new RestTemplate();
        ConsumerServer server = ConsumerServer.getInstance();
        Result result = restTemplate.getForObject(server.getUrl(), Result.class);

        return result;
    }

    static class Result implements Serializable {
        private String provider;
        private User user;

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "provider='" + provider + '\'' +
                    ", user=" + user +
                    '}';
        }
    }

    static class User implements Serializable {
        private String username;
        private String gender;
        private String hobby;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", gender='" + gender + '\'' +
                    ", hobby='" + hobby + '\'' +
                    '}';
        }
    }
}
