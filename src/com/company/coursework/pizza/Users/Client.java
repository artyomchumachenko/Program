package com.company.coursework.pizza.Users;

import com.company.coursework.pizza.District.District;

public class Client extends User {
    private District district;

    public Client(String nickname, String login, String password) {
        super(nickname, login, password);
//        this.district = district;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
