package pl.cwiczenia.restApi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cwiczenia.restApi.model.Comment;
import pl.cwiczenia.restApi.model.Post;
import pl.cwiczenia.restApi.repository.CommentRepository;
import pl.cwiczenia.restApi.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private static final int PAGE_SIZE = 5;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository,  CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<Post> getPosts(int page, Sort.Direction sort){
        return postRepository.findAllPosts(PageRequest.of(page,PAGE_SIZE,
                Sort.by(sort, "id") ));
    }

    public List<Post>getPostsWithComments(int pageNumber, Sort.Direction sort){
        List<Post> postList = postRepository.findAllPosts(PageRequest.of(pageNumber, PAGE_SIZE
                ,Sort.by(sort, "id")));
        List<Long> idList = postList.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(idList);
        postList.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return postList;
    }

    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream().filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }

    public Optional<Post> getSinglePost(long id){
        return postRepository.findById(id);
    }

    public Post addPost(Post post) {
            return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post postEdited =  postRepository.findById(post.getId()).orElseThrow();
        postEdited.setContent(post.getContent());
        postEdited.setCreated(post.getCreated());
        postEdited.setTitle(post.getTitle());
        return postEdited;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
