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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class UsuarioConverterTest {

    @InjectMocks
    UsuarioConverter usuarioConverter;

    @Mock
    Clock clock;
    UsuarioEntity usuarioEntity;

    EnderecoEntity enderecoEntity;

    UsuarioRequestDTO usuarioRequestDTO;
    EnderecoRequestDTO enderecoRequestDTO;

    LocalDateTime dataHora;

    @BeforeEach
    public void setup(){
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
                .nome("usuario")
                .documento("123456")
                .email("usuario@gmail.com")
                .dataCadastro(dataHora)
                .endereco(enderecoEntity)
                .build();
        enderecoRequestDTO = EnderecoRequestDTOFixture.build(
                "campo grande",
                123456L,
                "nossa senhora da saude",
                "casa",
                "Piranhas",
                "77777777777"
        );
        usuarioRequestDTO = UsuarioRequestDTOFixture.build("usuario", "usuario@gmail.com", "123456", enderecoRequestDTO);

        ZoneId zoneId = ZoneId.systemDefault();
        Clock fixedClock = Clock.fixed(dataHora.atZone(zoneId).toInstant(),zoneId);
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }


    @Test
    void deveConverterParaUsuarioEntity(){

        UsuarioEntity entity = usuarioConverter.paraUsuarioEntity(usuarioRequestDTO);

        assertEquals(usuarioEntity.getNome(), entity.getNome());
        assertEquals(usuarioEntity.getDocumento(), entity.getDocumento());
        assertEquals(usuarioEntity.getEmail(), entity.getEmail());
        assertEquals(usuarioEntity.getDataCadastro(), entity.getDataCadastro());

        assertEquals(usuarioEntity.getEndereco().getRua(), entity.getEndereco().getRua());
        assertEquals(usuarioEntity.getEndereco().getBairro(), entity.getEndereco().getBairro());
        assertEquals(usuarioEntity.getEndereco().getCep(), entity.getEndereco().getCep());
        assertEquals(usuarioEntity.getEndereco().getCidade(), entity.getEndereco().getCidade());
        assertEquals(usuarioEntity.getEndereco().getNumero(), entity.getEndereco().getNumero());
        assertEquals(usuarioEntity.getEndereco().getComplemento(), entity.getEndereco().getComplemento());
    }


}