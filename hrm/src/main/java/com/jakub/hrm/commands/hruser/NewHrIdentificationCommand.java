package com.jakub.hrm.commands.hruser;

import com.jakub.hrm.annotation.FieldsValueMatch;
import com.jakub.hrm.annotation.ValidPassword;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;


@FieldsValueMatch(
        field = "pwd",
        fieldMatch = "confirmPwd",
        message = "Passwords do not match!"
)
public class NewHrIdentificationCommand {

    @ValidPassword
    @NotBlank(message="Password must not be blank")
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Transient
    private String confirmPwd;

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
