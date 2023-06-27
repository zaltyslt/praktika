package lt.ks.vtmc.serviceapi.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByOrderByCreatedAtAsc();

    List<Order> findByIdContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(String id, String description);
}
