package cn.lion.query;

import lombok.Data;

@Data
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private Long deptId = -1L;


}
