package top.hu.test.spring6_test.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponseData {

    private Integer code;

    private Object data;

    private String msg;

    private String token;


}
