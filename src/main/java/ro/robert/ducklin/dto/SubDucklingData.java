package ro.robert.ducklin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubDucklingData {
    private Long id;
    private String name;
    private String description;
    private Long numberOfPosts;
    private Instant createdDate;
}
