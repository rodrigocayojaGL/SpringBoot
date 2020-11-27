package gl.poc.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import gl.common.config.LoggerAspectConfig;


/**
 * Example class to utilize the Logger
 * @author Nicolas.Cernic
 */
@Component
@Aspect
public class LoggerConfigsImpl extends LoggerAspectConfig {

	@Pointcut("within(gl.poc..*) || within(java..*)") // Pointcut composition example 
	public void loggerPointcut() {
	}
}