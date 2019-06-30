package fmi.clean.code.project.controllers;

import fmi.clean.code.project.exceptions.InvalidInputException;
import fmi.clean.code.project.helpers.ResponseMessage;
import fmi.clean.code.project.models.dtos.UserLoginDto;
import fmi.clean.code.project.models.dtos.UserRegisterDto;
import fmi.clean.code.project.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/login")
  public ResponseMessage login(@Valid @RequestBody UserLoginDto userLogin) throws InvalidInputException {
    return new ResponseMessage(HttpStatus.OK, userService.login(userLogin));
  }

  @PostMapping(value = "/register")
  public ResponseMessage register(@Valid @RequestBody UserRegisterDto userRegister) throws InvalidInputException {
    return new ResponseMessage(HttpStatus.OK, userService.register(userRegister));
  }
}
