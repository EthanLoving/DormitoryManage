package per.crystal.dormmanage.entity;

import java.io.Serializable;

/**
 * @author Crystal
 * 2018/02/10
 * 宿舍楼表实体
 */
public class DormBuild extends BaseEntity implements Serializable {

	private String buildNo;
    private String buildName;
    private Integer buildType;
    private String buildDescription;
    private Integer buildIsDelete;
    

	public String getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(String buildNo) {
        this.buildNo = buildNo;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public Integer getBuildType() {
        return buildType;
    }

    public void setBuildType(Integer buildType) {
        this.buildType = buildType;
    }

    public String getBuildDescription() {
        return buildDescription;
    }

    public void setBuildDescription(String buildDescription) {
        this.buildDescription = buildDescription;
    }

	public Integer getBuildIsDelete() {
		return buildIsDelete;
	}

	public void setBuildIsDelete(Integer buildIsDelete) {
		this.buildIsDelete = buildIsDelete;
	}

}
