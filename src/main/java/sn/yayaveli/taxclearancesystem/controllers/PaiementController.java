package sn.yayaveli.taxclearancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sn.yayaveli.taxclearancesystem.controllers.api.PaiementApi;
import sn.yayaveli.taxclearancesystem.dto.PaiementDto;
import sn.yayaveli.taxclearancesystem.services.PaiementService;

import java.util.List;

@RestController
public class PaiementController implements PaiementApi {

    private PaiementService paiementService;
    // Field Injection
    /*
     * @Autowired
     * private PaiementService paiementService;
     */

    // Setter Injection
    /*
     * @Autowired
     * public void setPaiementService(PaiementService paiementService) {
     * this.paiementService = paiementService;
     * }
     */

    // Constructor Injection
    @Autowired
    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    public ResponseEntity<PaiementDto> save(PaiementDto paiementDto) {
        return ResponseEntity.ok().body(this.paiementService.save(paiementDto));
    }

    public ResponseEntity<PaiementDto> update(Long id,PaiementDto paiementDto) {
        return ResponseEntity.ok().body(this.paiementService.save(paiementDto));
    }

    @Override
    public ResponseEntity<PaiementDto> findById(Long id) {
        return ResponseEntity.ok().body(this.paiementService.findById(id));
    }
    

    @Override
    public ResponseEntity<List<PaiementDto>> findAll() {
        return ResponseEntity.ok().body(this.paiementService.findAll());
    }

    @Override
    public ResponseEntity delete(Long id) {
        this.paiementService.deleteLong(id);
        return ResponseEntity.noContent().build();

    }

}
