package sn.yayaveli.taxclearancesystem.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(callSuper = true)
@Entity
public class Declarant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String raisonSociale;
    private String adresse;
    private String email;
    private String telephone;
    @JsonIgnore
    @OneToMany(mappedBy = "declarant")
    private List<Declaration> declarations = new ArrayList<>();;

    // getters and setters
}
