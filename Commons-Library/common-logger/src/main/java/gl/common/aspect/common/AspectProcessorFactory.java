package gl.common.aspect.common;

import org.aspectj.lang.ProceedingJoinPoint;


public abstract class AspectProcessorFactory {

	 public abstract AspectProcessor getProcessor(ProceedingJoinPoint pjp);
}
