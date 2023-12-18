package com.coderalliance.apimaster.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName(value = "project")
public class Project {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField("owner_id")
    private Long ownerId;
    @TableField("auto_import")
    private Boolean autoImport;
    @TableField("git_address")
    private String gitAddress;
    @TableField("git_branch")
    private String gitBranch;
}
