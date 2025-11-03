package fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fitness.models.Community;

public interface CommunityRepository extends JpaRepository<Community, Long> {}
