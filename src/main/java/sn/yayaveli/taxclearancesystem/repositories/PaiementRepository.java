package sn.yayaveli.taxclearancesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.yayaveli.taxclearancesystem.models.Declaration;
import sn.yayaveli.taxclearancesystem.models.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

}
