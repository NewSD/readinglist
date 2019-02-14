package reading;

import org.springframework.context.annotation.Conditional;

/**
 * Created by ami on 2019/2/14.
 */
public class CreateBean {

    @Conditional(JdbcTemplateCondition.class)
    public MyService myService(){
        System.out.println("自定义.....");
        return new MyService();
    }

}
