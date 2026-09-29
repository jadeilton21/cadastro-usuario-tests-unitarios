package com.javanauta.cadastrousuario.api.response;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoResponseDTOTest {

    public static EnderecoResponseDTO build(String rua,

                                            Long numero,

                                            String bairro,

                                            String complemento,

                                            String cidade,

                                            String cep){
        return new EnderecoResponseDTO(rua, numero, bairro, complemento, cidade, cep);
    }

}