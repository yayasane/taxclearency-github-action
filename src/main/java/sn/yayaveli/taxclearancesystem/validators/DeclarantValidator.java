package sn.yayaveli.taxclearancesystem.validators;


import org.springframework.util.StringUtils;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;

import java.util.ArrayList;
import java.util.List;

public class DeclarantValidator {

    public static List<String> validate(DeclarantDto declarantDto) {
        List<String> errors = new ArrayList<>();

        if (declarantDto == null) {
            errors.add("Veuillez renseigner l'adresse du déclarant");
            errors.add("Veuillez renseigner la raison social du déclarant");
            errors.add("Veuillez renseigner l'email du déclarant");
            errors.add("Veuillez renseigner le téléphone du déclarant");
            return errors;
        }


        if (!StringUtils.hasLength(declarantDto.getAdresse())) {
            errors.add("Veuillez renseigner l'adresse du déclarant");
        }

        if (!StringUtils.hasLength(declarantDto.getRaisonSociale())) {
            errors.add("Veuillez renseigner la raison social du déclarant");
        }
        if (!StringUtils.hasLength(declarantDto.getEmail())) {
            errors.add("Veuillez renseigner l'email du déclarant");
        }
        if (!StringUtils.hasLength(declarantDto.getTelephone())) {
            errors.add("Veuillez renseigner le téléphone du déclarant");
        }


        return errors;
    }
}
