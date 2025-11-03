package fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fitness.models.Workouts;

public interface WorkoutsRepository extends JpaRepository<Workouts, Long>{

	List<Workouts> findByUserId(Long userId);

}
