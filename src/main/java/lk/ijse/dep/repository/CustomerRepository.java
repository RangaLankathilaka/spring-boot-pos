package lk.ijse.dep.repository;

import lk.ijse.dep.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,String> {

//    @Query("SELECT c FROM Customer c WHERE c.address LIKE ?1%")
//    List<Customer> mataOniAddressMeaAkurenPatanGanna(String address);
//    @Query("SELECT c FROM Customer c WHERE c.address LIKE :address%")
//    List<Customer> mataOniAddressMeaAkurenPatanGanna(@Param("address") String address);

//    @Query("SELECT c FROM Customer c WHERE c.address LIKE ?#{[1]}%")
//    List<Customer> mataOniAddressMeaAkurenPatanGanna(String name,String address);

//    @Query("SELECT c FROM Customer c WHERE c.address LIKE :#{#address}%")
//    List<Customer> mataOniAddressMeaAkurenPatanGanna(@Param("address") String address);

    List<Customer> mataOniAddressMeaAkurenPatanGanna( String address);


}
