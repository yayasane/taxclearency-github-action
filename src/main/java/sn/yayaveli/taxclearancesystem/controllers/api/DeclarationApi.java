package sn.yayaveli.taxclearancesystem.controllers.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;
import sn.yayaveli.taxclearancesystem.utils.Constants;

import java.util.List;

@RequestMapping(Constants.APP_ROOT
        + "/declarations")
@Api(Constants.APP_ROOT + "/declarations")
public interface DeclarationApi {

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une déclaration", notes = "Cette methode permet d'enregistrer une déclaration", response = DeclarationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet déclaration crée"),
            @ApiResponse(code = 400, message = "L'objet déclaration n'est pas valide")
    })
    ResponseEntity<DeclarationDto> save(@RequestBody DeclarationDto declarationDto);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Mettre à jour une déclaration", notes = "Cette methode permet de modifier une déclaration", response = DeclarationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet déclaration modifié"),
            @ApiResponse(code = 400, message = "L'objet déclaration n'est pas valide")
    })
    ResponseEntity<DeclarationDto> update(@PathVariable Long id,@RequestBody DeclarationDto declarationDto);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rehercher un déclaration", notes = "Cette methode permet de chercher une déclaration par son ID", response = DeclarationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La déclaration a été trouvé dans la BD"),
            @ApiResponse(code = 404, message = "Aucune déclaration n'existe dans la bd avec l'ID fourni"),
    })
    ResponseEntity<DeclarationDto> findById(@PathVariable Long id);

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des déclarations", notes = "Cette methode permet de chercher et renvoyer la liste des déclarations qui existe dans la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des déclarations / Une liste vide")
    })
    ResponseEntity<List<DeclarationDto>> findAll();

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer une déclaration", notes = "Cette methode permet de supprimer une déclaration par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La déclaration a été supprimé")
    })
    ResponseEntity delete(@PathVariable Long id);
}
