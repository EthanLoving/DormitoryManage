package per.crystal.dormmanage.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Crystal
 * 2018/01/10
 * 物品进出记录表实体
 */
public class ArticleRecord extends BaseEntity implements Serializable {

    private String articleName;
    private String buildId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date enterTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date outTime;
    private DormBuild dormBuild;

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

	public DormBuild getDormBuild() {
		return dormBuild;
	}

	public void setDormBuild(DormBuild dormBuild) {
		this.dormBuild = dormBuild;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
}
