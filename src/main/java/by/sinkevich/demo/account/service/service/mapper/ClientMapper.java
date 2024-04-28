package by.sinkevich.demo.account.service.service.mapper;

import by.sinkevich.demo.account.service.domain.Client;
import by.sinkevich.demo.account.service.service.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDTO clientDTO);

    ClientDTO toDto(Client client);
}
