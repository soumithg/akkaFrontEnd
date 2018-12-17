package com.test.akkaTest.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.akkaTest.actor.SimpleActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Configuration
public class AkkaConfiguration {
	
	
	@Bean
	public ActorSystem actorSystem() {
		
		ActorSystem actorSystem = ActorSystem.create("actor-system");
		
		return actorSystem;
	}
	
	
	@Bean
	public ActorRef simpleActorReference(@Qualifier("actorSystem") ActorSystem actorSystem) {
		
		 ActorRef actorRef = actorSystem.actorOf(Props.create(SimpleActor.class), "simple-actor");
		
		return actorRef;
	}

}
