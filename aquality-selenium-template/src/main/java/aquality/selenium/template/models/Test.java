package aquality.selenium.template.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime = "";
    private String status;
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private String projectName;
    @JsonIgnore
    private String sid;
    @JsonIgnore
    private String env;
    @JsonIgnore
    private String browser;

    public Test(String name, String method, String env, String browser, String sid) {
        this.name = name;
        this.method = method;
        this.env = env;
        this.browser = browser;
        this.sid = sid;
    }

    public Test(String name, String method, String status, String startTime, String endTime, String duration) {
        this.name = name;
        this.method = method;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return duration.equals(test.duration) && method.equals(test.method) && name.equals(test.name) && startTime.equals(test.startTime) && endTime.equals(test.endTime) && status.equalsIgnoreCase(test.status);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime * Objects.hash(duration, method, name, startTime, endTime, status);
    }
}
