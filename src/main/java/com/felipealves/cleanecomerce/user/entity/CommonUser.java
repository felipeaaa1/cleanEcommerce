package com.felipealves.cleanecomerce.user.entity;

import com.felipealves.cleanecomerce.user.entity.User;
import com.felipealves.cleanecomerce.user.interfaceAdapter.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class CommonUser implements User {

    private String name;
    private String email;
    private String password;
    private UserType userType;

    public CommonUser(String name, String email, String password, UserType userType) {
        this.name = name;
        this.email = email ;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public boolean passwordIsValid() {
        // Validação básica de senha, pode ser expandida conforme as regras definidas
        return password != null && password.length() > 8
                && password.matches(".*\\d.*")       // Deve conter ao menos um número
                && password.matches(".*[A-Z].*")    // Deve conter ao menos uma letra maiúscula
                && password.matches(".*[a-z].*")    // Deve conter ao menos uma letra minúscula
                && password.matches(".*\\W.*");     // Deve conter ao menos um caractere especial
    }

    @Override
    public boolean nameIsValid() {
        return name != null && name.length() > 2;
    }

    @Override
    public UserType getUserType() {
        return this.userType;
    }

    @Override
    public String getPassword() { return this.password; }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

}
