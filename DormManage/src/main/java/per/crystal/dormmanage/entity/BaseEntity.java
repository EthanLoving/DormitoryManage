package per.crystal.dormmanage.entity;

import java.util.Date;

/**
 * 2018/01/10
 * @author Crystal
 */
public class BaseEntity {

    private String id;
    private Date createDate;
    private String creater;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
