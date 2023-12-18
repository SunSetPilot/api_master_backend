package com.coderalliance.apimaster.service;

import java.util.List;

public interface PermissionService {
    void checkProjectPermission(Long projectId, Long userId);

    List<Long> getProjectMemberIds(Long projectId);

    List<Long> getUserProjectIds(Long userId);

    void updateProjectMembers(Long projectId, List<Long> members);

    void deleteProjectPermission(Long projectId);
}
