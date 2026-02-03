package org.example.sql_connect.entity;

import java.util.Objects;

public class Shopper extends BaseEntity {
    private String fullName;
    private String email;
    private String phone;

    public Shopper() {}

    public Shopper(int shopperId, String fullName, String email, String phone) {
        super(shopperId);
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public int getShopperId() { return id; }
    public void setShopperId(int shopperId) { this.id = shopperId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Shopper{" +
                "shopperId=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Shopper shopper = (Shopper) o;
        return Objects.equals(fullName, shopper.fullName) &&
                Objects.equals(email, shopper.email) &&
                Objects.equals(phone, shopper.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fullName, email, phone);
    }
}
