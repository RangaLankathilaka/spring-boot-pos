package lk.ijse.dep.repository;

import lk.ijse.dep.entity.OrderDetail;
import lk.ijse.dep.entity.OrderDetail_PK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetail_PK> {
}
