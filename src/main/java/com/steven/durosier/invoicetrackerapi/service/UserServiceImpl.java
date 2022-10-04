package com.steven.durosier.invoicetrackerapi.service;

import com.steven.durosier.invoicetrackerapi.entity.User;
import com.steven.durosier.invoicetrackerapi.exception.ObjectAlreadyExistsException;
import com.steven.durosier.invoicetrackerapi.exception.ResourceNotFoundException;
import com.steven.durosier.invoicetrackerapi.repository.UserRepository;
import com.steven.durosier.invoicetrackerapi.request.CreateUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(final CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            final String error = String.format("User with email %s already exists", request.getEmail());
            throw new ObjectAlreadyExistsException(error);
        }

        User newUser = new User();
        BeanUtils.copyProperties(request, newUser);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id" + id));
    }

    @Override
    public User getLoggedInUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String email = authentication.getName();

        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for email " + email));
    }
}
