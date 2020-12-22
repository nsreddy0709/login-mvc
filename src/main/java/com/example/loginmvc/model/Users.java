package com.example.loginmvc.model;


import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;



import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")


public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;

    @NotNull(message = "first name should not be null")
    @Size(min = 2, message = "First Name should have atleast 2 characters")
    @Column(name="first_name")
    private String first_name;
    @NotNull(message = "last name should not be null")
    @Size(min = 2, message = "First Name should have atleast 2 characters")
    @Column(name="last_name")
    private String last_name;
    @Email(message = "Please enter valid email address")
    @Column(name="email")
    private String email;
    @Length(min = 5,message = "Password must be atleast 5 characters")
    @Column(name="password")
    private String password;
    @Pattern(regexp = "(^$|[9]{1}[0-9]{9})",message = "Mobile number must be a valid 10 digit number")
    @Column(name="mobile")
    private String mobile;
    @NotNull(message = "Please fill this field")
    @Column(name="address1")
    private String address1;
    @Column(name="address2")
    private String address2;

    public Users() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
