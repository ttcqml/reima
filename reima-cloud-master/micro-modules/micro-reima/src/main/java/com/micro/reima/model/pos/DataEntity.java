package com.micro.reima.model.pos;

import java.io.Serializable;

public class DataEntity<T> implements Serializable {
    private static final long serialVersionUID = -8265152962959583721L;

    private T list;

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
