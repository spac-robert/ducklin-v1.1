package ro.robert.ducklin.converter;

import org.springframework.stereotype.Component;
import ro.robert.ducklin.dto.SubDucklingData;
import ro.robert.ducklin.model.SubDucklingModel;

/**
 * Converter responsible for converting {@link SubDucklingData} to {@link SubDucklingModel}
 * and
 * {@link SubDucklingModel} to {@link SubDucklingData}
 */

@Component
public class SubDucklingConverter {

    /**
     * Convert {@link SubDucklingModel} to {@link SubDucklingData}
     *
     * @param source a subDuckling model
     * @return converted user model to subDuckling data
     */
    public SubDucklingData from(SubDucklingModel source) {
        SubDucklingData target = new SubDucklingData();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setName(source.getName());
        target.setCreatedDate(source.getCreatedDate());

        return target;
    }

    /**
     * Convert {@link SubDucklingData} to {@link SubDucklingModel}
     *
     * @param source a subDuckling data
     * @return converted user data to subDuckling model
     */
    public SubDucklingModel to(SubDucklingData source) {
        SubDucklingModel target = new SubDucklingModel();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setName(source.getName());
        target.setCreatedDate(source.getCreatedDate());

        return target;
    }
}
