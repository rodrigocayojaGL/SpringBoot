package gl.common.aspect;

import static java.util.Optional.ofNullable;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import gl.common.config.LoggerStrings;

@Component
@Aspect
@Order(110)
@ConditionalOnExpression("${gl.logger.parameter.enabled:true}")
public class LoggableAspect {

	@Autowired
	private LoggerStrings textStrings;
	
	@Pointcut("@annotation(gl.common.annotation.Loggable)")
	public void loggedMethod() {
	};

	@Around("loggedMethod()")
	public Object aroundLogged(ProceedingJoinPoint pjp) throws Throwable {

		
		Logger log = getLogger(pjp.getTarget().getClass());

		Signature sig = pjp.getSignature();

		log.info(textStrings.getParameterMethod(), sig.toShortString());
		Stream.of(pjp.getArgs()).forEach(x -> {
			log.info(textStrings.getParameterValue(), ofNullable(x).orElse(textStrings.getNullRepesent()).toString());
		});

		Object ret;
		try {
			ret = pjp.proceed();
		} catch (Throwable t) {
			log.warn(textStrings.getParameterException(), sig.toShortString(), t.getClass().getSimpleName());
			throw t;
		}

		log.info(textStrings.getParameterReturn(), sig.toShortString(), ofNullable(ret).orElse(textStrings.getNullRepesent()).toString());
		return ret;
	}

}
