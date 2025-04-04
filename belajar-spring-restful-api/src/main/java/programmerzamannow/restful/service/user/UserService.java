package programmerzamannow.restful.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.user.RegisterUserRequest;
import programmerzamannow.restful.model.user.UpdateUserRequest;
import programmerzamannow.restful.model.user.UserResponse;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;
import programmerzamannow.restful.service.ValidationService;

import java.util.Objects;

@Service
public class UserService implements UserServiceI {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ValidationService validationService;

  @Transactional
  public void register(RegisterUserRequest request) {
    validationService.validate(request);

    if (userRepository.existsById(request.getUsername())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
    }

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    user.setName(request.getName());

    userRepository.save(user);
  }

  @Override
  public UserResponse get(User user) {
    return UserResponse.builder().username(user.getUsername()).name(user.getName()).build();
  }

  @Override
  @Transactional
  public UserResponse update(User user, UpdateUserRequest request) {
    validationService.validate(request);

    if(Objects.nonNull(request.getName())) {
      user.setName(request.getName());
    }

    if (Objects.nonNull(request.getPassword())) {
      user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    }

    userRepository.save(user);
    return UserResponse.builder()
        .name(user.getName())
        .username(user.getUsername())
        .build();
  }
}
