package by.sinkevich.demo.account.service.service.mapper;

import by.sinkevich.demo.account.service.domain.Operation;
import by.sinkevich.demo.account.service.service.dto.OperationDTO;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface OperationMapper{
    Operation toEntity(OperationDTO operationDTO);
    OperationDTO toDto(Operation operation);

}
