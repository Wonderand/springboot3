package top.hu.test.spring6_test.util;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtResult {

    private int errCode;

    private boolean success;

    private Claims claims;

    public boolean isSuccess(){
        return success;
    }

}
