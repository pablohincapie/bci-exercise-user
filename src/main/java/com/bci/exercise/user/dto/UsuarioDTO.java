package com.bci.exercise.user.dto;

import java.util.List;

public class UsuarioDTO {

    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public UsuarioDTO(String name, String email, String password, List<PhoneDTO> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }
    public UsuarioDTO() {

    }
}
