package ro.robert.ducklin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.model.PostModel;
import ro.robert.ducklin.repository.PostRepository;
import ro.robert.ducklin.service.PostService;
import ro.robert.ducklin.service.AuthenticationService;

@Service
@Slf4j
public class DefaultPostService implements PostService {

    private final PostRepository postRepository;
    private final AuthenticationService userService;

    @Autowired
    public DefaultPostService(PostRepository postRepository, AuthenticationService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public PostModel savePost(PostModel postModel) {
        return postRepository.save(postModel);
    }
}
