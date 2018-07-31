package per.crystal.dormmanage.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Crystal
 * 2018/02/10
 */
public interface BaseMapper<Entity> {

    /**
     * 公共的新增方法
     * @param entity
     * @return
     */
    int save(Entity entity);

    /**
     * 公共的根据id删除方法
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 公共的批量删除方法
     * @param idList
     * @return
     */
    int batchDelete(List<String> idList);

    /**
     * 公共的逻辑删除方法
     * @param id
     * @return
     */
    int logicDeleteById(String id);

    /**
     * 公共的批量逻辑删除方法
     * @param idList
     * @return
     */
    int logicBatchDelete(List<String> idList);
    
    /**
     * 根据id启用
     * @param id
     * @return
     */
    int recoverById(String id);
    
    /**
     * 批量启用
     * @param idList
     * @return
     */
    int batchRecover(List<String> idList);

    /**
     * 公共的修改方法
     * @param entity
     * @return
     */
    int update(Entity entity);

    /**
     * 公共的根据id查询方法
     * @param id
     * @return
     */
    Entity getById(String id);

    /**
     * 公共的查询是否存在方法
     * @param param
     * @return
     */
    int findExists(String param);

    /**
     * 公共的查询所有方法
     * @return
     */
    List<Entity> listAll();

    /**
     * 公共的分页方法
     * @param map
     * @return
     */
    List<Entity> listByPage(Map<String, Object> map);

    /**
     * 公共的查询记录数方法
     * @param map
     * @return
     */
    Long getCount(Map<String, Object> map);

}
