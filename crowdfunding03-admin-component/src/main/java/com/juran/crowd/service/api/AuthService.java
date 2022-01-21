package com.juran.crowd.service.api;

import com.juran.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * 作者： Juran on 2022/1/21 11:16
 * 作者博客：iit.la
 */
public interface AuthService {
    List<Auth> getAllAuth();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAuthRelathinship(Map<String, List<Integer>> map);
}
