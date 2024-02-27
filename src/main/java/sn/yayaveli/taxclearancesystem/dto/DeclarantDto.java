package sn.yayaveli.taxclearancesystem.dto;

import lombok.Builder;
import lombok.Data;
import sn.yayaveli.taxclearancesystem.models.Declarant;
import sn.yayaveli.taxclearancesystem.models.Declaration;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class DeclarantDto {
    private Long id;
    private String raisonSociale;
    private String adresse;
    private String email;
    private String telephone;
    private List<DeclarationDto> declarations;

    public static DeclarantDto fromEntity(Declarant declarant) {
        if (declarant == null) {
            return null;
            // TODO throw an exception

        }
        //List<Declaration> declarations = declarant.getDeclarations();
        //System.out.println("before print toString");
        //System.out.println(declarations.size());
        //System.out.println("after print toString");

        return DeclarantDto.builder()
                .id(declarant.getId())
                .raisonSociale(declarant.getRaisonSociale())
                .email(declarant.getEmail())
                .adresse(declarant.getAdresse())
                .telephone(declarant.getTelephone())
                //.declarations(declarations.stream().map(DeclarationDto::fromEntity).toList())
                .build();
    };

    public static Declarant toEntity(DeclarantDto declarantDto) {
        if (declarantDto == null) {
            return null;
            // TODO throw an exception

        }
        Declarant declarant = new Declarant();
        declarant.setId(declarantDto.getId());
        declarant.setRaisonSociale(declarantDto.getRaisonSociale());
        declarant.setEmail(declarantDto.getEmail());
        declarant.setAdresse(declarantDto.getAdresse());
        declarant.setTelephone(declarantDto.getTelephone());

        return declarant;
    };
}
