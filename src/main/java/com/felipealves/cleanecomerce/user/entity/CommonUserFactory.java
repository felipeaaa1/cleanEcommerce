package com.felipealves.cleanecomerce.user.entity;


import com.felipealves.cleanecomerce.user.interfaceAdapter.UserType;

public class CommonUserFactory  implements UserFactory {


    @Override
    public User create(String name, String email, String password, UserType userType) {
        return new CommonUser(name, email, password, userType);
    }
}
