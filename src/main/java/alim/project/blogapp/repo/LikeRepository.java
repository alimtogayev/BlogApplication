package alim.project.blogapp.repo;

import alim.project.blogapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserId(Long userId);
    List<Like> findByPostId(Long postId);
}
