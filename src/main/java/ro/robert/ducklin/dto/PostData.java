package ro.robert.ducklin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.robert.ducklin.model.UserModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostData {

    private Long id;
    private String postName;
    private String url;
    private String description;
    private Integer voteCount;
}
