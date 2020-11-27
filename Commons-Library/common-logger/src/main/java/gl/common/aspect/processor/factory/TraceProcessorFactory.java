package gl.common.aspect.processor.factory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import gl.common.aspect.common.AspectProcessor;
import gl.common.aspect.common.AspectProcessorFactory;
import gl.common.aspect.processor.TraceProcessor;

@Component
@Order(100)
@ConditionalOnExpression("${gl.logger.traced.enabled:true}")
public class TraceProcessorFactory extends AspectProcessorFactory {

	@Override
	public AspectProcessor getProcessor(ProceedingJoinPoint pjp) {
		return new TraceProcessor(pjp);
	}

}
