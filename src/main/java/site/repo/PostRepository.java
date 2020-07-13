package site.repo;

import org.springframework.data.repository.CrudRepository;
import site.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
