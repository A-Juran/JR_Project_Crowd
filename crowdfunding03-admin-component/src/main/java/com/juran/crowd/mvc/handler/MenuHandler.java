package com.juran.crowd.mvc.handler;

import com.juran.crowd.entity.Menu;
import com.juran.crowd.service.api.MenuService;
import com.juran.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 作者： Juran on 2022/1/13 11:50
 * 作者博客：iit.la
 */
@Controller
public class MenuHandler {
    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("menu/remove.json")
    public ResultEntity<String> remove(@RequestParam("id") Integer id){
        menuService
                .remove(id);
        return
                ResultEntity.successWithoutData();
    }

    /**
     * 更新菜单
     * @param menu  菜单对象
     * @return  返回封装实体对象
     */
    @ResponseBody
    @RequestMapping("menu/edit.json")
    public ResultEntity<String> edit(Menu menu){
        menuService
                .edit(menu);
        return ResultEntity.successWithoutData();
    }

    /**
     * 菜单维护添加数据
     * @param menu  菜单实体
     * @return  返回封装实体对象。
     */
    @ResponseBody
    @RequestMapping("menu/save.json")
    public ResultEntity<String> save(Menu menu) {
        menuService.save(menu);
        return ResultEntity.successWithoutData();
    }


    /**
     * 查询菜单
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTreeNew() {
        //1.查询到所有的Menu对象。
        List<Menu> menuList = menuService.getAll();
        //2.声明一个变量找出需要存储的根节点。
        Menu root = null;
        //3.建立一个map对象存根节点
        HashMap<Integer, Menu> fatherMap = new HashMap<>();
        //4.将父节点存入map当中
        for (Menu father : menuList) {
                //将menu对象存入map中。
                fatherMap.put(father.getId(), father);
        }
        //5.将子节点存入map与之对应的Menu对象之中
        for (Menu father : menuList) {
            if (father.getPid() == null) {
                root = father;
                continue;
            }
            //将menu对象存入map中。
            Menu menu = fatherMap.get(father.getPid());
            System.out.println("=====51====" + father);
            menu.getChildren().add(father);
            System.out.println("=====53====" + menu.getChildren().toString());
        }
        return ResultEntity.successWithData(root);
    }


//    public ResultEntity<Menu> getWholeTreeOld() {
//        //1.查询到所有的Menu对象。
//        List<Menu> menuList = menuService.getAll();
//        //2.声明一个变量找出需要存储的根节点。
//        Menu root = null;
//        //3.遍历进行存储
//        for (Menu menu : menuList) {
//            //存储根节点
//            //4.获取当前menu兑现的pid
//            Integer pid = menu.getPid();
//            //5.检查pid是否为null
//            if (pid == null) {
//                //6.把正在遍历的这个menu对象赋值给root
//                root = menu;
//                //7.停止本次循环，继续执行下一次循环。
//                continue;
//            }
//            //6.将子节点存入父节点
//            //如果不为null时说明已经有了父节点，我们就需要将子节点存入父节点当中
//            for (Menu children : menuList) {
//                //7.将子节点pid 与 父 id 进行对比。对比成功则将其存入 父节点
//                if (Objects.equals(pid, children.getId())) {
//                    root.getChildren().add(children);
//                    break;
//                }
//            }
//        }
//        return ResultEntity.successWithData(root);
//    }
}
