package com.test.akkaTest.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorSystem;

@Configuration
public class SpringContext implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContext.applicationContext = applicationContext;
	}
	
	public static ActorSystem getActorSystem() {
		return (ActorSystem)applicationContext.getBean(ActorSystem.class);
	}
}
