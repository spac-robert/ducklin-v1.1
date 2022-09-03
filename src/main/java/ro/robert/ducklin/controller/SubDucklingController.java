package ro.robert.ducklin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ro.robert.ducklin.dto.SubDucklingData;
import ro.robert.ducklin.facade.SubDucklingFacade;

import java.util.List;

@RestController
@RequestMapping("/subduckling")
@Slf4j
public class SubDucklingController {

    private final SubDucklingFacade subDucklingFacade;

    @Autowired
    public SubDucklingController(SubDucklingFacade subDucklingFacade) {
        this.subDucklingFacade = subDucklingFacade;
    }

    @PostMapping
    public ResponseEntity<SubDucklingData> createSubDuckling(@RequestBody SubDucklingData subDucklingData) {
        ResponseEntity<SubDucklingData> response;
        try {
            SubDucklingData subDuckling = subDucklingFacade.saveSubDuckling(subDucklingData);
            response = new ResponseEntity<>(subDuckling, HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            MultiValueMap<String, String> map = new HttpHeaders();
            map.add("error", e.getMessage());
            response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<SubDucklingData>> getAllSubDucklings() {
        ResponseEntity<List<SubDucklingData>> response;
        try {
            List<SubDucklingData> list = subDucklingFacade.getAllSubDucklings();
            response = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            MultiValueMap<String, String> map = new HttpHeaders();
            map.add("error", e.getMessage());
            response = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
