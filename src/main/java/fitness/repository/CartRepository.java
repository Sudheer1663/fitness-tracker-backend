package fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fitness.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	List<Cart> findByUserId(Long userId);

	void deleteByUserId(Long userId);

	List<Cart> findById(int userId);

	

}
