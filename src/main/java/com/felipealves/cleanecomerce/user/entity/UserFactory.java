package com.felipealves.cleanecomerce.user.entity;

import com.felipealves.cleanecomerce.user.interfaceAdapter.UserType;

public interface UserFactory {
    User create(String name, String email, String password, UserType userType);
}
