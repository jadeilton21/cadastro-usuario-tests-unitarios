package com.javanauta.cadastrousuario.api.converter;

import com.javanauta.cadastrousuario.api.request.EnderecoRequestDTO;
import com.javanauta.cadastrousuario.api.request.EnderecoRequestDTOFixture;
import com.javanauta.cadastrousuario.api.request.UsuarioRequestDTO;
import com.javanauta.cadastrousuario.api.request.UsuarioRequestDTOFixture;
import com.javanauta.cadastrousuario.api.response.UsuarioResponseDTO;
import com.javanauta.cadastrousuario.infrastructure.entities.EnderecoEntity;
import com.javanauta.cadastrousuario.infrastructure.entities.UsuarioEntity;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioUpdateMapperTest {

    UsuarioUpdateMapper usuarioMapper;

    UsuarioEntity usuarioEntityEsperado;
    UsuarioEntity usuarioEntity;
    EnderecoEntity enderecoEntity;

    UsuarioRequestDTO usuarioRequestDTO;

    EnderecoRequestDTO enderecoRequestDTO;
    LocalDateTime dataHora;


    @BeforeEach
    public void sertup(){
        usuarioMapper = Mappers.getMapper(UsuarioUpdateMapper.class);
        dataHora = LocalDateTime.of(2023,10,05,14,12);
        enderecoEntity = EnderecoEntity.builder().rua("Rua teste").bairro("novo").id(2L).numero(39l).complemento("casa").cidade("Piranhas").cep("89988888").build();
        usuarioEntity = UsuarioEntity.builder().id(12L).nome("Jadeilton").email("jadeiltondm@gmail.com").documento("21344444").dataCadastro(dataHora).dataAtualizacao(dataHora).endereco(enderecoEntity).build();
        enderecoRequestDTO = EnderecoRequestDTOFixture.build("Rua teste", 39, "novo", "casa","Piranhas", "89988888" );
        usuarioRequestDTO = UsuarioRequestDTOFixture.build("Jadeilton", "jadeiltondm@gmail.com", "21344444",enderecoRequestDTO);

        usuarioEntityEsperado = usuarioEntity.builder().id(12L).nome("Jadeilton").email("jadeiltondm@gmail.com").documento("21344444").dataCadastro(dataHora).dataAtualizacao(dataHora).endereco(enderecoEntity).build();

    }


    @Test
    void deveConverterParaUsuarioResponseDTO(){
        UsuarioEntity entity = usuarioMapper.updateUsuarioFromDTO(usuarioRequestDTO, usuarioEntity);

        assertEquals(usuarioEntityEsperado, entity);
    }

}