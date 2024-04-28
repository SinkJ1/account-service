package by.sinkevich.demo.account.service.service.mapper;

import by.sinkevich.demo.account.service.domain.Account;
import by.sinkevich.demo.account.service.domain.Client;
import by.sinkevich.demo.account.service.service.dto.AccountDTO;
import by.sinkevich.demo.account.service.service.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface ClientMapper {
    Client toEntity(ClientDTO clientDTO);

    @Mapping(source = "accounts", target = "accounts", qualifiedByName = "toAccountName")
    ClientDTO toDto(Client client);
}
