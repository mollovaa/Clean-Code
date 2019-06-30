package fmi.clean.code.project.services;

import fmi.clean.code.project.exceptions.InvalidInputException;
import fmi.clean.code.project.mappers.UserMapper;
import fmi.clean.code.project.models.dtos.UserLoginDto;
import fmi.clean.code.project.models.dtos.UserRegisterDto;
import fmi.clean.code.project.models.entities.User;
import fmi.clean.code.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static fmi.clean.code.project.helpers.Constants.EMAIL_ALREADY_EXISTS;
import static fmi.clean.code.project.helpers.Constants.INVALID_PASSWORD;
import static fmi.clean.code.project.helpers.Constants.INVALID_USERNAME;
import static fmi.clean.code.project.helpers.Constants.PASSWORDS_DON_T_MATCH;
import static fmi.clean.code.project.helpers.Constants.SUCCESSFULLY_LOGIN;
import static fmi.clean.code.project.helpers.Constants.SUCCESSFULLY_REGISTERED;
import static fmi.clean.code.project.helpers.Constants.USERNAME_ALREADY_EXISTS;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private UserMapper userMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  @Override
  public String register(UserRegisterDto userRegisterDto) {
    if (userRepository.existsByUsername(userRegisterDto.getUsername())) {
      throw new InvalidInputException(USERNAME_ALREADY_EXISTS);
    }
    if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
      throw new InvalidInputException(EMAIL_ALREADY_EXISTS);
    }
    if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
      throw new InvalidInputException(PASSWORDS_DON_T_MATCH);
    }
    return saveUser(userRegisterDto);
  }

  @Override
  public String login(UserLoginDto userLoginDto) throws InvalidInputException {
    User user = userRepository.findByUsername(userLoginDto.getUsername())
        .orElseThrow(() -> new InvalidInputException(INVALID_USERNAME));
    if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
      throw new InvalidInputException(INVALID_PASSWORD);
    }
    return SUCCESSFULLY_LOGIN;
  }

  private String saveUser(UserRegisterDto userRegisterDto) {
    User user = userMapper.registerDtoToUser(userRegisterDto);
    user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
    userRepository.save(user);
    return SUCCESSFULLY_REGISTERED;
  }
}