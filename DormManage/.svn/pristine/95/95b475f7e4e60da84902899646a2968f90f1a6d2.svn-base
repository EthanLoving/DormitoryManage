package per.crystal.dormmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import per.crystal.dormmanage.controller.view.JsonView;
import per.crystal.dormmanage.controller.view.Page;
import per.crystal.dormmanage.entity.ArticleRecord;
import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.service.ArticleRecordService;
import per.crystal.dormmanage.util.Constants;

/**
 * 2018/02/22
 * @author Crystal
 *
 */
@Controller
@RequestMapping("record")
public class ArticleRecordController extends JsonView {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleRecordController.class);

	@Autowired
	private ArticleRecordService articleRecordService;
	
	
	/**
	 * 添加
	 * @param articleRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String save(@RequestBody ArticleRecord articleRecord) {
		try {
			User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			articleRecord.setCreater(user.getName());
			int count = articleRecordService.save(articleRecord);
			if (count > 0) {
				jsonView.successPack("", "添加成功");
			} else {
				jsonView.failPack("添加失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("ArticleRecordController save is error, {articleRecord:" + articleRecord + "}", 
					e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param articleRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listByPage", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listByPage(@RequestParam("page") Integer pageNumber, @RequestParam("rows") Integer pageSize,
			ArticleRecord articleRecord) {
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("articleName", articleRecord.getArticleName());
			page.setConditions(map);
			page.setPageNumber(pageNumber);
			page.setPageSize(pageSize);
			page = articleRecordService.listByPage(page);
			jsonView.successPack(page);
		} catch (ServiceException e) {
			log.error("ArticleRecordController listByPage is error,{pageNumber:" + pageNumber 
					+ ",pageSize:" + pageSize + "}");
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteById/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String deleteById(@PathVariable String id) {
		try {
			int count = articleRecordService.deleteById(id);
			if (count > 0 ) {
				jsonView.successPack("", "删除成功");
			} else {
				jsonView.failPack("删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("ArticleRecordController deleteById is error, {id:" + id + ",dormId:" + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "batchDelete", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String batchDelete(String ids) {
		List<String> idList = JSON.parseArray(ids, String.class);
		try {
			int count = articleRecordService.batchDelete(idList);
			if (count > 0) {
				jsonView.successPack("", "批量删除成功");
			} else {
				jsonView.failPack("批量删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("ArticleRecoreController batchDelete is error,{ids:" + ids + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 修改
	 * @param articleRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String update(@RequestBody ArticleRecord articleRecord) {
		try {
			int count = articleRecordService.update(articleRecord);
			if (count > 0) {
				jsonView.successPack("", "修改成功");
			} else {
				jsonView.failPack("修改失败");
			}
		} catch (Exception e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("ArticleRecordController update is error, {articleRecord:" + articleRecord + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
}
