package com.felipealves.cleanecomerce.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.felipealves.cleanecomerce.user.entity.CommonUser;
import com.felipealves.cleanecomerce.user.interfaceAdapter.UserType;
import org.junit.jupiter.api.Test;

class CommonUserTest {

    @Test
    void givenValidPassword_whenPasswordIsValid_thenReturnsTrue() {
//      senha válida de acordo com documentação, é para retornar True
        CommonUser user = new CommonUser("Felipe", "felipe@teste.com", "Senha123!", UserType.CLIENT);
        assertThat(user.passwordIsValid()).isTrue();
        assertThat(user.nameIsValid()).isTrue();
    }

    @Test
    void givenShortPassword_whenPasswordIsNotValid_thenReturnsFalse() {
//      senha muito curta, é para retornar False
        CommonUser user = new CommonUser("Felipe","felipe@teste.com",  "123", UserType.CLIENT);
        assertThat(user.passwordIsValid()).isFalse();
    }

    @Test
    void givenPasswordWithoutSpecialCharacter_whenPasswordIsNotValid_thenReturnsFalse() {
//      senha sem caracteres especiais, é para retornar False
        CommonUser user = new CommonUser("Felipe", "felipe@teste.com", "Senha123", UserType.CLIENT);
        assertThat(user.passwordIsValid()).isFalse();
    }

    @Test
    void givenNullPassword_whenPasswordIsNotValid_thenReturnsFalse() {
//      senha nula, é para retornar False
        CommonUser user = new CommonUser("Felipe", "felipe@teste.com", null, UserType.CLIENT);
        assertThat(user.passwordIsValid()).isFalse();
    }

    @Test
    void givenNullName_thenReturnsFalse() {
//      senha nula, é para retornar False
        CommonUser user = new CommonUser(null, "felipe@teste.com", "Senha123", UserType.CLIENT);
        assertThat(user.nameIsValid()).isFalse();
    }

    @Test
    void givenShortName_thenReturnsFalse() {
//      senha nula, é para retornar False
        CommonUser user = new CommonUser("us", "felipe@teste.com", "Senha123", UserType.CLIENT);
        assertThat(user.nameIsValid()).isFalse();
    }

}
