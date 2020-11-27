package gl.common.aspect.processor.factory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import gl.common.aspect.common.AspectProcessor;
import gl.common.aspect.common.AspectProcessorFactory;
import gl.common.aspect.processor.TimedProcessor;


@Component
@Order(90)
@ConditionalOnExpression("${gl.logger.timed.enabled:true}")
public class TimedProcessorFactory extends AspectProcessorFactory {

	@Override
	public AspectProcessor getProcessor(ProceedingJoinPoint pjp) {
		return new TimedProcessor(pjp);
	}

}
