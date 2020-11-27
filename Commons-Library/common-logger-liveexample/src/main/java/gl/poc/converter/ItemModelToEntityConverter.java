package gl.poc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import gl.common.annotation.Loggable;
import gl.poc.entity.ItemDTO;
import gl.poc.model.Item;

@Component
public class ItemModelToEntityConverter implements Converter<Item, ItemDTO>{

	@Override
	@Loggable
	public ItemDTO convert(Item source) {
		return new ItemDTO(source.getId(), source.getName(), source.getGroup());
	}

}
