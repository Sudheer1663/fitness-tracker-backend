package fitness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fitness.models.Profile;
import fitness.serviceImpl.ProfileService;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin("*")
public class ProfileController {
	@Autowired
	private ProfileService service;

	@GetMapping("/all")
	public List<Profile> getAllProfiles() {
		return service.getAllProfiles();
	}

	@GetMapping("/{id}")
	public Profile getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping("/add")
	public Profile createProfile(@RequestBody Profile profile) {
		return service.createProfile(profile);
	}

	@DeleteMapping("/delete{id}")
	public void deleteProfile(@PathVariable Long id) {
		service.deleteProfile(id);
	}

	@PutMapping("/update/{id}")
	public Profile updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
		return service.updateProfile(id, profile);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<Map<String, Object>> getCombinedProfile(@PathVariable Long userId) {
	    try {
	        Map<String, Object> profileData = service.getCombinedProfileByUserId(userId);
	        
	        if (profileData.containsKey("userId")) {
	            return ResponseEntity.ok(profileData);
	        } else {
	            // Return 404 with a proper JSON response
	            Map<String, Object> errorResponse = new HashMap<>();
	            errorResponse.put("error", "User not found");
	            errorResponse.put("userId", userId);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
	    } catch (Exception e) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("error", "Internal server error");
	        errorResponse.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}
	@PostMapping("/add/user/{userId}")
	public Profile createProfileForUser(@PathVariable Long userId, @RequestBody Profile profile) {
		return service.createProfileForUser(userId, profile);
	}
	@PutMapping("/upadate/user/{userId}")
	public Profile updateProfileByUserId(@PathVariable Long userId,@RequestBody Profile profile) {
		return service.updateProfileByUserId(userId, profile);
	}
	@GetMapping("/combined/{userId}")
	public Map<String, Object> getCombinebProfileData(@PathVariable Long userId){
		return service.getCombinedProfileByUserId(userId);
	}
    @PutMapping("/combined/{userId}")
    public Map<String, Object> updateCombinedProfile(@PathVariable Long userId, @RequestBody Map<String, Object> profileData) {
        return service.updateCombinedProfile(userId, profileData);
    }
}
