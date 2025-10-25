package fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fitness.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	 @Query("SELECT p FROM Profile p WHERE p.userId = :userId")
	    Profile findByUserId(@Param("userId") Long userId);
}
