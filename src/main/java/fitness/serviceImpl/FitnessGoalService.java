package fitness.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitness.helper.EnumCollections.Goal;
import fitness.models.FitnessGoal;
import fitness.repository.FitnessGoalRepository;

@Service
public class FitnessGoalService {

    @Autowired
    private FitnessGoalRepository repository;

    public List<FitnessGoal> getAllFitnessGoals() {
        return repository.findAll();
    }

    public List<FitnessGoal> getFitnessGoalsByGoal(Goal goal) {
        return repository.findByGoal(goal);
    }

    public FitnessGoal createFitnessGoal(FitnessGoal fitnessGoal) {
        return repository.save(fitnessGoal);
    }

    public FitnessGoal updateFitnessGoal(Long id, FitnessGoal fitnessGoal) {
        Optional<FitnessGoal> existingOpt = repository.findById(id);
        if(existingOpt.isPresent()) {
            FitnessGoal existing = existingOpt.get();
            existing.setTitle(fitnessGoal.getTitle());
            existing.setDuration(fitnessGoal.getDuration());
            existing.setCost(fitnessGoal.getCost());
            existing.setLevel(fitnessGoal.getLevel());
            existing.setType(fitnessGoal.getType());
            existing.setGoal(fitnessGoal.getGoal());
            if(fitnessGoal.getImageData() != null) {
                existing.setImageData(fitnessGoal.getImageData());
            }
            return repository.save(existing);
        }
        return null;
    }

    public void deleteFitnessGoal(Long id) {
        repository.deleteById(id);
    }
}
