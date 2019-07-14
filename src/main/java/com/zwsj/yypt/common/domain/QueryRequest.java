package com.zwsj.yypt.common.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryRequest<T> implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    private int pageSize;
    private int pageNum;

    private String sortField;
    private String sortOrder;
    private T querylist;
}