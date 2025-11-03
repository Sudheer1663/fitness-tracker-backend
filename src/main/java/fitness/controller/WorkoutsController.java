package fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fitness.models.Workouts;
import fitness.serviceImpl.WorkoutsService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/workouts")
public class WorkoutsController {
	@Autowired
	private WorkoutsService service;

	@PostMapping("/add")
	public Workouts addWorkout(@RequestBody Workouts workouts) {
		return service.saveWorkouts(workouts);
	}
	@GetMapping("user/{userId}")
		public List<Workouts> getUserWorkouts(@PathVariable Long userId){
			return service.getWorkoutsByUser(userId);
		}
	@DeleteMapping("/{id}")
	public void deleteWork(@PathVariable Long id) {
		service.deleteWorkouts(id);
	}
	@DeleteMapping("/clear/{userId}")
	public void clearWorkout( @PathVariable Long userId) {
		service.clearWorkouts(userId);
	}
}

