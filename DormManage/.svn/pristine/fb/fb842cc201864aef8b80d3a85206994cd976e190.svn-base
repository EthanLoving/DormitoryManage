package per.crystal.dormmanage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.crystal.dormmanage.entity.DormBuild;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.mapper.BaseMapper;
import per.crystal.dormmanage.mapper.DormBuildMapper;
import per.crystal.dormmanage.mapper.DormMapper;
import per.crystal.dormmanage.service.DormBuildService;

/**
 * 2018/02/11
 * @author Crystal
 *
 */
@Service
public class DormBuildServiceImpl extends BaseServiceImpl<DormBuild> implements DormBuildService {

	@Autowired
	private DormBuildMapper dormBuildMapper;
	
	@Autowired
	private DormMapper dormMapper;
	
	@Override
	public BaseMapper<DormBuild> getBaseMapper() {
		return dormBuildMapper;
	}

	@Override
	public int batchDeleteByBuildId(List<String> idList)
			throws ServiceException {
		int count;
		try {
			count = dormBuildMapper.logicBatchDelete(idList);
			dormMapper.batchDeleteByBuildId(idList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int deleteByBuildId(String id) throws ServiceException {
		int count;
		try {
			count = dormBuildMapper.logicDeleteById(id);
			dormMapper.deleteByBuildId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int getCountByBuildId(String id) throws ServiceException {
		int count;
		try {
			count = dormMapper.getCountByBuildId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int getCountByBuildIdList(List<String> idList)
			throws ServiceException {
		int count;
		try {
			count = dormMapper.getCountByBuildIdList(idList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int recoverByBuildId(String id) throws ServiceException {
		int count;
		try {
			count = dormBuildMapper.recoverById(id);
			dormMapper.recoverByBuildId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public int recoverByBuildIdList(List<String> idList)
			throws ServiceException {
		int count;
		try {
			count = dormBuildMapper.batchRecover(idList);
			dormMapper.recoverByBuildIdList(idList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}

}
