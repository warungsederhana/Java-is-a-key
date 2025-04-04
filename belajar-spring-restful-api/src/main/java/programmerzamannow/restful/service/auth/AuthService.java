package programmerzamannow.restful.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.user.LoginUserRequest;
import programmerzamannow.restful.model.user.TokenResponse;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;
import programmerzamannow.restful.service.ValidationService;

import java.util.UUID;

@Service
public class AuthService implements AuthServiceI {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ValidationService validationService;

  private Long next30Days() {
    return System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000);
  }

  @Override
  @Transactional
  public TokenResponse login(LoginUserRequest request) {
    validationService.validate(request);

    User user = userRepository.findById(request.getUsername())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

    if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
      user.setToken(UUID.randomUUID().toString());
      user.setTokenExpiredAt(next30Days());
      userRepository.save(user);

      return TokenResponse.builder()
          .token(user.getToken())
          .expiredAt(user.getTokenExpiredAt())
          .build();
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }
  }

  @Override
  @Transactional
  public void logout(User user) {
    user.setToken(null);
    user.setTokenExpiredAt(null);
    userRepository.save(user);
  }
}
