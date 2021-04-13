package pl.cwiczenia.restApi.controllers;

import pl.cwiczenia.restApi.dto.PostDto;
import pl.cwiczenia.restApi.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {
    private PostDtoMapper(){

    }
    public static List<PostDto> mapToPostDto(List<Post> posts) {
        return posts.stream()
                .map(post -> mapPostToPostDto(post))
                .collect(Collectors.toList());
    }

    private static PostDto mapPostToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }

}
