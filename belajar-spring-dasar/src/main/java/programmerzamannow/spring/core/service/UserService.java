package programmerzamannow.spring.core.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import programmerzamannow.spring.core.data.User;
import programmerzamannow.spring.core.event.LoginSuccessEvent;

@Component
public class UserService implements ApplicationEventPublisherAware {

  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public boolean login(String username, String password) {
    if (isLoginSuccess(username, password)) {
      applicationEventPublisher.publishEvent(new LoginSuccessEvent(new User(username)));
      return true;
    }
    return false;
  }

  private boolean isLoginSuccess(String username, String password) {
    return "gybran".equals(username) && "gybran".equals(password);
  }


}
