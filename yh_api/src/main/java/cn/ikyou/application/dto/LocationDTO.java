package cn.ikyou.application.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class LocationDTO {
    private String code;
    private String message;
    private List<Map<String,String>> body;
}
