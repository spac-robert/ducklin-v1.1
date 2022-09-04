package ro.robert.ducklin.service;

import lombok.NonNull;
import ro.robert.ducklin.model.SubDucklingModel;

import java.util.List;

/**
 * Contains business logic related to subduckling
 */
public interface SubDucklingService {

    /**
     * Save the {@link SubDucklingModel} into database
     *
     * @param subDucklingModel the entity to be saved
     * @return the entity
     */
    @NonNull
    SubDucklingModel saveSubDuckling(@NonNull SubDucklingModel subDucklingModel);

    /**
     * Get a list of {@link SubDucklingModel}
     * @return
     */
    @NonNull
    List<SubDucklingModel> getAllSubDucklings();
}
