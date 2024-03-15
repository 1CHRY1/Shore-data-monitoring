package nnu.edu.Shore.common.config;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Chry
 * @Date: 2024/3/15 10:01
 * @Description:
 */

@Configuration
@EnableConfigurationProperties
public class portConfig {

    @Value("${server.additionalPorts}")
    String additionalPorts;

    public class TomcatWebServerHttpPortCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
        @Override
        public void customize(ConfigurableServletWebServerFactory factory) {
            if (additionalPorts != null && !additionalPorts.isEmpty()) {
                for (String port : additionalPorts.split(",")) {
                    int portNumber = Integer.parseInt(port.trim());
                    if (portNumber > 0) {
                        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                        connector.setPort(portNumber);
                        ((TomcatServletWebServerFactory) factory).addAdditionalTomcatConnectors(connector);
                    }
                }
            }
        }
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return new TomcatWebServerHttpPortCustomizer();
    }
}
