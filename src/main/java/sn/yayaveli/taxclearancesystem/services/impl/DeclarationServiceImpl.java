package sn.yayaveli.taxclearancesystem.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;
import sn.yayaveli.taxclearancesystem.exceptions.EntityNotFoundException;
import sn.yayaveli.taxclearancesystem.exceptions.ErrorCodes;
import sn.yayaveli.taxclearancesystem.exceptions.InvalidEntityException;
import sn.yayaveli.taxclearancesystem.models.Declaration;
import sn.yayaveli.taxclearancesystem.repositories.DeclarationRepository;
import sn.yayaveli.taxclearancesystem.services.DeclarationService;
import sn.yayaveli.taxclearancesystem.validators.DeclarationValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeclarationServiceImpl implements DeclarationService {
    private DeclarationRepository declarationRepository;

    @Autowired
    public DeclarationServiceImpl(DeclarationRepository declarationRepository) {
        this.declarationRepository = declarationRepository;
    }

    @Override
    public DeclarationDto save(DeclarationDto declarationDto) {
        List<String> errors = DeclarationValidator.validate(declarationDto);
        if (!errors.isEmpty()) {
            log.error("Declaration i not valid {}", declarationDto);
            throw new InvalidEntityException("La déclaration n'est pas valide", ErrorCodes.DECLARATION_NOT_VALID, errors);
        }
        return DeclarationDto.fromEntity(declarationRepository.save(DeclarationDto.toEntity(declarationDto)));
    }

    @Override
    public DeclarationDto findById(Long id) {
        if (id == null) {
            log.error("Declaration is null");
            return null;
        }
        return declarationRepository.findById(id).map(DeclarationDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune déclaration avec l'id = " + id,
                        ErrorCodes.DECLARATION_NOT_FOUND));
    }



    @Override
    public List<DeclarationDto> findAll() {
        return declarationRepository.findAll().stream().map(DeclarationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteLong(Long id) {
        if (id == null) {
            log.error("Declaration is null");
            return;
        }
        if(!declarationRepository.existsById(id)) {
            throw new EntityNotFoundException("Aucune déclaration avec l'id = " + id,
                    ErrorCodes.DECLARATION_NOT_FOUND);
        }
        declarationRepository.deleteById(id);
    }

}
