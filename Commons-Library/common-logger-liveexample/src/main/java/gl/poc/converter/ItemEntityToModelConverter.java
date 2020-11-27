package gl.poc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import gl.common.annotation.Loggable;
import gl.poc.entity.ItemDTO;
import gl.poc.model.Item;


@Component
public class ItemEntityToModelConverter  implements Converter<ItemDTO, Item>{

	@Override
	@Loggable
	public Item convert(ItemDTO source) {
		return new Item(source.getId(),source.getName(),source.getSector());
	}

}
