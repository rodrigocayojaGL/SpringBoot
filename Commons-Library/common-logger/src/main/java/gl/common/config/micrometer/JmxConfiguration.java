package gl.common.config.micrometer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.jmx.JmxConfig;
import io.micrometer.jmx.JmxMeterRegistry;

@Configuration
@ConditionalOnExpression("${gl.logger.registry.jmx:true}")
public class JmxConfiguration {

	public JmxConfiguration() {
		Metrics.addRegistry(new JmxMeterRegistry(new JmxConfig() {
			@Override
			public String get(String s) {
				return null;
			}
		}, Clock.SYSTEM));
	}

}
