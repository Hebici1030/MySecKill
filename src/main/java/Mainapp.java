import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.seckill.service.SeckillService;
import org.seckill.vo.Exposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@MapperScan
@ComponentScan
public class Mainapp {
    @Autowired
    private SeckillService service;
    @Test
    public void test(){
        Exposer exposer = service.exportSeckillUrl(1000L);
        System.out.println(exposer);
    }
    public static void main(String[] args) {

    }
}
