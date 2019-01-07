package com.test.akkaTest;
import akka.stream.*;
import akka.stream.javadsl.*;
import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.util.ByteString;

import java.nio.file.Paths;
import java.math.BigInteger;
import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;



public class AkkaStreamsTest {

	public static void main(String[] args) {

		final ActorSystem system = ActorSystem.create("QuickStart");
		final Materializer materializer = ActorMaterializer.create(system);

		final Source<Integer, NotUsed> source = Source.range(1, 100);

		final CompletionStage<Done> done =
				source.runForeach(i -> System.out.println(i), materializer);

		done.thenRun(() -> system.shutdown());

//
//		// convert bug integers from one to binary objects
//		// run the source with stream of big integers
//		//run the source and and store the stream of data to 
//		
//		final Source<BigInteger, NotUsed> factorials =
//				source
//				.scan(BigInteger.ONE, (acc, next) -> acc.multiply(BigInteger.valueOf(next)));
//
//		final CompletionStage<IOResult> result =
//				factorials
//				.map(num -> ByteString.fromString(num.toString() + "\n"))
//				.runWith(FileIO.toPath(Paths.get("factorials.txt")), materializer);
//
//
//		
//		CompletionStage<IOResult> result2 = 
//				factorials
//				.map(BigInteger::toString)
//				.runWith(lineSink("factorial2.txt"), materializer);
//		
//		factorials
//		  .zipWith(Source.range(0, 99), (num, idx) -> String.format("%d! = %s", idx, num))
//		  .throttle(1, Duration.ofSeconds(1))
//		  .runForeach(s -> System.out.println(s), materializer);
//		
		
	}
	
	public static Sink<String, CompletionStage<IOResult>> lineSink(String filename) {
		  return Flow.of(String.class)
		    .map(s -> ByteString.fromString(s.toString() + "\n"))
		    .toMat(FileIO.toPath(Paths.get(filename)), Keep.right());
		}

}
