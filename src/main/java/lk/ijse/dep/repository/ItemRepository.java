package lk.ijse.dep.repository;

import lk.ijse.dep.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
