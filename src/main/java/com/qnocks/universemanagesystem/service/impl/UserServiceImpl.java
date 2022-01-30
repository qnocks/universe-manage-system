package com.qnocks.universemanagesystem.service.impl;

import com.qnocks.universemanagesystem.domain.User;
import com.qnocks.universemanagesystem.exception.ResourceNotFoundException;
import com.qnocks.universemanagesystem.repository.UserRepository;
import com.qnocks.universemanagesystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        // TODO: Check unique credentials before actual saving
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Cannot find user with username {}", username)));
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cannot find user with id {}", id)));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
