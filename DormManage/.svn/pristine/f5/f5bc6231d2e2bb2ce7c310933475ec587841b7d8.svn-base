package per.crystal.dormmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.crystal.dormmanage.entity.ArticleRecord;
import per.crystal.dormmanage.mapper.ArticleRecordMapper;
import per.crystal.dormmanage.mapper.BaseMapper;
import per.crystal.dormmanage.service.ArticleRecordService;

/**
 * 2018/02/15
 * @author Crystal
 *
 */
@Service
public class ArticleRecordServiceImpl extends BaseServiceImpl<ArticleRecord> implements ArticleRecordService {

	@Autowired
	private ArticleRecordMapper articleRecordMapper;
	
	@Override
	public BaseMapper<ArticleRecord> getBaseMapper() {
		return articleRecordMapper;
	}

}
