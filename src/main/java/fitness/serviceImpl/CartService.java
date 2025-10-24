package fitness.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fitness.models.Cart;
import fitness.models.FitnessGoal;
import fitness.models.User;
import fitness.repository.CartRepository;
import fitness.repository.FitnessGoalRepository;
import fitness.repository.UserRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private FitnessGoalRepository fitnessGoalRepository;

	public List<Cart> getCartByUserId(Long userId) {
		return cartRepository.findByUserId(userId);
	}

	public Cart addToCart(Cart CartItem) {
//		User user = userRepository.findById(CartItem.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
//		CartItem.setUserId(user.getId());
		
		FitnessGoal goal = fitnessGoalRepository.findById((long) CartItem.getFitnessGoalId())
                .orElseThrow(() -> new RuntimeException("FitnessGoal not found with ID: " + CartItem.getFitnessGoalId()));
		
		  CartItem.setTitle(goal.getTitle());
	        CartItem.setType(goal.getType().toString());
	        CartItem.setLevel(goal.getLevel().toString());
	        CartItem.setDuration(goal.getDuration());
	        CartItem.setCost(goal.getCost());
	        CartItem.setImageData(goal.getImageData());
		return cartRepository.save(CartItem);
	}
@Transactional
	public void clearCart(Long userId) {
		cartRepository.deleteByUserId(userId);
	}

	public double getTotalCost(Long userId) {
		return cartRepository.findByUserId(userId).stream().mapToDouble(Cart::getCost).sum();
	}

	public void removeItem(Long itemId) {
		cartRepository.deleteById(itemId);

	}
}
