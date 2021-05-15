package com.nautilus.trigger.app.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "custom")
public class ApplicationProperties {

	private TransferConsumerProperties transferConsumer;
	private AccountsConsumerProperties accountsConsumer;
	private BalanceConsumerProperties balanceConsumer;
	private NCBBalanceConsumerProperties ncbBalanceConsumer;

}
