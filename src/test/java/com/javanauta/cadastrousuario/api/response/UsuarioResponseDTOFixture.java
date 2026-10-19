package com.javanauta.cadastrousuario.api.response;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioResponseDTOTest {

    public static UsuarioResponseDTO build(Long id,

                                           String nome,

                                           String email,

                                           String documento,

                                           EnderecoResponseDTO endereco){
        return new UsuarioResponseDTO(id,nome, email, documento, endereco);
    }

}