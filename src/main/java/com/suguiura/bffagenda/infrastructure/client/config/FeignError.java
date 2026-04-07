package com.suguiura.bffagenda.infrastructure.client.config;

import com.suguiura.bffagenda.infrastructure.exceptions.BusinessException;
import com.suguiura.bffagenda.infrastructure.exceptions.ConflictException;
import com.suguiura.bffagenda.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.web.client.ResourceAccessException;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 409:
                return new ConflictException("Erro: atributo já existente");
            case 403:
                return new ResourceAccessException("Erro: atributo não encontrado");
            case 401:
                return new UnauthorizedException("Erro: usuário não autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
