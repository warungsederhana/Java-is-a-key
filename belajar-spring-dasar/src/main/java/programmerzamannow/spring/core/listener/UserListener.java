package programmerzamannow.spring.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import programmerzamannow.spring.core.event.LoginSuccessEvent;

@Component
@Slf4j
public class UserListener {

  @EventListener
  public void onLoginSuccessEvent(LoginSuccessEvent event) {
    log.info("[FROM USER LISTENER] Success login for user: {}", event.getUser().getUsername());
  }

}
