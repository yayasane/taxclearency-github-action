package sn.yayaveli.taxclearancesystem.services.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.exceptions.EntityNotFoundException;
import sn.yayaveli.taxclearancesystem.exceptions.InvalidEntityException;
import sn.yayaveli.taxclearancesystem.models.Declarant;
import sn.yayaveli.taxclearancesystem.repositories.DeclarantRepository;
import sn.yayaveli.taxclearancesystem.repositories.DeclarationRepository;
import sn.yayaveli.taxclearancesystem.validators.DeclarantValidator;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class DeclarantServiceImplTest {
    
        @Mock
        private DeclarantRepository declarantRepository;
        @Mock
        private DeclarationRepository declarationRepository;

        @Captor
        ArgumentCaptor<Declarant> declarantArgumentCaptor;

        private DeclarantServiceImpl underTest;

        @BeforeEach
        void setUp() {
            underTest = new DeclarantServiceImpl(declarantRepository, declarationRepository);
        }

        @Test
        void givenDeclarantDto_whenAddDeclarant_thenShouldSaveSuccess() {
            //Given
            DeclarantDto declarantDto = DeclarantDto.builder()
                    .email("yabadji2010@gmail.com")
                    .adresse("Cite gendarmerie")
                    .raisonSociale("raison x")
                    .telephone("781255000")
                    .build();

            //When
            underTest.save(declarantDto);

            //Then
            assertThat(DeclarantValidator.validate(declarantDto)).isEqualTo(Collections.emptyList());
            then(declarantRepository).should().save(declarantArgumentCaptor.capture());
            Declarant capturedDeclarant = declarantArgumentCaptor.getValue();
            assertThat(capturedDeclarant).isEqualTo(DeclarantDto.toEntity(declarantDto));
            then(declarantRepository).should().save(DeclarantDto.toEntity(declarantDto));

        }

        @Test
        void givenDeclarantDto_whenAddDeclarant_thenShouldThrowInvalidEntityException(){
            //Given

            DeclarantDto declarantDto = DeclarantDto.
                    builder()
                    .build();

            //When
            //Then
            assertThatThrownBy(()->underTest.save(declarantDto))
                    .isInstanceOf(InvalidEntityException.class)
                    .hasMessage("Le déclarant n'est pas valide");
            assertThat(DeclarantValidator.validate(declarantDto)).isNotEqualTo(Collections.emptyList());
            then(declarantRepository).should(never()).save(any());
        }

        @Test
        void givenDeclarantId_whenFindDeclarantById_thenShouldGetSuccess() {
            //Given
            Long declarantid = 28L;
            DeclarantDto declarantDto = DeclarantDto.builder()
                    .id(declarantid)
                    .email("yabadji2010@gmail.com")
                    .adresse("Cite gendarmerie")
                    .raisonSociale("raison x")
                    .telephone("781255000")
                    .build();
            
            given(declarantRepository.findById(declarantid)).willReturn(Optional.of(DeclarantDto.toEntity(declarantDto)));

            //When
            underTest.findById(declarantid);

            //Then
            then(declarantRepository).should().findById(declarantid);
        }

        @Test
        void givenNullForDeclarantId_whenFindDeclarantById_thenShouldntDoAnything() {
            //Given
            Long declarantOrderId = null;

            //when
            underTest.findById(declarantOrderId);

            //then
            then(declarantRepository).should(never()).findById(any());


        }

        @Test
        void givenDeclarantId_whenFindDeclarantById_thenShouldThrowEntityNotFoundException(){
            //Given
            Long declarantId = 28L;
            given(declarantRepository.findById(declarantId)).willReturn(Optional.empty());

            //When
            //Then
            assertThatThrownBy(()->underTest.findById(declarantId))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Aucun déclarant avec l'id = " + declarantId);
            then(declarantRepository).should().findById(declarantId);
        }

        @Test
        void whenFindAllDeclarants_thenShouldGetSuccess() {
            //When
            underTest.findAll();

            //Then
            then(declarantRepository).should().findAll();
        }

        @Test
        void givenDeclarantId_whenDeleteDeclarantById_thenShouldSaveSuccess() {
            //Given
            Long declarantId = Long.valueOf(28);
            given(declarantRepository.existsById(declarantId)).willReturn(true);

            //When
            underTest.deleteLong(declarantId);

            //Then
            then(declarantRepository).should().existsById(declarantId);
            then(declarantRepository).should().deleteById(declarantId);
        }

        @Test
        void givenNullForDeclarantId_whenDeleteDeclarantById_thenShouldDoAnything(){
            //Given
            Long declarantId = null;

            //When
            underTest.deleteLong(declarantId);

            //Then
            then(declarantRepository).should(never()).deleteById(any());
            then(declarantRepository).should(never()).existsById(any());
        }

        @Test
        void givenDeclarantId_whenDeleteDeclarantById_thenShouldThrowEntityNotFoundException (){
            //Given
            Long declarantId = 28L;
            given(declarantRepository.existsById(declarantId)).willReturn(false);

            //When
            //Then
            assertThatThrownBy(()->underTest.deleteLong(declarantId))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessage("Aucun déclarant avec l'id = " + declarantId);
            then(declarantRepository).should().existsById(declarantId);
            then(declarantRepository).should(never()).deleteById(any());
        }
}
