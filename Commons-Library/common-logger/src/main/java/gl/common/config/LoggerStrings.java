package gl.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class LoggerStrings {
	
	@Value("${gl.logger.strings.initialization:Custom Aspects started with {} processors factories}")
	private String initialization;

	@Value("${gl.logger.strings.finalization:Custom Aspects processors stopped}")
	private String finalization;
	
	@Value("${gl.logger.strings.parameter.method:Parameters for {}}")
	private String parameterMethod;

	@Value("${gl.logger.strings.parameter.parameter-value:-> {}}")
	private String parameterValue;

	@Value("${gl.logger.strings.parameter.return-value:Returning Value {}: {}}")
	private String parameterReturn;

	@Value("${gl.logger.strings.parameter.exception:Excption at {}: {}}")
	private String parameterException;

	@Value("${gl.logger.strings.null-represetnation:<null>}")
	private String nullRepesent;
	

}
