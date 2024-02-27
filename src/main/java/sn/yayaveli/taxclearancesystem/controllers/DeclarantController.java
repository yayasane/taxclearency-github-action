package sn.yayaveli.taxclearancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sn.yayaveli.taxclearancesystem.controllers.api.DeclarantApi;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;
import sn.yayaveli.taxclearancesystem.services.DeclarantService;

import java.util.List;

@RestController
public class DeclarantController implements DeclarantApi {
    private DeclarantService declarantService;
    // Field Injection
    /*
     * @Autowired
     * private DeclarantService declarantService;
     */

    // Setter Injection
    /*
     * @Autowired
     * public void setDeclarantService(DeclarantService declarantService) {
     * this.declarantService = declarantService;
     * }
     */

    // Constructor Injection
    @Autowired
    public DeclarantController(DeclarantService declarantService) {
        this.declarantService = declarantService;
    }

    @Override
    public ResponseEntity<DeclarantDto> save(DeclarantDto declarantDto) {
        return ResponseEntity.ok().body(this.declarantService.save(declarantDto));
    }

    @Override
    public ResponseEntity<DeclarantDto> update(Long id,DeclarantDto declarantDto) {
        return ResponseEntity.ok().body(this.declarantService.save(declarantDto));
    }

    @Override
    public ResponseEntity<DeclarantDto> findById(Long id) {
        return ResponseEntity.ok().body(this.declarantService.findById(id));
    }

    @Override
    public ResponseEntity<List<DeclarationDto>> findDeclarantDeclarations(Long id) {
        return ResponseEntity.ok().body(this.declarantService.findDeclarantDeclarations(id));
    }


    @Override
    public ResponseEntity<List<DeclarantDto>> findAll() {
        return ResponseEntity.ok().body(this.declarantService.findAll());
    }

    @Override
    public ResponseEntity delete(Long id) {
        this.declarantService.deleteLong(id);
        return ResponseEntity.noContent().build();
    }

}
