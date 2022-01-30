package com.qnocks.universemanagesystem.service;

import com.qnocks.universemanagesystem.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getAll();

    User getByUsername(String username);

    User getById(Long id);

    void delete(Long id);
}
