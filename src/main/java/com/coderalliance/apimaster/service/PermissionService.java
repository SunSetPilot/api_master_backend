package com.coderalliance.apimaster.service;

import java.util.List;

public interface PermissionService {
    void checkProjectPermission(Long projectId, Long userId);

    List<Long> getProjectMemberIds(Long projectId);
}
