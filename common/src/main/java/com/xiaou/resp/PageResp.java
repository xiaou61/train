package com.xiaou.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResp<T> implements Serializable {
    private Long total;
    private List<T> list;
}
