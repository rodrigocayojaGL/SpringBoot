package gl.poc.service;

import java.util.List;
import java.util.Random;

import javax.activity.InvalidActivityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gl.poc.entity.ItemDTO;
import gl.poc.repository.ItemRepo;

@Service
public class ItemService {

	@Autowired
	ItemRepo ir;

	@Autowired
	Random rnd;

	public List<ItemDTO> getAll() {
		return ir.findAll();
	}

	public void createItem(ItemDTO item) throws InvalidActivityException {
		if (rnd.nextInt(10) == 0)
			throw new InvalidActivityException("Random Error Occurred");
		ir.save(item);
	}

}
