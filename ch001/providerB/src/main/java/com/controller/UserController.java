package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jayying
 * @since 2021/1/15
 */
@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("info")
    public Result getUserInfo() {
        Result result = new Result();
        result.setProvider("ProviderB");

        User user = new User();
        user.setUsername("Jayying");
        user.setGender("Man");
        user.setHobby("Programming");
        result.setUser(user);

        return result;
    }

    static class Result {
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

    static class User {
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
