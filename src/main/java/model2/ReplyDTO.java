package model2;

import java.util.Date;

public class ReplyDTO {
    private int ridx;
    private int bno;
    private String name;
    private String content;
    private Date postdate;
    private String pass;

    public int getRidx() {
        return ridx;
    }

    public void setRidx(int ridx) {
        this.ridx = ridx;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
