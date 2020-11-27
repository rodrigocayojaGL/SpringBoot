package gl.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gl.poc.entity.ItemDTO;

@Repository
public interface ItemRepo extends JpaRepository<ItemDTO, Integer>{

}
