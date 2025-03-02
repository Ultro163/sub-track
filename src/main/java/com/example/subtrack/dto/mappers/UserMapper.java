package com.example.subtrack.dto.mappers;

import com.example.subtrack.dto.NewUserRequest;
import com.example.subtrack.dto.ResponseUserDto;
import com.example.subtrack.dto.UpdateUserRequest;
import com.example.subtrack.dto.UserDto;
import com.example.subtrack.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toUserDto(User user);

    UserDto toUserDtoFromNewUserRequest(NewUserRequest newUserRequest);

    UserDto toUserDtoFromUpdateUserRequest(UpdateUserRequest updateUserRequest);

    ResponseUserDto toResponseUserDtoFromUserDto(UserDto userDto);
}