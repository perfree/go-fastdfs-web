package com.perfree.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int state;
    private String msg;
    private Long total;
    private List<T> data;
}
