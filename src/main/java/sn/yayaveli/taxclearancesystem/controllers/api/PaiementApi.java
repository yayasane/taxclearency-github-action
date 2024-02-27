package sn.yayaveli.taxclearancesystem.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.yayaveli.taxclearancesystem.dto.PaiementDto;
import sn.yayaveli.taxclearancesystem.utils.Constants;

import java.util.List;

@RequestMapping(Constants.APP_ROOT
        + "/payments")
@Api(Constants.APP_ROOT + "/payments")
public interface PaiementApi {

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Effectuer un paiement", notes = "Cette methode permet d'effectuer un paiement", response = PaiementDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet paiement crée"),
            @ApiResponse(code = 400, message = "L'objet paiement n'est pas valide")
    })
    ResponseEntity<PaiementDto> save(@RequestBody PaiementDto itemDto);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Mettre à jour un paiement", notes = "Cette methode permet de mettre à jour un paiement", response = PaiementDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet paiement mis à jour"),
            @ApiResponse(code = 400, message = "L'objet paiement n'est pas valide")
    })
    ResponseEntity<PaiementDto> update(@PathVariable Long id,@RequestBody PaiementDto itemDto);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rehercher un paiement", notes = "Cette methode permet de chercher un paiement par son ID", response = PaiementDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le paiement a été trouvé dans la BD"),
            @ApiResponse(code = 404, message = "Aucun paiement n'existe dans la bd avec l'ID fourni"),
    })
    ResponseEntity<PaiementDto> findById(@PathVariable Long id);

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des paiements", notes = "Cette methode permet de chercher et renvoyer la liste des paiements qui existe dans la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des paiements / Une liste vide")
    })
    ResponseEntity<List<PaiementDto>> findAll();

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer un paiement", notes = "Cette methode permet de supprimer un paiement par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'paiement a été supprimé")
    })
    ResponseEntity delete(@PathVariable Long id);
}
