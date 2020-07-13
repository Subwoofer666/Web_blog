import org.springframework.stereotype.Component;

@Component
public class asd {
    private String data = "I am a singleton!!";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
