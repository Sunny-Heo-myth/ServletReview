package connPoolReview;

import java.sql.Date;

public class MemberVO {

    private String name;
    private String owner;
    private String job;
    private String sex;
    private Date birth;
    private Date death;

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getJob() {
        return job;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirth() {
        return birth;
    }

    public Date getDeath() {
        return death;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setDeath(Date death) {
        this.death = death;
    }
}
