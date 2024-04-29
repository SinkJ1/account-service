package by.sinkevich.demo.account.service.web;

import by.sinkevich.demo.account.service.service.ClientService;
import by.sinkevich.demo.account.service.service.dto.ClientDTO;
import by.sinkevich.demo.account.service.service.impl.OperationServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "ClientResource", description = "Endpoint for creating clients. After creating the client, the client will be returned with a list of his accounts; you need to take the ID of the created account to carry out operations")
public class ClientResource {
    private final Logger log = LoggerFactory.getLogger(ClientResource.class);

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("")
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO) {
        log.debug("REST request to save account");
        return ResponseEntity.ok(clientService.save(clientDTO));
    }

}
