package gl.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Item {
	
	private int id;
	private String name;
	
	@ToString.Exclude
	private String group;

	@ToString.Include
	private static String groupMasked = "*******";
	
	@ToString.Include
	private String groupPartialMask()
	{
		return group.replaceAll("^(.{1}).*(.{1})$", "$1******$2");
	}
	
}
