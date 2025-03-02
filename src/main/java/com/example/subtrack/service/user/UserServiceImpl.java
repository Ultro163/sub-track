package com.example.subtrack.service.user;

import com.example.subtrack.dto.UserDto;
import com.example.subtrack.dto.mappers.UserMapper;
import com.example.subtrack.entity.User;
import com.example.subtrack.error.exception.EntityNotFoundException;
import com.example.subtrack.error.exception.ValidationException;
import com.example.subtrack.repository.UserRepository;
import com.example.subtrack.repository.UserSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Реализация сервиса для управления пользователями.
 * <p>
 * Предоставляет методы для создания, получения, обновления и удаления пользователей.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserMapper userMapper;

    /**
     * Создает нового пользователя.
     *
     * @param dto объект {@link UserDto}, содержащий информацию о новом пользователе.
     * @return UUID созданного пользователя.
     * @throws ValidationException если email уже существует в системе.
     */
    @Override
    public UUID createUser(UserDto dto) {
        existsByEmail(dto.getEmail());
        User newUser = userMapper.toEntity(dto);
        return userRepository.save(newUser).getId();
    }

    /**
     * Получает пользователя по его идентификатору.
     *
     * @param userId UUID пользователя.
     * @return объект {@link UserDto} с данными пользователя.
     * @throws EntityNotFoundException если пользователь не найден или не активен.
     */
    @Override
    public UserDto getUserById(UUID userId) {
        return userMapper.toUserDto(userRepository.findByIdAndIsActive(userId, Boolean.TRUE).orElseThrow(() -> userNotFound(userId)));
    }

    /**
     * Обновляет данные существующего пользователя.
     *
     * @param dto объект {@link UserDto} с обновленной информацией.
     * @return UUID обновленного пользователя.
     * @throws EntityNotFoundException если пользователь не найден или не активен.
     * @throws ValidationException     если новый email уже используется другим пользователем.
     */
    @Override
    public UUID updateUser(UserDto dto) {
        User existsUser = userRepository.findByIdAndIsActive(dto.getId(), Boolean.TRUE).orElseThrow(() -> userNotFound(dto.getId()));
        if (dto.getEmail() != null) {
            existsByEmail(dto.getEmail());
            existsUser.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            existsUser.setPassword(dto.getPassword());
        }
        if (dto.getFirstName() != null) {
            existsUser.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            existsUser.setLastName(dto.getLastName());
        }
        return userRepository.save(existsUser).getId();
    }

    /**
     * Деактивирует пользователя, устанавливая флаг isActive в false, и отключает его подписки.
     *
     * @param userId UUID пользователя, которого нужно удалить.
     * @throws EntityNotFoundException если пользователь не найден или уже неактивен.
     */
    @Override
    public void deleteUser(UUID userId) {
        User existsUser = userRepository.findByIdAndIsActive(userId, Boolean.TRUE).orElseThrow(() -> userNotFound(userId));
        existsUser.setIsActive(false);
        userSubscriptionRepository.deactiveSubscriptions(userId);
    }

    /**
     * Создает и выбрасывает исключение {@link EntityNotFoundException}, если пользователь не найден.
     *
     * @param userId UUID пользователя.
     * @throws EntityNotFoundException всегда.
     */
    private EntityNotFoundException userNotFound(UUID userId) {
        throw new EntityNotFoundException("User with id " + userId + " not found");
    }

    /**
     * Проверяет, существует ли пользователь с указанным email.
     * Если такой email уже зарегистрирован, выбрасывается {@link ValidationException}.
     *
     * @param email email пользователя.
     * @throws ValidationException если email уже существует.
     */
    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ValidationException("Email already exists");
        }
    }
}