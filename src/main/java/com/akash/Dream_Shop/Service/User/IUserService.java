package com.akash.Dream_Shop.Service.User;

import com.akash.Dream_Shop.DTO.UserDto;
import com.akash.Dream_Shop.Model.User;
import com.akash.Dream_Shop.Request.CreateUserRequest;
import com.akash.Dream_Shop.Request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);

    User createUser(CreateUserRequest request);

    User updateUser(UserUpdateRequest request, Long userId);

    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}