package sn.yayaveli.taxclearancesystem.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.dto.PaiementDto;
import sn.yayaveli.taxclearancesystem.exceptions.EntityNotFoundException;
import sn.yayaveli.taxclearancesystem.exceptions.ErrorCodes;
import sn.yayaveli.taxclearancesystem.exceptions.InvalidEntityException;
import sn.yayaveli.taxclearancesystem.models.Paiement;
import sn.yayaveli.taxclearancesystem.repositories.PaiementRepository;
import sn.yayaveli.taxclearancesystem.services.PaiementService;
import sn.yayaveli.taxclearancesystem.validators.PaiementValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaiementServiceImpl implements PaiementService {
    private PaiementRepository paiementRepository;

    @Autowired
    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public PaiementDto save(PaiementDto paiementDto) {
        List<String> errors = PaiementValidator.validate(paiementDto);
        if (!errors.isEmpty()) {
            log.error("Paiement i not valid {}", paiementDto);
            throw new InvalidEntityException("Le paiement n'est pas valide", ErrorCodes.PAIEMENT_NOT_VALID, errors);
        }
        return PaiementDto.fromEntity(paiementRepository.save(PaiementDto.toEntity(paiementDto)));
    }

    @Override
    public PaiementDto findById(Long id) {
        if (id == null) {
            log.error("Paiement is null");
            return null;
        }
        return paiementRepository.findById(id).map(PaiementDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun paiement avec l'id = " + id,
                        ErrorCodes.PAIEMENT_NOT_FOUND));
    }



    @Override
    public List<PaiementDto> findAll() {
        return paiementRepository.findAll().stream().map(PaiementDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteLong(Long id) {
        if (id == null) {
            log.error("Paiement is null");
            return;
        }
        if(!paiementRepository.existsById(id)) {
            throw new EntityNotFoundException("Aucun paiement avec l'id = " + id,
                    ErrorCodes.PAIEMENT_NOT_FOUND);
        }
        paiementRepository.deleteById(id);
    }

}
