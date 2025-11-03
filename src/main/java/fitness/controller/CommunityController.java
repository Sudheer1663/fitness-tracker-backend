package fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fitness.models.Community;
import fitness.serviceImpl.CommunityService;

@RestController
@RequestMapping("/api/community")
@CrossOrigin("*")
public class CommunityController {

    @Autowired
    private CommunityService service;

    @GetMapping("/posts")
    public List<Community> getAllPosts() {
        return service.getAllPosts();
    }

    @PostMapping("/add")
    public Community createCommunity(@RequestBody Community community) {
        return service.addPost(community);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommunity(@PathVariable Long id) {
        service.deleteCommunity(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likePost(@PathVariable Long id, @RequestParam String username) {
        return service.likePost(id, username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Community> search(@RequestParam String keyword) {
        return service.searchPosts(keyword);
    }

    @GetMapping("/top-liked")
    public List<Community> topLiked() {
        return service.getTopLikedPosts();
    }
}
