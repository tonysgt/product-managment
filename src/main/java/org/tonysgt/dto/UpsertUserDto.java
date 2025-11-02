package org.tonysgt.dto;

public class UpsertUserDto extends UserDto{

    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
