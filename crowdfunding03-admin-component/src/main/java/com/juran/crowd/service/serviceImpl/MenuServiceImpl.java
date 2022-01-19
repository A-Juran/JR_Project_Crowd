package com.juran.crowd.service.serviceImpl;

import com.juran.crowd.entity.Menu;
import com.juran.crowd.entity.MenuExample;
import com.juran.crowd.entity.RoleExample;
import com.juran.crowd.mapper.MenuMapper;
import com.juran.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者： Juran on 2022/1/13 11:51
 * 作者博客：iit.la
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void save(Menu menu) {
        menuMapper.insert(menu);

    }

    @Override
    public void edit(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void remove(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
