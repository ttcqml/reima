package com.micro.reima.model.pos;

import java.io.Serializable;

public class ResultEntity implements Serializable {
    private static final long serialVersionUID = 6996999462982112804L;
    private Integer status;

    private String message;

    private DataEntity data;

    private SearchEntity search;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public SearchEntity getSearch() {
        return search;
    }

    public void setSearch(SearchEntity search) {
        this.search = search;
    }
}
