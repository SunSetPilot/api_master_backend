package com.coderalliance.apimaster.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderalliance.apimaster.dao.PermissionMapper;
import com.coderalliance.apimaster.exception.PermissionException;
import com.coderalliance.apimaster.model.entity.UserProjectPermission;
import com.coderalliance.apimaster.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void checkProjectPermission(Long projectId, Long userId) {
        if (projectId == null || userId == null) {
            throw new PermissionException("You have no permission to access this project!");
        }
        QueryWrapper<UserProjectPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserProjectPermission::getProjectId, projectId)
                .eq(UserProjectPermission::getUserId, userId);
        UserProjectPermission userProjectPermission = permissionMapper.selectOne(queryWrapper);
        if (userProjectPermission == null) {
            throw new PermissionException("You have no permission to access this project!");
        }
    }

    @Override
    public List<Long> getProjectMemberIds(Long projectId) {
        QueryWrapper<UserProjectPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserProjectPermission::getProjectId, projectId);
        List<UserProjectPermission> userProjectPermissions = permissionMapper.selectList(queryWrapper);
        return userProjectPermissions.stream().map(UserProjectPermission::getUserId).collect(Collectors.toList());
    }

    @Override
    public List<Long> getUserProjectIds(Long userId) {
        QueryWrapper<UserProjectPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserProjectPermission::getUserId, userId);
        List<UserProjectPermission> userProjectPermissions = permissionMapper.selectList(queryWrapper);
        return userProjectPermissions.stream().map(UserProjectPermission::getProjectId).collect(Collectors.toList());
    }

    @Override
    public void updateProjectMembers(Long projectId, List<Long> members) {
        QueryWrapper<UserProjectPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserProjectPermission::getProjectId, projectId);
        permissionMapper.delete(queryWrapper);
        members.forEach(member -> {
            UserProjectPermission userProjectPermission = UserProjectPermission.builder()
                    .projectId(projectId)
                    .userId(member)
                    .build();
            permissionMapper.insert(userProjectPermission);
        });
    }

    @Override
    public void deleteProjectPermission(Long projectId) {
        QueryWrapper<UserProjectPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserProjectPermission::getProjectId, projectId);
        permissionMapper.delete(queryWrapper);
    }
}
