package users;

import java.util.List;
import java.util.Map;

public class TreeNodeInfo {
    private String id;                  //要显示的子节点的ID
    private String text;                //要显示的子节点的 Text
    private String state;
    private String iconCls;             //节点的图标
    private String parentId;            //父节点的ID
    private List<TreeNodeInfo>  children;   //孩子节点的List
    private boolean checked = false;
    //    private Map<String, Object>  attributes = new HashMap<String, Object>();
    private Map<String, Object>  attributes;

    public TreeNodeInfo() {
        super();
    }

    public TreeNodeInfo(String id, String text, String state, String iconCls,
                        String parentId, List<TreeNodeInfo> children, boolean checked,
                        Map<String, Object> attributes) {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
        this.iconCls = iconCls;
        this.parentId = parentId;
        this.children = children;
        this.checked = checked;
        this.attributes = attributes;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getIconCls() {
        return iconCls;
    }
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public List<TreeNodeInfo> getChildren() {
        return children;
    }
    public void setChildren(List<TreeNodeInfo> children) {
        this.children = children;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
