package com.jakub.hrm.query.hruserprofile;


import com.jakub.hrm.annotation.FieldsValueMatch;
import com.jakub.hrm.annotation.ValidPassword;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldsValueMatch(
        field = "pwd",
        fieldMatch = "confirmPwd",
        message = "Passwords do not match!"
)
public class GetCurrentHrUserPasswordByIdQuery {

    @ValidPassword
    @NotBlank(message="Password must not be blank")
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Transient
    private String confirmPwd;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    private String currentPassword;

    public GetCurrentHrUserPasswordByIdQuery() {
    }

    public GetCurrentHrUserPasswordByIdQuery(String currentPassword) {
        this.currentPassword = currentPassword;
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

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}
