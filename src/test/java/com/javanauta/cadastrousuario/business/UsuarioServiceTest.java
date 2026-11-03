package com.javanauta.cadastrousuario.business;

import com.javanauta.cadastrousuario.api.converter.UsuarioConverter;
import com.javanauta.cadastrousuario.api.converter.UsuarioMapper;
import com.javanauta.cadastrousuario.api.converter.UsuarioUpdateMapper;
import com.javanauta.cadastrousuario.api.request.EnderecoRequestDTO;
import com.javanauta.cadastrousuario.api.request.EnderecoRequestDTOFixture;
import com.javanauta.cadastrousuario.api.request.UsuarioRequestDTO;
import com.javanauta.cadastrousuario.api.request.UsuarioRequestDTOFixture;
import com.javanauta.cadastrousuario.api.response.EnderecoResponseDTO;
import com.javanauta.cadastrousuario.api.response.EnderecoResponseDTOFixture;
import com.javanauta.cadastrousuario.api.response.UsuarioResponseDTO;
import com.javanauta.cadastrousuario.api.response.UsuarioResponseDTOFixture;
import com.javanauta.cadastrousuario.infrastructure.entities.EnderecoEntity;
import com.javanauta.cadastrousuario.infrastructure.entities.UsuarioEntity;
import com.javanauta.cadastrousuario.infrastructure.repository.UsuarioRepository;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {


    @InjectMocks
    private UsuarioService usuarioService;


    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioUpdateMapper usuarioUpdateMapper;


    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioConverter usuarioConverter;

    UsuarioEntity usuarioEntity;

    EnderecoEntity enderecoEntity;

    UsuarioRequestDTO usuarioRequestDTO;
    EnderecoRequestDTO enderecoRequestDTO;
    UsuarioResponseDTO usuarioResponseDTO;

    EnderecoResponseDTO enderecoResponseDTO;
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
        enderecoResponseDTO = EnderecoResponseDTOFixture.build(
                "campo grande",
                123456L,
                "nossa senhora da saude",
                "casa",
                "Piranhas",
                "77777777777"
        );
        usuarioResponseDTO = UsuarioResponseDTOFixture.build(12345L,"usuario", "usuario@gmail.com","123456", enderecoResponseDTO);

    }


    @Test
    public void testLoginSucesso(){

    }



    @Test
    void deveSalvarUsuarioComSucesso(){
        when(usuarioRepository.saveAndFlush(usuarioEntity)).thenReturn(usuarioEntity);


        UsuarioEntity entity = usuarioService.salvaUsuario(usuarioEntity);

        assertEquals(entity,usuarioEntity);
        verify(usuarioRepository).saveAndFlush(usuarioEntity);
        verifyNoMoreInteractions(usuarioRepository);


    }


    @Test
    void deveGravarUsuarioComSucesso(){


        when(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO)).thenReturn(usuarioEntity);
        when(usuarioRepository.saveAndFlush(usuarioEntity)).thenReturn(usuarioEntity);
        when(usuarioMapper.paraUsuarioResponseDTO(usuarioEntity)).thenReturn(usuarioResponseDTO);


        UsuarioResponseDTO dto = usuarioService.gravarUsuarios(usuarioRequestDTO);


        assertEquals(dto, usuarioResponseDTO);
        verify(usuarioConverter).paraUsuarioEntity(usuarioRequestDTO);
        verify(usuarioRepository).saveAndFlush(usuarioEntity);
        verify(usuarioMapper).paraUsuarioResponseDTO(usuarioEntity);

        verifyNoMoreInteractions(usuarioRepository,usuarioConverter,usuarioMapper);
    }

}