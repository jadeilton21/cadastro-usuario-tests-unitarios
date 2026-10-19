package com.javanauta.cadastrousuario.api.converter;


import com.javanauta.cadastrousuario.api.response.EnderecoResponseDTO;
import com.javanauta.cadastrousuario.api.response.EnderecoResponseDTOFixture;
import com.javanauta.cadastrousuario.api.response.UsuarioResponseDTO;
import com.javanauta.cadastrousuario.api.response.UsuarioResponseDTOFixture;
import com.javanauta.cadastrousuario.infrastructure.entities.EnderecoEntity;
import com.javanauta.cadastrousuario.infrastructure.entities.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class UsuarioMapperTest {



    UsuarioMapper usuarioMapper;
    UsuarioEntity usuarioEntity;
    EnderecoEntity enderecoEntity;
    UsuarioResponseDTO usuarioResponseDTO;
    EnderecoResponseDTO enderecoResponseDTO;

    LocalDateTime dataHora;

    @BeforeEach
    public void setup(){
        usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
        dataHora = LocalDateTime.of(2026,10, 05, 12, 8);
        enderecoEntity = EnderecoEntity.builder()
                .rua("campo grande")
                .bairro("nossa senhora da saude")
                .cep("77777777777")
                .cidade("Piranhas")
                .numero(123456L)
                .complemento("casa")
                .build();

        usuarioEntity = UsuarioEntity.builder()
                .id(123456L)
                .nome("usuario")
                .documento("123456")
                .email("usuario@gmail.com")
                .dataCadastro(dataHora)
                .endereco(enderecoEntity)
                .build();
        enderecoResponseDTO = EnderecoResponseDTOFixture.build(
                "campo grande",
                123456L,
                "nossa senhora da saude",
                "casa",
                "Piranhas",
                "77777777777"
        );
        usuarioResponseDTO = UsuarioResponseDTOFixture.build(123456L, "usuario", "usuario@gmail.com", enderecoResponseDTO);


    }

    @Test
    void deveConverterParaUsuarioResponseDTO(){

        UsuarioResponseDTO dto = usuarioMapper.paraUsuarioResponseDTO(usuarioEntity);

        assertEquals(usuarioResponseDTO, dto);
    }



    
}