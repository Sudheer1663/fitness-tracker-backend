package fitness.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fitness.helper.EnumCollections;
import fitness.helper.EnumCollections.Goal;
import fitness.models.FitnessGoal;
import fitness.serviceImpl.FitnessGoalService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/fitnessgoal")
public class FitnessGoalController {

    @Autowired
    private FitnessGoalService service;

    @GetMapping("/all")
    public List<FitnessGoal> getAllFitnessGoal() {
        return service.getAllFitnessGoals();
    }

    @GetMapping("/goal/{goal}")
    public List<FitnessGoal> getFitnessGoalsByGoal(@PathVariable String goal) {
        Goal enumGoal = Goal.valueOf(goal.toUpperCase());
        return service.getFitnessGoalsByGoal(enumGoal);
    }

    @PostMapping("/add")
    public FitnessGoal createFitnessGoal(@RequestBody FitnessGoal fitnessGoal) throws Exception {
      
        if(fitnessGoal.getImageData() != null) {
      
        }
        return service.createFitnessGoal(fitnessGoal);
    }

    @PutMapping("/update/{id}")
    public FitnessGoal updateFitnessGoal(@PathVariable Long id, @RequestBody FitnessGoal fitnessGoal) throws Exception {
        return service.updateFitnessGoal(id, fitnessGoal);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFitnessGoal(@PathVariable Long id) {
        service.deleteFitnessGoal(id);
    }
}
