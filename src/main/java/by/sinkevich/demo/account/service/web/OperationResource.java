package by.sinkevich.demo.account.service.web;

import by.sinkevich.demo.account.service.service.OperationService;
import by.sinkevich.demo.account.service.service.dto.OperationDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operations")
@Tag(name = "OperationResource", description = "Endpoint for conducting operations on the client’s account")
public class OperationResource {
    private final Logger log = LoggerFactory.getLogger(OperationResource.class);
    private final OperationService operationService;

    public OperationResource(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("")
    public ResponseEntity<OperationDTO> save(@RequestBody @Valid OperationDTO operationDTO) {
        log.debug("REST request to save operation");
        return ResponseEntity.ok(operationService.save(operationDTO));
    }

}
