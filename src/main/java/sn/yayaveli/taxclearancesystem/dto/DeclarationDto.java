package sn.yayaveli.taxclearancesystem.dto;


import lombok.Builder;
import lombok.Data;
import sn.yayaveli.taxclearancesystem.models.Declarant;
import sn.yayaveli.taxclearancesystem.models.Declaration;
import sn.yayaveli.taxclearancesystem.models.Paiement;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class DeclarationDto {
    private Long id;
    private Date dateDeclaration;
    private Double montantDeclaration;
    private DeclarantDto declarant;
    private List<PaiementDto> paiements;

    public static DeclarationDto fromEntity(Declaration declaration) {
        if (declaration == null) {
            return null;
            // TODO throw an exception

        }
        return DeclarationDto.builder()
                .id(declaration.getId())
                .dateDeclaration(declaration.getDateDeclaration())
                .declarant(DeclarantDto.fromEntity(declaration.getDeclarant()))
                .montantDeclaration(declaration.getMontantDeclaration())
                .build();
    };

    public static Declaration toEntity(DeclarationDto declarationDto) {
        if (declarationDto == null) {
            return null;
            // TODO throw an exception

        }
        Declaration declaration = new Declaration();
        declaration.setId(declarationDto.getId());
        declaration.setDeclarant(DeclarantDto.toEntity(declarationDto.getDeclarant()));
        declaration.setDateDeclaration(declarationDto.getDateDeclaration());
        declaration.setMontantDeclaration(declarationDto.getMontantDeclaration());


        return declaration;
    };
}
