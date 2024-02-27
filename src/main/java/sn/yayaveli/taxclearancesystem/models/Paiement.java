package sn.yayaveli.taxclearancesystem.models;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(callSuper = true)
@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datePaiement;
    private Double montantPaiement;
    @ManyToOne
    private Declaration declaration;

    // getters and setters
}
