package sn.yayaveli.taxclearancesystem.validators;

import org.springframework.util.StringUtils;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;

import java.util.ArrayList;
import java.util.List;

public class DeclarationValidator {
    public static List<String> validate(DeclarationDto declarationDto) {
        List<String> errors = new ArrayList<>();

        if (declarationDto == null) {
            errors.add("Veuillez renseigner le montant de la declaration");
            errors.add("Veuillez renseigner la date de la declaration");
            errors.add("Veuillez sélectionner un déclarant");
            return errors;
        }


        if (declarationDto.getMontantDeclaration() == null) {
            errors.add("Veuillez renseigner le montant de la declaration");
        }
        if (declarationDto.getDateDeclaration() == null) {
            errors.add("Veuillez renseigner la date de la declaration");
        }
        if (declarationDto.getDeclarant() == null) {
            errors.add("Veuillez sélectionner un déclarant");
        }
        return errors;
    }
}
