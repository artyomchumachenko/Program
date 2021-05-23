package com.company.coursework.pizza.Users;

public class User {
    private String login;
    private String password;
    private String nickname;

    public User(String nickname, String login, String password) {
        this.nickname = nickname;
        this.login = login;
        this.password = password;
    }

    public boolean enter(User user) {
        return (this.password.equals(user.password) && this.login.equals(user.login));
    }

    public boolean enter(String login, String password) {
        return (this.login.equals(login) && this.password.equals(password));
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
