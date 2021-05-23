package com.company.coursework.pizza.District;

import java.util.Objects;

public class District {
    private final String title;
    private String description;

    public District(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public District(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        District district = (District) o;
        return Objects.equals(title, district.title);
    }

    public String toString() {
        return title + ": " + description;
    }
}
