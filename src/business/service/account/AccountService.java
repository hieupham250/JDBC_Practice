package business.service.account;

import business.model.Account;

public interface AccountService {
    Account login(String username, String password);
}
