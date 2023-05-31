package Test;

import Business.UtenteBusiness;
import DAO.MockUtenteDAO;
import Model.LoginResponse;
import Model.Utente;
import org.junit.Assert;
import org.junit.Test;

public class UtenteBusinessTest {

    UtenteBusiness ub = UtenteBusiness.getInstance();

    String rightUsername = "mario";
    String rightPassword = "12345";
    String wrongUsername = "maria";
    String wrongPassword = "1234";

    public void setUp() throws Exception {

        MockUtenteDAO mockDao = new MockUtenteDAO();
        ub.setuDAO(mockDao);

        Utente u = new Utente();
        u.setUsername(rightUsername);
        u.setPassword(rightPassword);

        mockDao.add(u);
    }

    @Test
    public void loginTest() {

        LoginResponse res = ub.login(wrongUsername, wrongPassword);
        Assert.assertEquals(LoginResponse.LoginResult.USER_NOT_EXISTS, res.getResult());

        res = ub.login(rightUsername, wrongPassword);
        Assert.assertEquals(LoginResponse.LoginResult.WRONG_PASSWORD, res.getResult());

        res = ub.login(rightUsername, rightPassword);
        Assert.assertEquals(LoginResponse.LoginResult.LOGIN_OK, res.getResult());
    }
}
