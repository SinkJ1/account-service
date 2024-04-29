package by.sinkevich.demo.account.service.service.impl;

import by.sinkevich.demo.account.service.domain.Operation;
import by.sinkevich.demo.account.service.repository.OperationRepository;
import by.sinkevich.demo.account.service.service.AccountService;
import by.sinkevich.demo.account.service.service.OperationService;
import by.sinkevich.demo.account.service.service.dto.OperationDTO;
import by.sinkevich.demo.account.service.service.mapper.OperationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationServiceImpl implements OperationService {
    private final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);
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
    @Transactional
    public OperationDTO save(OperationDTO operationDTO) {
        log.debug("Request to save operation");
        Operation operation = operationMapper.toEntity(operationDTO);
        accountService.changeAmount(operationDTO.getAccount(), operation.getAmount(), operation.getType());
        operation = operationRepository.save(operation);
        return operationMapper.toDto(operation);
    }
}
