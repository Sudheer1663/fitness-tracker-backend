package fitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fitness.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByCommunityId(Long communityId);

}
