package fitness.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitness.models.Profile;
import fitness.models.User;
import fitness.repository.ProfileRepository;
import fitness.repository.UserRepository;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile getById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    public Profile updateProfile(Long id, Profile profile) {
        Profile existing = profileRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(profile.getName());
            existing.setAge(profile.getAge());
            existing.setGender(profile.getGender());
            existing.setHeight(profile.getHeight());
            existing.setWeight(profile.getWeight());
            existing.setGoal(profile.getGoal());
            return profileRepository.save(existing);
        }
        return null;
    }


    public Profile getByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile createProfileForUser(Long userId, Profile profile) {
        profile.setUserId(userId);
        return profileRepository.save(profile);
    }

    public Profile updateProfileByUserId(Long userId, Profile profile) {
        Profile existing = profileRepository.findByUserId(userId);
        if (existing != null) {
            existing.setName(profile.getName());
            existing.setAge(profile.getAge());
            existing.setGender(profile.getGender());
            existing.setHeight(profile.getHeight());
            existing.setWeight(profile.getWeight());
            existing.setGoal(profile.getGoal());
            return profileRepository.save(existing);
        }
        return null;
    }

    public Map<String, Object> getCombinedProfileByUserId(Long userId) {
        Map<String, Object> combinedData = new HashMap<>();
        
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Add user data with null checks
            combinedData.put("userId", user.getId());
            combinedData.put("name", user.getName() != null ? user.getName() : "");
            combinedData.put("email", user.getEmail() != null ? user.getEmail() : "");
            combinedData.put("phone", user.getPhone() != null ? user.getPhone() : "");
            
            // Add profile data
            Profile profile = profileRepository.findByUserId(userId);
            if (profile != null) {
                combinedData.put("profileId", profile.getId());
                combinedData.put("age", profile.getAge());
                combinedData.put("gender", profile.getGender());
                combinedData.put("height", profile.getHeight());
                combinedData.put("weight", profile.getWeight());
                combinedData.put("goal", profile.getGoal());
            } else {
                // Set default null values when no profile exists
                combinedData.put("profileId", null);
                combinedData.put("age", null);
                combinedData.put("gender", null);
                combinedData.put("height", null);
                combinedData.put("weight", null);
                combinedData.put("goal", null);
            }
        } else {
            // User not found - return empty but valid response
            combinedData.put("error", "User not found");
        }
        
        return combinedData; // This was missing - make sure it's always returned
    }

    public Map<String, Object> updateCombinedProfile(Long userId, Map<String, Object> profileData) {
        Map<String, Object> result = new HashMap<>();
        
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            if (profileData.containsKey("name")) {
                user.setName((String) profileData.get("name"));
            }
            userRepository.save(user);
            
            Profile profile = profileRepository.findByUserId(userId);
            if (profile == null) {
                profile = new Profile();
                profile.setUserId(userId);
            }
            
            if (profileData.containsKey("name")) {
                profile.setName((String) profileData.get("name"));
            }
            if (profileData.containsKey("age")) {
                profile.setAge((Integer) profileData.get("age"));
            }
            if (profileData.containsKey("gender")) {
                profile.setGender((String) profileData.get("gender"));
            }
            if (profileData.containsKey("height")) {
                profile.setHeight((Double) profileData.get("height"));
            }
            if (profileData.containsKey("weight")) {
                profile.setWeight((Double) profileData.get("weight"));
            }
            if (profileData.containsKey("goal")) {
                profile.setGoal((String) profileData.get("goal"));
            }
            
            Profile savedProfile = profileRepository.save(profile);
            
            result.put("userId", user.getId());
            result.put("name", user.getName());
            result.put("email", user.getEmail());
            result.put("phone", user.getPhone());
            result.put("profileId", savedProfile.getId());
            result.put("profileName", savedProfile.getName());
            result.put("age", savedProfile.getAge());
            result.put("gender", savedProfile.getGender());
            result.put("height", savedProfile.getHeight());
            result.put("weight", savedProfile.getWeight());
            result.put("goal", savedProfile.getGoal());
        }
        
        return result;
    }
}