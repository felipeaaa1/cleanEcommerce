package com.felipealves.cleanecomerce.user.entity;

import com.felipealves.cleanecomerce.user.interfaceAdapter.UserType;

public interface User {
    boolean passwordIsValid();

    boolean nameIsValid();

    String getName();

    String getEmail();

    String getPassword();

    UserType getUserType();
}
