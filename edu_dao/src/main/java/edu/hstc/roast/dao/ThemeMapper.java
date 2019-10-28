package edu.hstc.roast.dao;

import edu.hstc.roast.module.Theme;

import java.util.List;

public interface ThemeMapper {
    int add(Theme theme);
    List<Theme> queryAll();
    Theme queryThemeByID(int id);
    Theme queryThemeByIDWithList(int id);
    Theme queryThemeByName(String name);
    List<Theme> queryThemeAndParentByID(int id);
    List<Theme> queryThemeParentByID(int id);
    int updateThemeByID(Theme theme);
    int deleteThemeByID(int id);

}
