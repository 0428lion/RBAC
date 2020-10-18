package cn.lion.query;

import lombok.Data;

@Data
public class QueryObject {
    private int currentPage=1;//当前页
    private int pageSize=3;// 每页显示几条数据

}
