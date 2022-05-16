package com.restapijwt.dto;

import com.restapijwt.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username, email;

    private String role; //ROle.User moderator

}
