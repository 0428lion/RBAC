package cn.lion.query;

import lombok.Data;

@Data
public class JSONResult {
    private boolean success=true;
    private String msg;

    public JSONResult mark(String msg){
        this.success=false;
        this.msg=msg;
        return this;
    }
}
