package com.test.akkaTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import akka.actor.ActorRef;

@RestController
@RequestMapping(value="/test")
public class TestController {
	
	private final static Logger log = LoggerFactory.getLogger(TestController.class);

	@Autowired
	@Qualifier("simpleActorReference")
	private ActorRef simpleActorReference;

	@RequestMapping(value="/message", method=RequestMethod.POST)
	public void postTestMessage(@RequestParam(required=true)String message) {
		log.debug("postTestMessage: Sending message to local actor={}", message);
		simpleActorReference.tell(message, null);
		log.debug("postTestMessage: sent message successfully={}", message);
	}
}
