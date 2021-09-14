package com.project.service.taxi.mapper;

import com.project.service.taxi.dto.UserRequestDTO;
import com.project.service.taxi.entity.User;
import com.project.service.taxi.entity.erole.RoleUser;

public class UserMapper {
    public static User fromUserRequestDTOToUser(UserRequestDTO userRequestDTO) {
        User users = new User();
        users.setLogin(userRequestDTO.getLogin());
        users.setPassword(userRequestDTO.getPassword());
        if (userRequestDTO.getRole() == null) {
            users.setRole(RoleUser.ROLE_USER);
        } else {
            users.setRole(userRequestDTO.getRole());
        }
        return users;
    }
}
