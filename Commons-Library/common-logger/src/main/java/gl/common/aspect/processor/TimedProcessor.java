package gl.common.aspect.processor;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gl.common.aspect.common.AspectProcessor;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Timer.Sample;

public class TimedProcessor extends AspectProcessor {

	Sample timer;
	Logger log;
	String signature;

	public TimedProcessor(ProceedingJoinPoint pjp) {
		this.signature = pjp.getSignature().toShortString();
		log = LoggerFactory.getLogger(pjp.getTarget().getClass());
	}

	@Override
	public void start() {
		timer=Timer.start();
	}

	@Override
	public void stop(Object retValue) {
		long time = timer.stop(Metrics.timer(signature, "result", retValue==null?"<null>":retValue.getClass().getCanonicalName()));

		log.info("Timed: {} {}ms", signature, TimeUnit.NANOSECONDS.toMillis(time));
	}

	@Override
	public void exception(Throwable t) {
		long time = timer.stop(Metrics.timer(signature, "result", t.getClass().getCanonicalName()));
		log.warn("Timed: {} {}ms", signature, TimeUnit.NANOSECONDS.toMillis(time));
	}

}
