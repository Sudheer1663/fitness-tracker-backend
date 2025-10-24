package fitness.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import fitness.helper.EnumCollections.Goal;
import fitness.models.FitnessGoal;

public interface FitnessGoalRepository extends JpaRepository<FitnessGoal, Long> {
    List<FitnessGoal> findByGoal(Goal goal);
}
