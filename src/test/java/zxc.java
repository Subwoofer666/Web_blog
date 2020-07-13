import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class zxc {
    private asd bean;

    @PostConstruct
    public void init() {
        System.out.println(bean.getData());
    }
    @Autowired
    public void setBean(asd bean) {
        this.bean = bean;
    }
}
