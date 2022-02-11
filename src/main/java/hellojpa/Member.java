package hellojpa;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // 꼭 넣어야 JPA가 인식 + 객체 테이블과 매핑을 위한
@SequenceGenerator(
        name = "MEMBER SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,allocationSize = 50 //1부터 시작 1증가
)
//@Table(name = "user") 테이블 이름이 다르면 이렇게 매핑 해서 처는 / user테이블로 from절을 보냄
public class Member {

    //pk 매
    @Id // import javax.persistence 로 넣어야 된댜.
    //generator를 다음과같이 바꿔주면 테이블마다 시퀀스를 따로 관리할수있다.
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MEMBER SEQ_GENERATOR") //identity sequece table 3개 존제 (auto 는 3중에 알아서 db방언에 맞게선택)
    //sequence 하이버네이트 자체 시퀸스 사용해서 순서출력
    private  Long id;

    // 객체에는 Username but db에는 name사용
    //nullable -> not null 유용
    //length ->길이조절 , columnDefinition  객체에서 디비에 정보설정
    @Column(name = "name" , nullable = false) //네임을 따로 하고 싶으면 네임으로 따로 매핑
    private  String username;

    //숫자가 클때는 precision or scale 작은 숫자 쓸떄 정확도 상승
    private Integer age;

    //db에는 Enum x -> Enumerate 이용
    //기본이 oridinal : enum의 순서를 저장->type int,저장 0 (user) / string enum의 이름 저장
    //따라서 대부분 string으로 사용
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //타입 3가지 -> date(날짜) time(시간) timestamp(둘다)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    //요즘에는 이렇게 날짜씀
    private LocalDate localDate;
    private LocalDateTime localDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //db에 varchar를 넘어서는 큰값을 넣고 싶을때 다음과 같이 lob 활용

    @Lob // 문자인 경우엔 clob으로 생성
    //나머지는 blob매핑
    private  String description;

    //db에서는 안쓰고 객체에서만 쓰고 싶어
    //필드 매핑 x ,저장 ,조회x
    @Transient
    private int temp;

    //JPA는 동적으로 객체 생성해야되기때문에 기본생성자 하나 만들어줘야함

    public  Member(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
