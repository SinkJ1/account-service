package by.sinkevich.demo.account.service.web;

import by.sinkevich.demo.account.service.service.ClientService;
import by.sinkevich.demo.account.service.service.dto.ClientDTO;
import by.sinkevich.demo.account.service.service.impl.OperationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
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
