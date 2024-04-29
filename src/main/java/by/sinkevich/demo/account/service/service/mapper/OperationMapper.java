package by.sinkevich.demo.account.service.service.mapper;

import by.sinkevich.demo.account.service.domain.Operation;
import by.sinkevich.demo.account.service.service.dto.OperationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface OperationMapper{
    @Mapping(source = "account", target = "account", qualifiedByName = "toAccount")
    Operation toEntity(OperationDTO operationDTO);
    @Mapping(source = "account", target = "account", qualifiedByName = "toAccountName")
    OperationDTO toDto(Operation operation);

}
