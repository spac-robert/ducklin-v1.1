package ro.robert.ducklin.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.model.SubDucklingModel;
import ro.robert.ducklin.repository.SubDucklingRepository;
import ro.robert.ducklin.service.SubDucklingService;

import java.util.List;

@Service
@Slf4j
public class DefaultSubDucklingService implements SubDucklingService {

    private final SubDucklingRepository subDucklingRepository;

    @Autowired
    public DefaultSubDucklingService(SubDucklingRepository subDucklingRepository) {
        this.subDucklingRepository = subDucklingRepository;
    }

    @NonNull
    public SubDucklingModel saveSubDuckling(@NonNull SubDucklingModel subDucklingModel) {
        return subDucklingRepository.save(subDucklingModel);
    }

    @Override
    public @NonNull List<SubDucklingModel> getAllSubDucklings() {
        return subDucklingRepository.findAll();
    }
}
