package sn.yayaveli.taxclearancesystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sn.yayaveli.taxclearancesystem.dto.DeclarantDto;
import sn.yayaveli.taxclearancesystem.exceptions.EntityNotFoundException;
import sn.yayaveli.taxclearancesystem.exceptions.ErrorCodes;
import sn.yayaveli.taxclearancesystem.exceptions.InvalidEntityException;
import sn.yayaveli.taxclearancesystem.services.DeclarantService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static sn.yayaveli.taxclearancesystem.utils.Constants.APP_ROOT;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeclarantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DeclarantService declarantService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void PostDeclarant_thenShouldCreatedSuccess() throws Exception {
        //Given
        DeclarantDto declarantDto = DeclarantDto.builder()
                .id(1L)
                .email("yabadji2010@gmail.com")
                .adresse("Cite gendarmerie")
                .raisonSociale("raison x")
                .telephone("781255000")
                .build();
        given(declarantService.save(any(DeclarantDto.class))).willReturn(declarantDto);

        //When
        ResultActions resultActions = this.mockMvc.perform(post("/" + APP_ROOT
                        + "/declarants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(declarantDto)))
        )
                .andDo(print());

        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("email").value("yabadji2010@gmail.com"))
                .andExpect(jsonPath("raisonSociale").value("raison x"))
                .andExpect(jsonPath("adresse").value("Cite gendarmerie"))
                .andExpect(jsonPath("telephone").value("781255000"));
    }


    @Test
    void PostDeclarant_thenShouldThrowInvalidEntityException() throws Exception {
        //Given
        DeclarantDto declarantDto = DeclarantDto.builder()
                .build();
        given(declarantService.save(any(DeclarantDto.class))).willThrow(new InvalidEntityException("Le déclarant n'est pas valide", ErrorCodes.DECLARANT_NOT_VALID, Collections.emptyList()));

        //When
        ResultActions resultActions = mockMvc.perform(post("/" + APP_ROOT + "/declarants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(declarantDto)))
        ).andDo(print());

        //Then
        resultActions.andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("httpCode").value(422))
                .andExpect(jsonPath("code").value(ErrorCodes.DECLARANT_NOT_VALID.getCode()))
                .andExpect(jsonPath("message").value("Le déclarant n'est pas valide"));
        then(declarantService).should().save(any(DeclarantDto.class));
    }

    @Test
    void GetDeclarantById_thenShouldGetSuccess() throws Exception {
        //Given
        DeclarantDto declarantDto = DeclarantDto.builder()
                .id(55L)
                .email("yabadji2010@gmail.com")
                .adresse("Cite gendarmerie")
                .raisonSociale("raison x")
                .telephone("781255000")
                .build();
        given(declarantService.findById(55L)).willReturn(declarantDto);

        //When
        ResultActions resultActions = this.mockMvc.perform(get("/" + APP_ROOT
                        + "/declarants/"+declarantDto.getId())
        )
                .andDo(print());

        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("id").value(55))
                .andExpect(jsonPath("email").value("yabadji2010@gmail.com"))
                .andExpect(jsonPath("raisonSociale").value("raison x"))
                .andExpect(jsonPath("adresse").value("Cite gendarmerie"))
                .andExpect(jsonPath("telephone").value("781255000"));
        then(declarantService).should().findById(55L);
    }

    @Test
    void GetDeclarantById_thenShouldReturnStatus404() throws Exception {
        //Given
        Long declarantId = 55L;
        given(declarantService.findById(declarantId)).willThrow(new EntityNotFoundException("Aucun déclarant avec l'id = " + declarantId, ErrorCodes.DECLARANT_NOT_FOUND));

        //When
        ResultActions resultActions = this.mockMvc.perform(get("/" + APP_ROOT
                + "/declarants/"+declarantId)
        )
                .andDo(print());

        //Then
        resultActions.andExpect(status().isNotFound())
                .andExpect(jsonPath("httpCode").value(404))
                .andExpect(jsonPath("code").value(ErrorCodes.DECLARANT_NOT_FOUND.getCode()))
                .andExpect(jsonPath("message").value("Aucun déclarant avec l'id = " + declarantId));
        then(declarantService).should().findById(declarantId);
    }

    @Test
    void GetDeclarations_thenShouldReturnStatus200() throws Exception {
        //Given
        List<DeclarantDto> declarantDtos = new ArrayList<>();
        given(declarantService.findAll()).willReturn(declarantDtos);
        //When
        ResultActions resultActions = this.mockMvc.perform(get("/" + APP_ROOT + "/declarants"))
                .andDo(print());

        //Then
        resultActions.andExpect(status().isOk());
        then(declarantService).should().findAll();

    }


    @Test
    void DeleteDeclarantById_thenShouldReturnStatus204() throws Exception {
        //Given
        Long declarantId = 1L;
        willDoNothing().given(declarantService).deleteLong(declarantId);

        //When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/" + APP_ROOT + "/declarants/02"))
                .andDo(print());
        //Then
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    void DeleteDeclarantById_thenShouldReturnStatus404() throws Exception {
        //Given
        Long declarantId = 5555L;
        willThrow(new EntityNotFoundException("Aucun déclarant avec l'id = " + declarantId,
                ErrorCodes.DECLARANT_NOT_FOUND)).given(declarantService).deleteLong(declarantId);

        //When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/" + APP_ROOT + "/declarants/5555"))
                .andDo(print());
        //Then
        resultActions.andExpect(status().isNotFound())
                .andExpect(jsonPath("httpCode").value(404))
                .andExpect(jsonPath("code").value(ErrorCodes.DECLARANT_NOT_FOUND.getCode()))
                .andExpect(jsonPath("message").value("Aucun déclarant avec l'id = " + declarantId));
        then(declarantService).should().deleteLong(declarantId);
    }

    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }
}