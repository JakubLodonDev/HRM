package com.jakub.hrm.model;


import com.jakub.hrm.annotation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "hr_identification")
public class HrIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identification_id")
    private UUID identificationId;
    @NotBlank(message="Email must not be blank")
    private String login;
    @ValidPassword
    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Size(min=5, message="Confirm Password must be at least 5 characters long")
    @Transient
    private String confirmPwd;

    public HrIdentification() {
    }

    public HrIdentification(String login, String pwd) {
        this.login = login;
        this.pwd = pwd;
    }

    public UUID getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(UUID identificationId) {
        this.identificationId = identificationId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
}
