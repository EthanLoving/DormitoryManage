package per.crystal.dormmanage.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Crystal
 * 2018/02/10
 * 来访记录表实体
 */
public class DormVistor extends BaseEntity implements Serializable{

    private String vistorName;
    private String vistorPhone;
    private String vistorIdcard;
    private Timestamp startTime;
    private Timestamp endTime;
    private String vistedName;

    public String getVistorName() {
        return vistorName;
    }

    public void setVistorName(String vistorName) {
        this.vistorName = vistorName;
    }

    public String getVistorPhone() {
        return vistorPhone;
    }

    public void setVistorPhone(String vistorPhone) {
        this.vistorPhone = vistorPhone;
    }

    public String getVistorIdcard() {
        return vistorIdcard;
    }

    public void setVistorIdcard(String vistorIdcard) {
        this.vistorIdcard = vistorIdcard;
    }

    public String getVistedName() {
        return vistedName;
    }

    public void setVistedName(String vistedName) {
        this.vistedName = vistedName;
    }

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

}
