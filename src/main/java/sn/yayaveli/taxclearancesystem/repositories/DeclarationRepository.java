package sn.yayaveli.taxclearancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.yayaveli.taxclearancesystem.models.Declarant;
import sn.yayaveli.taxclearancesystem.models.Declaration;

import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    List<Declaration> findByDeclarantId(Long id);
}
