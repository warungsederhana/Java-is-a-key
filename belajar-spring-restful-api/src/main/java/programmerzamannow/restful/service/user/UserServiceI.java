package programmerzamannow.restful.service.user;

import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.user.RegisterUserRequest;
import programmerzamannow.restful.model.user.UpdateUserRequest;
import programmerzamannow.restful.model.user.UserResponse;

public interface UserServiceI {

  public void register(RegisterUserRequest request);

  public UserResponse get(User user);

  public UserResponse update(User user, UpdateUserRequest request);
}
