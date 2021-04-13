package pl.cwiczenia.restApi.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public List<PostDto> getPosts(@RequestParam(required = false) int page, Sort.Direction sort){
        int pageNumber = page >= 0   ? page : 0;
       // throw new IllegalArgumentException("Not implemented yet!");
        return PostDtoMapper.mapToPostDto(postService.getPosts(pageNumber, sort));

    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) int page, Sort.Direction sort){
        int pageNumber = page >= 0   ? page : 0;
        // throw new IllegalArgumentException("Not implemented yet!");
        return postService.getPostsWithComments(pageNumber, sort);

    }



    @GetMapping("/posts/{id}")
    public Post getSinglePosts(@PathVariable long id){
        Optional<Post> postOptional = postService.getSinglePost(id);
        if(postOptional.isPresent()){
            return postOptional.get();
        }else throw new IllegalArgumentException("There's no post with id " + id);


    }
}
