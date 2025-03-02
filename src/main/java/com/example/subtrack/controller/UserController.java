package com.example.subtrack.controller;

import com.example.subtrack.dto.NewUserRequest;
import com.example.subtrack.dto.ResponseUserDto;
import com.example.subtrack.dto.UpdateUserRequest;
import com.example.subtrack.dto.UserDto;
import com.example.subtrack.dto.mappers.UserMapper;
import com.example.subtrack.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Контроллер для управления пользователями.
 * Обрабатывает запросы, связанные с созданием, обновлением, получением и удалением пользователей.
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Создает нового пользователя.
     *
     * @param dto данные для создания нового пользователя.
     * @return UUID созданного пользователя.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@Valid @RequestBody NewUserRequest dto) {
        return userService.createUser(userMapper.toUserDtoFromNewUserRequest(dto));
    }

    /**
     * Обновляет данные пользователя.
     *
     * @param userId идентификатор пользователя.
     * @param dto    данные для обновления пользователя.
     * @return UUID обновленного пользователя.
     */
    @PatchMapping("/{userId}")
    public UUID updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRequest dto) {
        UserDto userDto = userMapper.toUserDtoFromUpdateUserRequest(dto);
        userDto.setId(userId);
        return userService.updateUser(userDto);
    }

    /**
     * Получает информацию о пользователе по его идентификатору.
     *
     * @param userId идентификатор пользователя.
     * @return объект {@link ResponseUserDto}, содержащий данные пользователя.
     */
    @GetMapping("/{userId}")
    public ResponseUserDto getUserById(@PathVariable UUID userId) {
        return userMapper.toResponseUserDtoFromUserDto(userService.getUserById(userId));
    }

    /**
     * Удаляет пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя.
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }


}