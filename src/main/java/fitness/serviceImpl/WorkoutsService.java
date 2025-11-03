package fitness.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitness.models.Workouts;
import fitness.repository.WorkoutsRepository;

@Service
public class WorkoutsService {
@Autowired
private WorkoutsRepository workoutsRepository;

public Workouts saveWorkouts(Workouts workouts) {
	return workoutsRepository.save(workouts);
}
public List<Workouts> getWorkoutsByUser(Long userId){
	return workoutsRepository.findByUserId(userId);
}
public void deleteWorkouts(Long id) {
	workoutsRepository.deleteById(id);
}
public void clearWorkouts(Long userId) {
	List<Workouts>list=workoutsRepository.findByUserId(userId);
	workoutsRepository.deleteAll(list);
}
}
