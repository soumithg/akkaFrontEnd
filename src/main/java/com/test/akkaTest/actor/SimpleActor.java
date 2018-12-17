/**
 * (C) Copyright 2014 Roy Russo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 *
 */


package com.test.akkaTest.actor;

import com.test.akkaTest.configuration.SpringContext;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * SimpleActor receives an instance of Command and emits an Event.
 *
 */
public class SimpleActor extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);


	public SimpleActor() {
		log.info("SimpleActor constructor");
	}

	@Override
	public void onReceive(Object msg) throws Exception {

		log.info("Received Command: " + msg);

		// emit an event to remote host
		ActorSystem actorSystem = SpringContext.getActorSystem();
		ActorSelection actorSelection = actorSystem.actorSelection("akka.tcp://actor-system@127.0.0.1:2551/user/complex-actor");

		actorSelection.tell(msg, getSelf());
	}
}
