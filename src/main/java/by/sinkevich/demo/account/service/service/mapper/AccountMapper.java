package by.sinkevich.demo.account.service.service.mapper;

import by.sinkevich.demo.account.service.domain.Account;
import by.sinkevich.demo.account.service.service.dto.AccountDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Named("toAccountName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "currency", target = "currency")
    AccountDTO toAccountName(Account account);

    @Named("toAccount")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "currency", target = "currency")
    Account toAccount(AccountDTO account);

}
