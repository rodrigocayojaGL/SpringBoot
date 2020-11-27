package gl.poc.model;



import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Error {

	String code;
	String message;
	LocalDateTime time;
}
