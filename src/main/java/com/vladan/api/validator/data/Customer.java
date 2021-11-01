package com.vladan.api.validator.data;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Customer {
    private @Id @GeneratedValue Long id;
    private @NotNull @Size(min = 2) String name;
    private @NotNull @Size(min = 9, max = 9) String ssn;
    private @NotNull @Pattern(regexp="\\([0-9]{3}\\)\\-[0-9]{3}\\-[0-9]{4}", message = "Please provide a valid phone number, ex. (123)-456-7890") String phone;
    private @NotNull @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address") String email;
    private @NotNull @Past LocalDate dateOfBirth;

    Customer() {}

    Customer (String name, String ssn, String phone, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.ssn = ssn;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(ssn, customer.ssn) && Objects.equals(phone, customer.phone) && Objects.equals(email, customer.email) && Objects.equals(dateOfBirth, customer.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ssn, phone, email, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
