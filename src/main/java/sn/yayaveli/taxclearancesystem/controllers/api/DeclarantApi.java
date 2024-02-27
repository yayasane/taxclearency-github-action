package sn.yayaveli.taxclearancesystem.controllers.api;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;
import sn.yayaveli.taxclearancesystem.utils.Constants;

import java.util.List;

@RequestMapping(Constants.APP_ROOT
                + "/declarants")
@Api(Constants.APP_ROOT + "/declarants")
public interface DeclarantApi {
        @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Enregistrer declarant declarant", notes = "Cette methode permet d'enregistrer un declarant", response = DeclarantDto.class)
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Le objet declarant crée"),
                        @ApiResponse(code = 400, message = "Le objet declarant n'est pas valide")
        })

        ResponseEntity<DeclarantDto> save(@RequestBody DeclarantDto declarantDto);

        @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Mettre à jour declarant", notes = "Cette methode permet de modifier un declarant", response = DeclarantDto.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Le objet declarant modifié"),
                @ApiResponse(code = 400, message = "Le objet declarant n'est pas valide")
        })

        ResponseEntity<DeclarantDto> update(@PathVariable Long id,@RequestBody DeclarantDto declarantDto);

        @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Rehercher un declarant", notes = "Cette methode permet de chercher un declarant par son ID", response = DeclarantDto.class)
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Le declarant a été trouvé dans la BD"),
                        @ApiResponse(code = 404, message = "Aucun declarant n'existe dans la bd avec l'ID fourni"),
        })
        ResponseEntity<DeclarantDto> findById(@PathVariable Long id);

        @GetMapping(value = "/{id}/declarations", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Récuperer la liste des déclarations d'un declarant", notes = "Cette methode permet de récupérer les déclarations d'un declarant par son ID", response = DeclarantDto.class)
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "La liste des declarations du declarant / Une liste vide"),
                @ApiResponse(code = 404, message = "Aucun declarant n'existe dans la bd avec l'ID fourni"),
        })
        ResponseEntity<List<DeclarationDto>> findDeclarantDeclarations(@PathVariable Long id);
        @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Renvoi la liste des declarants", notes = "Cette methode permet de chercher et renvoyer la liste des declarants qui existe dans la BD")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "La liste des declarants / Une liste vide")
        })
        ResponseEntity<List<DeclarantDto>> findAll();

        @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Supprimer un declarant", notes = "Cette methode permet de supprimer un declarant par son ID")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Le declarant a été supprimé")
        })
        ResponseEntity delete(@PathVariable Long id);

}
