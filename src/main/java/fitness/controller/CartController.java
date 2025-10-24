//package fitness.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import fitness.models.Cart;
//import fitness.serviceImpl.CartService;
//
//@RestController
//@RequestMapping("/cart")
//@CrossOrigin("*")
//public class CartController {
//@Autowired
//private CartService service;
//
//@GetMapping("/{userId}")
//public ResponseEntity<List<Cart>> getCart(@PathVariable Long userId){
//	return ResponseEntity.ok(service.getCartByUserId(userId));
//}
//@PostMapping("/add")
//public ResponseEntity<Cart> addItem(@RequestBody Cart cartItem){ 
//	return ResponseEntity.ok(service.addToCart(cartItem));
//}
//@DeleteMapping("/{userId}/clear")
//public ResponseEntity<String> clearCart(@PathVariable Long userId){
//	service.clearCart(userId);
//	return ResponseEntity.ok("Cart Cleared successfully!");
//}
//@GetMapping("/{userId}/total")
//public ResponseEntity<Double> getTotalCost(@PathVariable Long userId){
//	return ResponseEntity.ok(service.getTotalCost(userId));
//}
//}

package fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fitness.models.Cart;
import fitness.serviceImpl.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

	@Autowired
	private CartService service;

	@GetMapping("/items/{userId}")
	public ResponseEntity<List<Cart>> getCart(@PathVariable Long userId) {
		return ResponseEntity.ok(service.getCartByUserId(userId));
	}

	@PostMapping("/add")
	public ResponseEntity<Cart> addItem(@RequestBody Cart cartItem) {
		Cart savedCart = service.addToCart(cartItem);
		return ResponseEntity.ok(service.addToCart(cartItem));
	}

	@DeleteMapping("/remove/{itemId}")
	public ResponseEntity<String> removeItem(@PathVariable Long itemId) {
		service.removeItem(itemId);
		return ResponseEntity.ok("Item removed successfully!");
	}

	@DeleteMapping("/clear/{userId}")
	public ResponseEntity<String> clearCart(@PathVariable Long userId) {
		service.clearCart(userId);
		return ResponseEntity.ok("Cart cleared successfully!");
	}

	@GetMapping("/total/{userId}")
	public ResponseEntity<Double> getTotalCost(@PathVariable Long userId) {
		double totalCost=service.getTotalCost(userId);
		return ResponseEntity.ok(service.getTotalCost(userId));
	}
}
