package sn.yayaveli.taxclearancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sn.yayaveli.taxclearancesystem.controllers.api.DeclarationApi;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;
import sn.yayaveli.taxclearancesystem.services.DeclarationService;

import java.util.List;

@RestController
public class DeclarationController implements DeclarationApi {

    private DeclarationService declarationService;
    // Field Injection
    /*
     * @Autowired
     * private DeclarationService declarationService;
     */

    // Setter Injection
    /*
     * @Autowired
     * public void setDeclarationService(DeclarationService declarationService) {
     * this.declarationService = declarationService;
     * }
     */

    // Constructor Injection
    @Autowired
    public DeclarationController(DeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    @Override
    public ResponseEntity<DeclarationDto> save(DeclarationDto declarationDto) {
        return ResponseEntity.ok().body(this.declarationService.save(declarationDto));
    }

    @Override
    public ResponseEntity<DeclarationDto> update(Long id,DeclarationDto declarationDto) {
        return ResponseEntity.ok().body(this.declarationService.save(declarationDto));
    }

    @Override
    public ResponseEntity<DeclarationDto> findById(Long id) {
        return ResponseEntity.ok().body(this.declarationService.findById(id));
    }


    @Override
    public ResponseEntity<List<DeclarationDto>> findAll() {
        return ResponseEntity.ok().body(this.declarationService.findAll());
    }

    @Override
    public ResponseEntity delete(Long id) {
        this.declarationService.deleteLong(id);
        return ResponseEntity.noContent().build();

    }

}
