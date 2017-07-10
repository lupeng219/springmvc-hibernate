package com.crdloo.loanloop.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Serializable {
    /**
     * <p>
     * Discription:[字段功能描述]
     * </p>
     */
    private static final long serialVersionUID = 6744340166551496713L;

    public static interface Converter<T> {
        public TreeNode toTreeNode(T t, String path);
    }

    private Long id;
    private String title;
    private String path;
    private Object data;

    private List<TreeNode> items = new ArrayList<TreeNode>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TreeNode> getItems() {
        return items;
    }

    public void setItems(List<TreeNode> items) {
        this.items = items;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
