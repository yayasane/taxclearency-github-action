package sn.yayaveli.taxclearancesystem.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.dto.DeclarationDto;
import sn.yayaveli.taxclearancesystem.dto.PaiementDto;
import sn.yayaveli.taxclearancesystem.exceptions.EntityNotFoundException;
import sn.yayaveli.taxclearancesystem.exceptions.InvalidEntityException;
import sn.yayaveli.taxclearancesystem.models.Paiement;
import sn.yayaveli.taxclearancesystem.repositories.PaiementRepository;
import sn.yayaveli.taxclearancesystem.validators.PaiementValidator;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class PaiementServiceImplTest {

    @Mock
    private PaiementRepository paiementRepository;

    @Captor
    ArgumentCaptor<Paiement> paiementArgumentCaptor;

    private PaiementServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new PaiementServiceImpl(paiementRepository);
    }

    @Test
    void givenPaiementDto_whenAddPaiement_thenShouldSaveSuccess() {
        //Given
        DeclarantDto declarantDto = DeclarantDto.builder()
                .email("yabadji2010@gmail.com")
                .adresse("Cite gendarmerie")
                .raisonSociale("raison x")
                .telephone("781255000")
                .build();
        DeclarationDto declarationDto = DeclarationDto.builder()
                .montantDeclaration(Double.valueOf(10000))
                .dateDeclaration(Date.from(Instant.now()))
                .declarant(declarantDto)
                .build();

        PaiementDto paiementDto = PaiementDto.
                builder()
                .montantPaiement(Double.valueOf(10000))
                .datePaiement(Date.from(Instant.now()))
                .declaration(declarationDto)
                .build();

        //When
        underTest.save(paiementDto);

        //Then
        assertThat(PaiementValidator.validate(paiementDto)).isEqualTo(Collections.emptyList());
        then(paiementRepository).should().save(paiementArgumentCaptor.capture());
        Paiement capturedPaiement = paiementArgumentCaptor.getValue();
        assertThat(capturedPaiement).isEqualTo(PaiementDto.toEntity(paiementDto));
        then(paiementRepository).should().save(PaiementDto.toEntity(paiementDto));

    }

    @Test
    void givenPaiementDto_whenAddPaiement_thenShouldThrowInvalidEntityException(){
        //Given

        PaiementDto paiementDto = PaiementDto.
                builder()
                .build();

        //When
        //Then
        assertThatThrownBy(()->underTest.save(paiementDto))
                .isInstanceOf(InvalidEntityException.class)
                .hasMessage("Le paiement n'est pas valide");
        assertThat(PaiementValidator.validate(paiementDto)).isNotEqualTo(Collections.emptyList());
        then(paiementRepository).should(never()).save(any());
    }

    @Test
    void givenPaiementId_whenFindPaiementById_thenShouldGetSuccess() {
        System.out.println(Double.valueOf(10000));
        //Given
        Long paiementid = 28L;
        DeclarantDto declarantDto = DeclarantDto.builder()
                .email("yabadji2010@gmail.com")
                .adresse("Cite gendarmerie")
                .raisonSociale("raison x")
                .telephone("781255000")
                .build();
        DeclarationDto declarationDto = DeclarationDto.builder()
                .montantDeclaration(Double.valueOf(10000))
                .dateDeclaration(Date.from(Instant.now()))
                .declarant(declarantDto)
                .build();

        PaiementDto paiementDto = PaiementDto.
                builder()
                .id(paiementid)
                .montantPaiement(Double.valueOf(10000))
                .datePaiement(Date.from(Instant.now()))
                .declaration(declarationDto)
                .build();
        given(paiementRepository.findById(paiementid)).willReturn(Optional.of(PaiementDto.toEntity(paiementDto)));

        //When
        underTest.findById(paiementid);

        //Then
        then(paiementRepository).should().findById(paiementid);
    }

    @Test
    void givenNullForPaiementId_whenFindPaiementById_thenShouldntDoAnything() {
        //Given
        Long paiementOrderId = null;

        //when
        underTest.findById(paiementOrderId);

        //then
        then(paiementRepository).should(never()).findById(any());


    }

    @Test
    void givenPaiementId_whenFindPaiementById_thenShouldThrowEntityNotFoundException(){
        //Given
        Long paiementId = 28L;
        given(paiementRepository.findById(paiementId)).willReturn(Optional.empty());

        //When
        //Then
        assertThatThrownBy(()->underTest.findById(paiementId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Aucun paiement avec l'id = " + paiementId);
        then(paiementRepository).should().findById(paiementId);
    }

    @Test
    void whenFindAllPaiements_thenShouldGetSuccess() {
        //When
        underTest.findAll();

        //Then
        then(paiementRepository).should().findAll();
    }

    @Test
    void givenPaiementId_whenDeletePaiementById_thenShouldSaveSuccess() {
        //Given
        Long paiementId = 28L;
        given(paiementRepository.existsById(paiementId)).willReturn(true);

        //When
        underTest.deleteLong(paiementId);

        //Then
        then(paiementRepository).should().existsById(paiementId);
        then(paiementRepository).should().deleteById(paiementId);
    }

    @Test
    void givenNullForPaiementId_whenDeletePaiementById_thenShouldDoAnything(){
        //Given
        Long paiementId = null;

        //When
        underTest.deleteLong(paiementId);

        //Then
        then(paiementRepository).should(never()).deleteById(any());
        then(paiementRepository).should(never()).existsById(any());
    }

    @Test
    void givenPaiementId_whenDeletePaiementById_thenShouldThrowEntityNotFoundException (){
        //Given
        Long paiementId = 28L;
        given(paiementRepository.existsById(paiementId)).willReturn(false);

        //When
        //Then
        assertThatThrownBy(()->underTest.deleteLong(paiementId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Aucun paiement avec l'id = " + paiementId);
        then(paiementRepository).should().existsById(paiementId);
        then(paiementRepository).should(never()).deleteById(any());
    }
}