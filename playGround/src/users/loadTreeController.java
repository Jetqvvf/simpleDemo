package users;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import net.sf.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class loadTreeController extends Controller {
    private List<TreeNodeInfo> treeList = new ArrayList<TreeNodeInfo>();

    @ActionKey("/loadTreeJson")
    public void loadTreeJson() {
        String id = getPara("nodeid");
        JSONObject mm = new JSONObject();
        String path = "/home/wang/IdeaProjects/playGround/database/";
        File root = new File(path);

        if((id==null) || "".equals(id))
            treeList = showAllFiles(root, new ArrayList<TreeNodeInfo>(), 0);

        mm.put("treeJson", treeList);
        renderJson(mm);
    }

    public List showAllFiles(File dir, ArrayList<TreeNodeInfo> tempList, int idx) {
        File[] fs = dir.listFiles();

        for(int i = 0; i < fs.length; i++) {
            TreeNodeInfo e = new TreeNodeInfo();
            e.setId(i+""+String.valueOf(idx));
            e.setText(fs[i].getName());
            Map<String, Object> attributes = new HashMap<String, Object>();


            if(fs[i].isDirectory()) {
                try {
                    e.setAttributes(attributes);
                    tempList.add(e);
                    e.setChildren(showAllFiles(fs[i], new ArrayList<TreeNodeInfo>(), idx++));
                }
                catch (Exception ex) {}
            }
            else {
                attributes.put("path",fs[i].getAbsolutePath());
                e.setAttributes(attributes);
                tempList.add(e);
            }
        }

        return tempList;
    }
}
