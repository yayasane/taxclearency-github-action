package sn.yayaveli.taxclearancesystem.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(callSuper = true)
@Entity
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDeclaration;
    private Double montantDeclaration;
    @ManyToOne
    private Declarant declarant;
    @OneToMany(mappedBy = "declaration")
    private List<Paiement> paiements;

    @Override
    public String toString() {
        return "Declaration{" +
                "id=" + id +
                ", dateDeclaration=" + dateDeclaration +
                ", montantDeclaration=" + montantDeclaration +
                ", declarant=" + declarant +
                ", paiements=" + paiements +
                '}';
    }
// getters and setters
}
