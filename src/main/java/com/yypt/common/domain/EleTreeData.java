package com.yypt.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-04-08
 * @描述
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EleTreeData {
    private  Long id;
    private String label;
    private List<EleTreeData> children;
    @JsonIgnore
    private Long parentId;
    public void initChildren(){
        this.children = new ArrayList<>();
    }
}
