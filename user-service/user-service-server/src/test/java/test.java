import com.wly.user.UserApplication;
import com.wly.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserApplication.class)
public class test {


    @Test
    public void test1() throws Exception {
        String jwt = JwtUtil.createJWT("123456");
        Claims claims = JwtUtil.parseJWT(jwt);
        String id = claims.getSubject();
        System.out.println(id);
    }
}
