package per.crystal.dormmanage.controller.view;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Crystal
 * 2018/02/10
 */
public class Page implements Serializable {

    /**
     * 系统允许的最大单页记录数
     */
    public static final int MAX_PAGE_SIZE = 500;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    /**
     * 当前页数
     */
    private Integer pageNumber = 1;

    /**
     * 总记录数
     */
    private Long total = 0L;

    /**
     * 查询条件
     */
    private Map<String, Object> conditions;

    private List rows;

    public Page() {
    }

    public Page(Integer pageSize, Long total) {
        this.pageSize = pageSize;
        this.total = total;
    }

    /**
     * 开始行
     * @return
     */
    public int getStartNum(){
        return (pageSize * (pageNumber - 1));
    }


    /**
     * 获取总页数
     * @return
     */
    public Long getTotalPage(){
        return total % pageSize == 0 ? (total / pageSize) : (total / pageSize) + 1;
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public static int getMaxPageSize() {
		return MAX_PAGE_SIZE;
	}

}
