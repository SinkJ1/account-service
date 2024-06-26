package by.sinkevich.demo.account.service.repository;

import by.sinkevich.demo.account.service.domain.Account;
import by.sinkevich.demo.account.service.service.dto.AccountDTO;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findOneById(Long id);
}
