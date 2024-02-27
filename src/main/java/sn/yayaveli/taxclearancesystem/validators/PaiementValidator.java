package sn.yayaveli.taxclearancesystem.validators;

import sn.yayaveli.taxclearancesystem.dto.PaiementDto;

import java.util.ArrayList;
import java.util.List;

public class PaiementValidator {
    public static List<String> validate(PaiementDto declarationDto) {
        List<String> errors = new ArrayList<>();

        if (declarationDto == null) {
            errors.add("Veuillez renseigner le montant du paiement");
            errors.add("Veuillez renseigner la date du paiement");
            errors.add("Veuillez sélectionner un déclarant");
            return errors;
        }


        if (declarationDto.getMontantPaiement() == null) {
            errors.add("Veuillez renseigner le montant du paiement");
        }
        if (declarationDto.getDatePaiement() == null) {
            errors.add("Veuillez renseigner la date du paiement");
        }
        if (declarationDto.getDeclaration() == null) {
            errors.add("Veuillez sélectionner une déclaration");
        }
        return errors;
    }
}
