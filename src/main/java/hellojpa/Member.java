package hellojpa;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // 꼭 넣어야 JPA가 인식
public class Member {

    @Id // import javax.persistence 로 넣어야 된댜.
    private  Long id;
    private  String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
