package com.company.dz.three.onlinestore;

public class User {
    private String login;
    private String password;
    private Basket myBasket;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        myBasket = new Basket();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Basket getMyBasket() {
        return myBasket;
    }
}
