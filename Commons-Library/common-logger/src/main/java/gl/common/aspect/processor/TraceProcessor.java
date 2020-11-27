package gl.common.aspect.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gl.common.aspect.common.AspectProcessor;


public class TraceProcessor extends AspectProcessor {

	String traceId;
	Logger log;
	
	public TraceProcessor(ProceedingJoinPoint pjp) {
		traceId = pjp.getSignature().toShortString();
		log = LoggerFactory.getLogger(pjp.getTarget().getClass());
	}
	
	@Override
	public void start() {
		log.info("Entering: {}",traceId);
	}
	
	@Override
	public void stop(Object retValue) {
		log.info("Exiting: {} returning: {}",traceId, retValue==null?"<null>":retValue.getClass().getCanonicalName());
	}
	
	@Override
	public void exception(Throwable t) {
		log.warn("Exiting: {} Exception: {}",traceId,t.getClass().getCanonicalName());
	}

}
