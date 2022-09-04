package ro.robert.ducklin.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.robert.ducklin.converter.PostConverter;
import ro.robert.ducklin.dto.PostData;
import ro.robert.ducklin.model.PostModel;
import ro.robert.ducklin.service.PostService;

@Component
public class PostFacade {

    private PostConverter postConverter;
    private PostService postService;

    @Autowired
    public PostFacade(PostConverter postConverter, PostService postService) {
        this.postConverter = postConverter;
        this.postService = postService;
    }

    public PostData createPost(PostData postData) {
        PostModel post = postService.savePost(postConverter.to(postData));
        return postConverter.from(post);
    }
}
