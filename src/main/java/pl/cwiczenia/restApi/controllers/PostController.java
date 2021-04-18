package pl.cwiczenia.restApi.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.cwiczenia.restApi.dto.PostDto;
import pl.cwiczenia.restApi.model.Post;
import pl.cwiczenia.restApi.service.PostService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page >= 0   ? page : 0;
        Sort.Direction sortDirection = sort !=null ? sort : Sort.Direction.ASC;
       // throw new IllegalArgumentException("Not implemented yet!");
        return PostDtoMapper.mapToPostDto(postService.getPosts(pageNumber, sortDirection));

    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page >= 0   ? page : 0;
        Sort.Direction sortDirection = sort !=null ? sort : Sort.Direction.ASC;
        // throw new IllegalArgumentException("Not implemented yet!");
        return postService.getPostsWithComments(pageNumber, sortDirection);

    }

    @GetMapping("/posts/{id}")
    public Post getSinglePosts(@PathVariable long id){
        Optional<Post> postOptional = postService.getSinglePost(id);
        if(postOptional.isPresent()){
            return postOptional.get();
        }else throw new IllegalArgumentException("There's no post with id " + id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post){
        return postService.editPost(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id){
        postService.deletePost(id);
    }
}
