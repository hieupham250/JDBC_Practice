package business.service.account;
import business.dao.account.AccountDao;
import business.dao.account.AccountDaoImp;
import business.model.Account;

public class AccountServiceImp implements AccountService {
    private final AccountDao accountDao;

    public AccountServiceImp() {
        accountDao = new AccountDaoImp();
    }

    @Override
    public Account login(String username, String password) {
        return accountDao.login(username, password);
    }
}
