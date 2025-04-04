package programmerzamannow.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.model.user.RegisterUserRequest;
import programmerzamannow.restful.model.user.UpdateUserRequest;
import programmerzamannow.restful.model.user.UserResponse;
import programmerzamannow.restful.service.user.UserServiceI;

@RestController
public class UserController {

  @Autowired
  private UserServiceI userService;

  @PostMapping(
      path = "/api/users",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
    userService.register(request);
    return WebResponse.<String>builder().data("OK").build();
  }


  @GetMapping(
      path = "/api/users/current",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<UserResponse> get(User user) {
    UserResponse userResponse = userService.get(user);
    return WebResponse.<UserResponse>builder()
        .data(userResponse)
        .build();
  }

  @PatchMapping(
      path = "/api/users/current",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<UserResponse> update(User user, @RequestBody UpdateUserRequest request) {
    UserResponse userResponse = userService.update(user, request);
    return WebResponse.<UserResponse>builder()
        .data(userResponse)
        .build();
  }
}
