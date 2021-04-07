package com.rbkmoney.proxy.mocketbank.mpi.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties("mpi20")
public class Mpi20Properties {

    private String url;
    private String threeDsServerUrl;
    private String sharedKey;
    private String callbackUrl;
    private String pathCallbackUrl;
    private String pathRecurrentCallbackUrl;
    private String pathNotificationAcsUrl;
    private String pathPrepareAcsUrl;
    private String pathResultAcsUrl;
    private String returnUrl;

}
