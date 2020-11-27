package gl.poc.config;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Example configuration for an arbitraty bean.
 * @author Nicolas.Cernic
 */
@Configuration
public class ApplicationConfig {

	@Bean 
	public Random getRandomGenerator()
	{
		return new Random();
	}

}
