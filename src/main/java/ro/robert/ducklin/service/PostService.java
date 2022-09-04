package ro.robert.ducklin.service;

import ro.robert.ducklin.model.PostModel;

/**
 * Contains business logic related to posts
 */
public interface PostService {

    /**
     * Save post
     * @param postModel
     * @return the saved post
     */
    PostModel savePost(PostModel postModel);
}
