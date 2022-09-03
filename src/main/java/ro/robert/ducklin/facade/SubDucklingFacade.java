package ro.robert.ducklin.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.robert.ducklin.converter.SubDucklingConverter;
import ro.robert.ducklin.dto.SubDucklingData;
import ro.robert.ducklin.model.SubDucklingModel;
import ro.robert.ducklin.service.SubDucklingService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubDucklingFacade {

    private final SubDucklingConverter subDucklingConverter;
    private final SubDucklingService subDucklingService;

    @Autowired
    public SubDucklingFacade(SubDucklingConverter subDucklingConverter, SubDucklingService subDucklingService) {
        this.subDucklingConverter = subDucklingConverter;
        this.subDucklingService = subDucklingService;
    }

    public SubDucklingData saveSubDuckling(SubDucklingData subDucklingData) {
        SubDucklingModel subDucklingModel = subDucklingConverter.to(subDucklingData);
        subDucklingModel.setCreatedDate(Instant.now());
        subDucklingModel = subDucklingService.saveSubDuckling(subDucklingModel);

        return subDucklingConverter.from(subDucklingModel);
    }

    public List<SubDucklingData> getAllSubDucklings() {
        return subDucklingService.getAllSubDucklings().stream()
                .map(subDucklingConverter::from)
                .collect(Collectors.toList());
    }
}
