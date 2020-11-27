package gl.common.config;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;

import gl.common.aspect.common.AspectProcessor;
import gl.common.aspect.common.AspectProcessorFactory;


/**
 * 
 * This Abstract class is designed as the configurator for the Logger aspects.
 * As such, it requires to have these annotations:<br/>
 * {@link org.springframework.stereotype.Component Component}<br/>
 * {@link org.aspectj.lang.annotation.Aspect Aspect}<br/>
 * 
 * @author Nicolas.Cernic
 */
@ComponentScan(basePackages = "gl.common")
@Order(20)
public abstract class LoggerAspectConfig {

	/**
	 * This method is used with the annotation
	 * {@link org.aspectj.lang.annotation.Pointcut Pointcut} to identify the logger
	 * interception location.<br/>
	 * Examples:<br/>
	 * <b>within(packageRegexp)</b>: <code>within(gl.poc..*)</code><br/>
	 * <b>execution(method Signature pattern)</b>:
	 * <code>execution (public gl.poc.ProcessRunner.setValue(..))</code><br/>
	 */
	@Pointcut
	public abstract void loggerPointcut();
	
	
	@Autowired(required = false)
	private List<AspectProcessorFactory> prsLst = new ArrayList<>();

	@Autowired
	private LoggerStrings textStrings;

	
	@Around("loggerPointcut()")
	public final Object aroundTimed(ProceedingJoinPoint pjp) throws Throwable {

	    List<AspectProcessor> state = prsLst.stream().map(x->x.getProcessor(pjp)).collect(Collectors.toList());

		Object ret;

		state.forEach(AspectProcessor::start);
		Collections.reverse(state);
		
		try {
			ret = pjp.proceed();
		} catch( Throwable t)
		{
			state.forEach(x->x.exception(t));
			state.clear();
			throw t;
		}
		state.forEach(x->x.stop(ret));		
		state.clear();
		return ret;
	}
	

	@PostConstruct
	public void construct() {
		Logger log = getLogger(LoggerAspectConfig.class);
		log.info(textStrings.getInitialization(), prsLst.size());
		prsLst.forEach(x -> log.debug(" - {}", x.getClass().getSimpleName()));
	}
	

	@PreDestroy
	public void destroy() {
		Logger log = getLogger(LoggerAspectConfig.class);
		log.info(textStrings.getFinalization());
	}

}
