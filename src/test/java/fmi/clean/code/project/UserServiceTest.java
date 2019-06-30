package fmi.clean.code.project;


import fmi.clean.code.project.exceptions.InvalidInputException;
import fmi.clean.code.project.mappers.UserMapper;
import fmi.clean.code.project.models.dtos.UserLoginDto;
import fmi.clean.code.project.models.dtos.UserRegisterDto;
import fmi.clean.code.project.models.entities.User;
import fmi.clean.code.project.repositories.UserRepository;
import fmi.clean.code.project.services.UserServiceImpl;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;


import static fmi.clean.code.project.Constants.CONFIRM_PASSWORD;
import static fmi.clean.code.project.Constants.TEST_EMAIL;
import static fmi.clean.code.project.Constants.TEST_PASS;
import static fmi.clean.code.project.Constants.TEST_USERNAME;
import static fmi.clean.code.project.helpers.Constants.SUCCESSFULLY_LOGIN;
import static fmi.clean.code.project.helpers.Constants.SUCCESSFULLY_REGISTERED;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  private UserRegisterDto userRegisterDto;
  private UserLoginDto userLoginDto;

  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private UserMapper userMapper;

  @InjectMocks
  private UserServiceImpl userService;

  @Before
  public void init() {
    userRegisterDto = new UserRegisterDto(TEST_EMAIL, TEST_USERNAME, TEST_PASS, TEST_PASS);
    userLoginDto = new UserLoginDto(TEST_USERNAME, TEST_PASS);
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = InvalidInputException.class)
  public void register_usernameExists() throws InvalidInputException {
    when(userRepository.existsByUsername(anyString())).thenReturn(true);
    userService.register(userRegisterDto);
  }

  @Test(expected = InvalidInputException.class)
  public void register_emailExists() throws InvalidInputException {
    when(userRepository.existsByUsername(anyString())).thenReturn(false);
    when(userRepository.existsByEmail(anyString())).thenReturn(true);
    userService.register(userRegisterDto);
  }

  @Test(expected = InvalidInputException.class)
  public void register_passwordsDontMatch() throws InvalidInputException {
    when(userRepository.existsByUsername(anyString())).thenReturn(false);
    when(userRepository.existsByEmail(anyString())).thenReturn(false);

    userRegisterDto.setConfirmPassword(CONFIRM_PASSWORD);
    userService.register(userRegisterDto);
  }

  @Test
  public void register_successfullyRegistered() throws InvalidInputException {
    when(userRepository.existsByUsername(anyString())).thenReturn(false);
    when(userRepository.existsByEmail(anyString())).thenReturn(false);
    User user = new User();
    when(userMapper.registerDtoToUser(userRegisterDto)).thenReturn(user);

    String response = userService.register(userRegisterDto);

    Assert.assertEquals(response, SUCCESSFULLY_REGISTERED);
    verify(userRepository).save(any(User.class));
  }

  @Test(expected = InvalidInputException.class)
  public void login_invalidUsername() throws InvalidInputException {
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

    userService.login(userLoginDto);

    verify(userRepository).findByUsername(anyString());
  }

  @Test(expected = InvalidInputException.class)
  public void login_invalidPassword() throws InvalidInputException {
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(new User()));

    userService.login(userLoginDto);
    this.verifyCallsWhenLogin();
  }

  @Test
  public void login_successfullyLogged() throws InvalidInputException {
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(new User("pass")));
    when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

    String response = userService.login(userLoginDto);

    Assert.assertEquals(response, SUCCESSFULLY_LOGIN);
    this.verifyCallsWhenLogin();
  }

  private void verifyCallsWhenLogin() {
    verify(userRepository).findByUsername(anyString());
    verify(passwordEncoder).matches(anyString(), anyString());
  }
}