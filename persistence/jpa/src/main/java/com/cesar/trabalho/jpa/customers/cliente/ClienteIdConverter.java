package com.cesar.trabalho.jpa.customers.cliente;

import com.cesar.trabalho.cliente.ClienteId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClienteIdConverter implements AttributeConverter<ClienteId, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClienteId clienteId) {
        return Math.toIntExact(clienteId != null ? clienteId.getId() : null);
    }

    @Override
    public ClienteId convertToEntityAttribute(Integer id) {
        return id != null ? new ClienteId(id) : null;
    }
}