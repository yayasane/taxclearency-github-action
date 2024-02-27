package sn.yayaveli.taxclearancesystem.services;

import sn.yayaveli.taxclearancesystem.dto.PaiementDto;

import java.util.List;

public interface PaiementService {
    PaiementDto save(PaiementDto paiementDto);

    PaiementDto findById(Long id);

    List<PaiementDto> findAll();

    void deleteLong(Long id);
}
