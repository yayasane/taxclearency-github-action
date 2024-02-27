package sn.yayaveli.taxclearancesystem.services;

import org.springframework.http.ResponseEntity;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;

import java.util.List;

public interface DeclarantService {
    DeclarantDto save(DeclarantDto declarantDto);

    DeclarantDto findById(Long id);

    List<DeclarantDto> findAll();
    List<DeclarationDto> findDeclarantDeclarations(Long id);

    void deleteLong(Long id);
}
