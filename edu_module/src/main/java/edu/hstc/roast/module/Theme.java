package edu.hstc.roast.module;

import java.util.List;

public class Theme {
    private String name;
    private int id;
    private int parent_id;

    private Theme theme;
    private List<Theme> parent_themes;
    private List<Theme> child_theme;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<Theme> getParent_themes() {
        return parent_themes;
    }

    public void setParent_themes(List<Theme> parent_themes) {
        this.parent_themes = parent_themes;
    }

    public List<Theme> getChild_theme() {
        return child_theme;
    }

    public void setChild_theme(List<Theme> child_theme) {
        this.child_theme = child_theme;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", parent_id=" + parent_id +
                ", theme=" + theme +
                ", parent_themes=" + parent_themes +
                ", child_theme=" + child_theme +
                '}';
    }
}
