package per.crystal.dormmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.crystal.dormmanage.entity.DormVistor;
import per.crystal.dormmanage.mapper.BaseMapper;
import per.crystal.dormmanage.mapper.DormVistorMapper;
import per.crystal.dormmanage.service.DormVistorService;

/**
 * 2018/02/15
 * @author Crystal
 *
 */
@Service
public class DormVistorServiceImpl extends BaseServiceImpl<DormVistor> implements DormVistorService {

	@Autowired
	private DormVistorMapper dormVistorMapper;
	
	@Override
	public BaseMapper<DormVistor> getBaseMapper() {
		return dormVistorMapper;
	}

}
