package fitness.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitness.models.Community;
import fitness.repository.CommunityRepository;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    public List<Community> getAllPosts() {
        return communityRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(Community::getDate).reversed())
            .collect(Collectors.toList());
    }

    public Community addPost(Community community) {
        return communityRepository.save(community);
    }

    public void deleteCommunity(Long id) {
        communityRepository.deleteById(id);
    }

    public Optional<Community> likePost(Long id, String username) {
        return communityRepository.findById(id).map(post -> {
            List<String> likes = post.getLikes();
            if (likes.contains(username)) {
                likes.remove(username);
            } else {
                likes.add(username);
            }
            post.setLikes(likes);
            return communityRepository.save(post);
        });
    }

    public List<Community> searchPosts(String keyword) {
        return communityRepository.findAll()
            .stream()
            .filter(p -> p.getText().toLowerCase().contains(keyword.toLowerCase())
                    || p.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                    || p.getCategory().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Community> getTopLikedPosts() {
        return communityRepository.findAll()
            .stream()
            .sorted((a, b) -> Integer.compare(b.getLikes().size(), a.getLikes().size()))
            .limit(5)
            .collect(Collectors.toList());
    }
}
