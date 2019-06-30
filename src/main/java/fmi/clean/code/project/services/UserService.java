package fmi.clean.code.project.services;

import fmi.clean.code.project.exceptions.InvalidInputException;
import fmi.clean.code.project.models.dtos.UserLoginDto;
import fmi.clean.code.project.models.dtos.UserRegisterDto;
import fmi.clean.code.project.models.entities.User;

public interface UserService {

  String register(UserRegisterDto userRegisterDto) throws InvalidInputException;

  String login(UserLoginDto userLoginDto) throws InvalidInputException;

  User getById(Long id);

  User save(User user);
}
