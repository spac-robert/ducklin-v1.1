package ro.robert.ducklin.converter;

import org.springframework.stereotype.Component;
import ro.robert.ducklin.dto.PostData;
import ro.robert.ducklin.model.PostModel;

/**
 * Converter responsible for converting {@link PostData} to {@link PostModel}
 * and
 * {@link PostModel} to {@link PostData}
 */

@Component
public class PostConverter {

    /**
     * Convert {@link PostModel} to {@link PostData}
     *
     * @param source a post model
     * @return converted post model to post data
     */
    public PostData from(PostModel source) {
        PostData target = new PostData();
        target.setDescription(source.getDescription());
        target.setId(source.getPostId());
        target.setPostName(source.getPostName());
        target.setUrl(source.getPostName());
        target.setVoteCount(source.getVoteCount());

        return target;
    }

    /**
     * Convert {@link PostData} to {@link PostModel}
     *
     * @param source a post data
     * @return converted post data to post model
     */
    public PostModel to(PostData source) {
        PostModel target = new PostModel();
        target.setDescription(source.getDescription());
        target.setPostId(source.getId());
        target.setPostName(source.getPostName());
        target.setUrl(source.getPostName());
        target.setVoteCount(source.getVoteCount());

        return target;
    }
}
