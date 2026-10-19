package com.javanauta.cadastrousuario.api.response;

public class UsuarioResponseDTOFixture {

    public static UsuarioResponseDTO build(Long id,

                                           String nome,

                                           String email,

                                           EnderecoResponseDTO endereco){
        return new UsuarioResponseDTO(id,nome, email, endereco);
    }

}