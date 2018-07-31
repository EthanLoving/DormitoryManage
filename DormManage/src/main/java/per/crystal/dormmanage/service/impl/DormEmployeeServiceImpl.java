package per.crystal.dormmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.crystal.dormmanage.entity.DormEmployee;
import per.crystal.dormmanage.mapper.BaseMapper;
import per.crystal.dormmanage.mapper.DormEmployeeMapper;
import per.crystal.dormmanage.service.DormEmployeeService;

/**
 * 2018/02/15
 * @author Crystal
 *
 */
@Service
public class DormEmployeeServiceImpl extends BaseServiceImpl<DormEmployee> implements DormEmployeeService {

	@Autowired
	private DormEmployeeMapper dormEmployeeMapper;
	
	@Override
	public BaseMapper<DormEmployee> getBaseMapper() {
		return dormEmployeeMapper;
	}

}
