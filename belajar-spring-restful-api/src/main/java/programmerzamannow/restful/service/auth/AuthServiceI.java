package programmerzamannow.restful.service.auth;

import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.user.LoginUserRequest;
import programmerzamannow.restful.model.user.TokenResponse;

public interface AuthServiceI {

  public TokenResponse login(LoginUserRequest request);

  public void logout(User user);
}
