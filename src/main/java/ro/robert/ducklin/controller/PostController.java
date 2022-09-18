package ro.robert.ducklin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.robert.ducklin.dto.PostData;
import ro.robert.ducklin.facade.impl.PostFacade;

@RestController
@RequestMapping("/duckling/posts")
@Slf4j
public class PostController {
    private final PostFacade postFacade;

    @Autowired
    public PostController(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @PostMapping
    public ResponseEntity<PostData> createPost(@RequestBody PostData postData) {
        ResponseEntity<PostData> response;
        try {
            PostData post = postFacade.createPost(postData);
            response = new ResponseEntity<>(post, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            MultiValueMap<String, String> map = new HttpHeaders();
            map.add("error", e.getMessage());
            response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
