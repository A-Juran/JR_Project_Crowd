package com.juran.crowd.service.api;

import com.juran.crowd.entity.Menu;

import java.util.List;

/**
 * 作者： Juran on 2022/1/13 11:51
 * 作者博客：iit.la
 */
public interface MenuService {

    List<Menu> getAll();

    void save(Menu menu);

    void edit(Menu menu);

    void remove(Integer id);
}
