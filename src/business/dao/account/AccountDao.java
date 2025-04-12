package business.dao.account;

import business.model.Account;

public interface AccountDao {
    Account login(String username, String password);
}
