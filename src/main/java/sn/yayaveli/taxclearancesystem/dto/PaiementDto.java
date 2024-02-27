package sn.yayaveli.taxclearancesystem.dto;

import lombok.Builder;
import lombok.Data;
import sn.yayaveli.taxclearancesystem.models.Paiement;

import java.util.Date;

@Data
@Builder
public class PaiementDto {
    private Long id;
    private Date datePaiement;
    private Double montantPaiement;

    private DeclarationDto declaration;

    public static PaiementDto fromEntity(Paiement paiement) {
        if (paiement == null) {
            return null;
            // TODO throw an exception

        }
        return PaiementDto.builder()
                .id(paiement.getId())
                .datePaiement(paiement.getDatePaiement())
                .montantPaiement(paiement.getMontantPaiement())
                .declaration(DeclarationDto.fromEntity(paiement.getDeclaration()))

                .build();
    };

    public static Paiement toEntity(PaiementDto paiementDto) {
        if (paiementDto == null) {
            return null;
            // TODO throw an exception

        }
        Paiement paiement = new Paiement();
        paiement.setId(paiementDto.getId());
        paiement.setMontantPaiement(paiementDto.getMontantPaiement());
        paiement.setDatePaiement(paiementDto.getDatePaiement());
        paiement.setDeclaration(DeclarationDto.toEntity(paiementDto.getDeclaration()));


        return paiement;
    };
}
