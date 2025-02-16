package programmerzamannow.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "programmerzamannow.spring.core.service",
    "programmerzamannow.spring.core.repository",
    "programmerzamannow.spring.core.configuration"
})
public class ComponentConfiguration {
}
