package users;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import net.sf.json.JSONObject;

public class loginController extends Controller {
    @ActionKey("/login")
    public void login() {
        boolean flag = false;
        String msg = "";
        String name = getPara("Username");
        String password = getPara("Password");
        JSONObject json = new JSONObject();

        if("wang".equals(name)) {
            flag = true;
            render("/home.jsp");
        }
        else {
            msg = "account not exist";
            json.put("flag", flag);
            json.put("msg", msg);
            renderJson(json);
        }
    }

    @ActionKey("/manage")
    public void manage() {
        render("home.jsp");
    }
}
