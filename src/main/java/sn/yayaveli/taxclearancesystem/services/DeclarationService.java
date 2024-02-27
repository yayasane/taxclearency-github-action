package sn.yayaveli.taxclearancesystem.services;

import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;

import java.util.List;

public interface DeclarationService {
    DeclarationDto save(DeclarationDto declarationDto);

    DeclarationDto findById(Long id);

    List<DeclarationDto> findAll();

    void deleteLong(Long id);
}
