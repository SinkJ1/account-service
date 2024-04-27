package by.sinkevich.demo.account.service.service.impl;

import by.sinkevich.demo.account.service.domain.Operation;
import by.sinkevich.demo.account.service.repository.OperationRepository;
import by.sinkevich.demo.account.service.service.OperationService;
import by.sinkevich.demo.account.service.service.dto.OperationDTO;
import by.sinkevich.demo.account.service.service.mapper.OperationMapper;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationMapper operationMapper;
    private final OperationRepository operationRepository;

    public OperationServiceImpl(
            OperationMapper operationMapper,
            OperationRepository operationRepository
    ) {
        this.operationMapper = operationMapper;
        this.operationRepository = operationRepository;
    }

    @Override
    public OperationDTO save(OperationDTO operationDTO) {
        Operation operation = operationMapper.toEntity(operationDTO);
        operation = operationRepository.save(operation);
        return operationMapper.toDto(operation);
    }
}
