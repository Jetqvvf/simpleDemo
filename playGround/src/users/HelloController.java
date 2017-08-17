package users;

import com.jfinal.core.Controller;

public class HelloController extends Controller {
    public void index() {
        renderText("hello world");
    }
}
