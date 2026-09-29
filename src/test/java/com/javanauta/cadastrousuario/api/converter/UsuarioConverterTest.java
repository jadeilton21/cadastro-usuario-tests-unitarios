package com.javanauta.cadastrousuario.api.converter;

import com.javanauta.cadastrousuario.api.request.EnderecoRequestDTO;
import com.javanauta.cadastrousuario.api.request.EnderecoRequestDTOFixture;
import com.javanauta.cadastrousuario.api.request.UsuarioRequestDTO;
import com.javanauta.cadastrousuario.api.request.UsuarioRequestDTOFixture;
import com.javanauta.cadastrousuario.infrastructure.entities.EnderecoEntity;
import com.javanauta.cadastrousuario.infrastructure.entities.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UsuarioConverterTest {

    @InjectMocks
    UsuarioConverter usuarioConverter;

    UsuarioEntity usuarioEntity;

    EnderecoEntity enderecoEntity;

    UsuarioRequestDTO usuarioRequestDTO;
    EnderecoRequestDTO enderecoRequestDTO;



    @BeforeEach
    public void setup(){

        enderecoEntity = EnderecoEntity.builder().rua("campo grande").bairro("nossa senhora da saude").cep("77777777777").
                cidade("Piranhas").numero(1234L).complemento("Casa").build();

        usuarioEntity = UsuarioEntity.builder().nome("Usuario").documento("123456")
                .email("usuario@gmail.com").dataCadastro(LocalDateTime.now()).endereco(enderecoEntity).build();
        enderecoRequestDTO = EnderecoRequestDTOFixture.build("campo grande",123456L,"nossa senhora da saude","casa","Piranhas","8888888888" );
        usuarioRequestDTO = UsuarioRequestDTOFixture.build("usuario", "usuario@gmail.com", "123456", enderecoRequestDTO);

    }


    @Test

    void deveConverterParaUsuarioEntity(){

        UsuarioEntity entity = usuarioConverter.paraUsuarioEntity(usuarioRequestDTO);

        assertEquals(usuarioEntity,entity);
    }


}