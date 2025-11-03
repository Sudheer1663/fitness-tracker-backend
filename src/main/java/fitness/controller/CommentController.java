package fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fitness.models.Comment;
import fitness.serviceImpl.CommentService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 🔹 Get all comments for a post
    @GetMapping("/community/{communityId}")
    public List<Comment> getComments(@PathVariable Long communityId) {
        return commentService.getCommentsByCommunity(communityId);
    }

    // 🔹 Add a new comment
    @PostMapping("/community/{communityId}/add")
    public ResponseEntity<?> addComment(@PathVariable Long communityId, @RequestBody Comment comment) {
        return commentService.addComment(communityId, comment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Delete comment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment deleted successfully");
    }

    // 🔹 Like/unlike comment
    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeComment(@PathVariable Long id, @RequestParam String username) {
        return commentService.likeComment(id, username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
