package pojo.form;

public class Weight {


    private String myid;
    private String num;
    private String pic;
    private Integer isok;

    public void setIsok(Integer isok) {
        this.isok = isok;
    }

    public Integer getIsok() {
        return isok;
    }

    public void setMyid(String myid) {
        this.myid = myid;
    }

    public String getMyid() {
        return myid;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
