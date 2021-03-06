package com.juran.crowd.mapper;


import java.util.List;

import com.juran.crowd.entity.Auth;
import com.juran.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void deleteOldRelathionship(Integer roleId);

    void insertNewRelathinship(@Param("roleId") Integer roleId,@Param("authIdList")  List<Integer> authIdList);

    List<String> selectAssignedAuthNameByAdminId(Integer adminId);
}