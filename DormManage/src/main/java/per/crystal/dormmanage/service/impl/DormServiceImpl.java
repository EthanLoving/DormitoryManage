package per.crystal.dormmanage.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.crystal.dormmanage.entity.Dorm;
import per.crystal.dormmanage.mapper.BaseMapper;
import per.crystal.dormmanage.mapper.DormMapper;
import per.crystal.dormmanage.service.DormService;

/**
 * 2018/02/15
 * @author Crystal
 *
 */
@Service
public class DormServiceImpl extends BaseServiceImpl<Dorm> implements DormService {

	@Autowired
	private DormMapper dormMapper;
	
	@Override
	public BaseMapper<Dorm> getBaseMapper() {
		return dormMapper;
	}

}
