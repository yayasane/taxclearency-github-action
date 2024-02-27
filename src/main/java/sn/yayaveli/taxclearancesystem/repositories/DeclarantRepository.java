package sn.yayaveli.taxclearancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.yayaveli.taxclearancesystem.models.Declarant;

public interface DeclarantRepository extends JpaRepository<Declarant, Long> {

}
