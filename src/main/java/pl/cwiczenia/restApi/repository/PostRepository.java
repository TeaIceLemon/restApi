package pl.cwiczenia.restApi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.cwiczenia.restApi.model.Post;

;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//  @Query("Select p from Post p left join fetch p.comment" )
  @Query("Select p from Post p" )
  List<Post> findAllPosts(Pageable page);
}
