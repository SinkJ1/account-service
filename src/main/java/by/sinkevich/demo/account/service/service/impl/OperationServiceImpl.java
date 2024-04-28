package by.sinkevich.demo.account.service.service.impl;

import by.sinkevich.demo.account.service.domain.Operation;
import by.sinkevich.demo.account.service.repository.OperationRepository;
import by.sinkevich.demo.account.service.service.AccountService;
import by.sinkevich.demo.account.service.service.OperationService;
import by.sinkevich.demo.account.service.service.dto.OperationDTO;
import by.sinkevich.demo.account.service.service.mapper.OperationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationMapper operationMapper;
    private final OperationRepository operationRepository;
    private final AccountService accountService;

    public OperationServiceImpl(
            OperationMapper operationMapper,
            OperationRepository operationRepository,
            AccountService accountService) {
        this.operationMapper = operationMapper;
        this.operationRepository = operationRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public OperationDTO save(OperationDTO operationDTO) {
        Operation operation = operationMapper.toEntity(operationDTO);
        operation = operationRepository.save(operation);
        return operationMapper.toDto(operation);
    }
}
