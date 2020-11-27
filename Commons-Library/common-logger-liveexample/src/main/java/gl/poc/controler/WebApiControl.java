package gl.poc.controler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gl.common.annotation.Loggable;
import gl.poc.entity.ItemDTO;
import gl.poc.model.Item;
import gl.poc.service.ItemService;

@RestController()
public class WebApiControl {

	@Autowired
	ItemService is;
	
	@Autowired
	ConversionService converter;
	
	
	@GetMapping("/item")
	@Loggable
	public List<Item> getAllItems()
	{
		return is.getAll().stream().map(x->converter.convert(x, Item.class)).collect(Collectors.toList());
	}
	
	
	@PostMapping("/item")
	@Loggable
	public void addItem(@RequestBody Item item) throws Exception
	{
		is.createItem(converter.convert(item, ItemDTO.class));
	}
	
	
	
}
