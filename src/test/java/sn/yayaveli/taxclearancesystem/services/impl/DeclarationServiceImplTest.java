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
import sn.yayaveli.taxclearancesystem.exceptions.EntityNotFoundException;
import sn.yayaveli.taxclearancesystem.exceptions.InvalidEntityException;
import sn.yayaveli.taxclearancesystem.models.Declaration;
import sn.yayaveli.taxclearancesystem.repositories.DeclarationRepository;
import sn.yayaveli.taxclearancesystem.validators.DeclarationValidator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class DeclarationServiceImplTest {

    @Mock
    private DeclarationRepository declarationRepository;

    @Captor
    ArgumentCaptor<Declaration> declarationArgumentCaptor;

    private DeclarationServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new DeclarationServiceImpl(declarationRepository);
    }

    @Test
    void givenDeclarationDto_whenAddDeclaration_thenShouldSaveSuccess() {
        //Given
        DeclarantDto declarantDto = DeclarantDto.builder()
                .email("yabadji2010@gmail.com")
                .adresse("Cite gendarmerie")
                .raisonSociale("raison x")
                .telephone("781255000")
                .build();

        DeclarationDto declarationDto = DeclarationDto.
                builder()
                .montantDeclaration(Double.valueOf(10000))
                .dateDeclaration(Date.from(Instant.now()))
                .declarant(declarantDto)
                .build();

        //When
        underTest.save(declarationDto);

        //Then
        assertThat(DeclarationValidator.validate(declarationDto)).isEqualTo(Collections.emptyList());
        then(declarationRepository).should().save(declarationArgumentCaptor.capture());
        Declaration capturedDeclaration = declarationArgumentCaptor.getValue();
        assertThat(capturedDeclaration).isEqualTo(DeclarationDto.toEntity(declarationDto));
        then(declarationRepository).should().save(DeclarationDto.toEntity(declarationDto));

    }

    @Test
    void givenDeclarationDto_whenAddDeclaration_thenShouldThrowInvalidEntityException(){
        //Given

        DeclarationDto declarationDto = DeclarationDto.
                builder()
                .build();

        //When
        //Then
        assertThatThrownBy(()->underTest.save(declarationDto))
                .isInstanceOf(InvalidEntityException.class)
                .hasMessage("La déclaration n'est pas valide");
        assertThat(DeclarationValidator.validate(declarationDto)).isNotEqualTo(Collections.emptyList());
        then(declarationRepository).should(never()).save(any());
    }

    @Test
    void givenDeclarationId_whenFindDeclarationById_thenShouldGetSuccess() {
        //Given
        Long declarationid = 28L;
        DeclarantDto declarantDto = DeclarantDto.builder()
                .email("yabadji2010@gmail.com")
                .adresse("Cite gendarmerie")
                .raisonSociale("raison x")
                .telephone("781255000")
                .build();

        DeclarationDto declarationDto = DeclarationDto.
                builder()
                .id(declarationid)
                .montantDeclaration(Double.valueOf(10000))
                .dateDeclaration(Date.from(Instant.now()))
                .declarant(declarantDto)
                .build();
        given(declarationRepository.findById(declarationid)).willReturn(Optional.of(DeclarationDto.toEntity(declarationDto)));

        //When
        underTest.findById(declarationid);

        //Then
        then(declarationRepository).should().findById(declarationid);
    }

    @Test
    void givenNullForDeclarationId_whenFindDeclarationById_thenShouldntDoAnything() {
        //Given
        Long declarationOrderId = null;

        //when
        underTest.findById(declarationOrderId);

        //then
        then(declarationRepository).should(never()).findById(any());


    }

    @Test
    void givenDeclarationId_whenFindDeclarationById_thenShouldThrowEntityNotFoundException(){
        //Given
        Long declarationId = 28L;
        given(declarationRepository.findById(declarationId)).willReturn(Optional.empty());

        //When
        //Then
        assertThatThrownBy(()->underTest.findById(declarationId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Aucune déclaration avec l'id = " + declarationId);
        then(declarationRepository).should().findById(declarationId);
    }

    @Test
    void whenFindAllDeclarations_thenShouldGetSuccess() {
        //When
        underTest.findAll();

        //Then
        then(declarationRepository).should().findAll();
    }

    @Test
    void givenDeclarationId_whenDeleteDeclarationById_thenShouldSaveSuccess() {
        //Given
        Long declarationId = 28L;
        given(declarationRepository.existsById(declarationId)).willReturn(true);

        //When
        underTest.deleteLong(declarationId);

        //Then
        then(declarationRepository).should().existsById(declarationId);
        then(declarationRepository).should().deleteById(declarationId);
    }

    @Test
    void givenNullForDeclarationId_whenDeleteDeclarationById_thenShouldDoAnything(){
        //Given
        Long declarationId = null;

        //When
        underTest.deleteLong(declarationId);

        //Then
        then(declarationRepository).should(never()).deleteById(any());
        then(declarationRepository).should(never()).existsById(any());
    }

    @Test
    void givenDeclarationId_whenDeleteDeclarationById_thenShouldThrowEntityNotFoundException (){
        //Given
        Long declarationId = 28L;
        given(declarationRepository.existsById(declarationId)).willReturn(false);

        //When
        //Then
        assertThatThrownBy(()->underTest.deleteLong(declarationId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Aucune déclaration avec l'id = " + declarationId);
        then(declarationRepository).should().existsById(declarationId);
        then(declarationRepository).should(never()).deleteById(any());
    }
}