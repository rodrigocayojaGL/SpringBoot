package gl.poc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemDTO {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@ToString.Exclude
	private String sector;
	
	@ToString.Include
	private static String sectorMasked = "######";
}
