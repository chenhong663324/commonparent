package edu.hstc.roast.service;

import edu.hstc.roast.dao.ThemeMapper;
import edu.hstc.roast.module.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ThemeServiceImpl implements ThemeService {
    @Autowired
    ThemeMapper themeMapper;

    @Override
    public int add(Theme theme) {
        return themeMapper.add(theme);
    }

    @Override
    public List<Theme> queryAll() {
        return themeMapper.queryAll();
    }

    @Override
    public Theme queryThemeByID(int id) {
        return themeMapper.queryThemeByID(id);
    }

    @Override
    public Theme queryThemeByIDWithList(int id) {
        return themeMapper.queryThemeByIDWithList(id);
    }

    @Override
    public Theme queryThemeByName(String name) {
        return themeMapper.queryThemeByName(name);
    }

    @Override
    public List<Theme> queryThemeAndParentByID(int id) {
        return themeMapper.queryThemeAndParentByID(id);
    }

    @Override
    public List<Theme> queryThemeParentByID(int id) {
        return themeMapper.queryThemeParentByID(id);
    }

    @Override
    public int updateThemeByID(Theme theme) {
        return themeMapper.updateThemeByID(theme);
    }

    @Override
    public int deleteThemeByID(int id) {
        return themeMapper.deleteThemeByID(id);
    }
}
