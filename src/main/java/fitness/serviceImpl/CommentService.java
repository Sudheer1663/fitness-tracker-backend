package fitness.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitness.models.Comment;
import fitness.models.Community;
import fitness.repository.CommentRepository;
import fitness.repository.CommunityRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommunityRepository communityRepository;

    // 🔹 Get all comments for a community post
    public List<Comment> getCommentsByCommunity(Long communityId) {
        return commentRepository.findByCommunityId(communityId)
                .stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .collect(Collectors.toList());
    }

    // 🔹 Add a comment under a community
    public Optional<Comment> addComment(Long communityId, Comment comment) {
        return communityRepository.findById(communityId).map(post -> {
            comment.setCommunity(post);
            return commentRepository.save(comment);
        });
    }

    // 🔹 Delete comment
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    // 🔹 Like/unlike a comment
    public Optional<Comment> likeComment(Long id, String username) {
        return commentRepository.findById(id).map(comment -> {
            List<String> likes = comment.getLikes();
            if (likes.contains(username)) {
                likes.remove(username);
            } else {
                likes.add(username);
            }
            comment.setLikes(likes);
            return commentRepository.save(comment);
        });
    }
}
