package users;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openFileController extends Controller {
    @ActionKey("/openFile")
    public void openFile() throws Exception {
        String path = getPara("path");
        File file = new File(path);
        JSONObject mm = new JSONObject();
        mm.put("isOK",false);

        if (!file.exists() || file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        temp = br.readLine();
        while (temp != null) {
            sb.append(temp + "\n");
            temp = br.readLine();
        }

        mm.put("isOK",true);
        mm.put("textcontent", sb.toString());
        mm.put("filename",getPara("node"));
        renderJson(mm);
    }
}
