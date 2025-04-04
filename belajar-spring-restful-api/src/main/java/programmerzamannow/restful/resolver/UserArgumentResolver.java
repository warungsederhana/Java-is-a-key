package programmerzamannow.restful.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.repository.UserRepository;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

  @Autowired
  private UserRepository userRepository;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return User.class.equals(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
    String token = httpServletRequest.getHeader("X-API-TOKEN");
    if (token == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
    }

    User user = userRepository.findFirstByToken(token)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED"));

    if (user.getTokenExpiredAt() < System.currentTimeMillis()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
    }

    return user;
  }
}
