package sn.yayaveli.taxclearancesystem.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;
import sn.yayaveli.taxclearancesystem.exceptions.EntityNotFoundException;
import sn.yayaveli.taxclearancesystem.exceptions.ErrorCodes;
import sn.yayaveli.taxclearancesystem.exceptions.InvalidEntityException;
import sn.yayaveli.taxclearancesystem.models.Declarant;
import sn.yayaveli.taxclearancesystem.repositories.DeclarantRepository;
import sn.yayaveli.taxclearancesystem.repositories.DeclarationRepository;
import sn.yayaveli.taxclearancesystem.services.DeclarantService;
import sn.yayaveli.taxclearancesystem.validators.DeclarantValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeclarantServiceImpl implements DeclarantService {
    private DeclarantRepository declarantRepository;
    private DeclarationRepository declarationRepository;

    @Autowired
    public DeclarantServiceImpl(DeclarantRepository declarantRepository, DeclarationRepository declarationRepository) {
        this.declarantRepository = declarantRepository;
        this.declarationRepository = declarationRepository;
    }

    @Override
    public DeclarantDto save(DeclarantDto declarantDto) {
        List<String> errors = DeclarantValidator.validate(declarantDto);
        if (!errors.isEmpty()) {
            log.error("Declarant i not valid {}", declarantDto);
            throw new InvalidEntityException("Le déclarant n'est pas valide", ErrorCodes.DECLARANT_NOT_VALID, errors);
        }
        return DeclarantDto.fromEntity(declarantRepository.save(DeclarantDto.toEntity(declarantDto)));
    }

    @Override
    public DeclarantDto findById(Long id) {
        if (id == null) {
            log.error("Declarant is null");
            return null;
        }

        return declarantRepository.findById(id).map(DeclarantDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun déclarant avec l'id = " + id,
                        ErrorCodes.DECLARANT_NOT_FOUND));
    }


    @Override
    public List<DeclarantDto> findAll() {
        return declarantRepository.findAll().stream().map(DeclarantDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<DeclarationDto> findDeclarantDeclarations(Long id) {
        if (id == null) {
            log.error("Declarant is null");
            return null;
        }

        if(!declarantRepository.existsById(id)) {
            throw new EntityNotFoundException("Aucun déclarant avec l'id = " + id,
                    ErrorCodes.DECLARANT_NOT_FOUND);
        }

        return declarationRepository.findByDeclarantId(id).stream().map(DeclarationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteLong(Long id) {
        if (id == null) {
            log.error("Declarant is null");
            return;
        }

        if(!declarantRepository.existsById(id)) {
            throw new EntityNotFoundException("Aucun déclarant avec l'id = " + id,
                    ErrorCodes.DECLARANT_NOT_FOUND);
        }
        declarantRepository.deleteById(id);
    }

}
