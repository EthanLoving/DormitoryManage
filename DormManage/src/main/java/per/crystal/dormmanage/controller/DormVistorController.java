package per.crystal.dormmanage.controller;

import java.sql.Timestamp;
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
import per.crystal.dormmanage.entity.DormVistor;
import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.service.DormVistorService;
import per.crystal.dormmanage.util.Constants;

/**
 * 2018/02/22
 * @author Crystal
 *
 */
@Controller
@RequestMapping("vistor")
public class DormVistorController extends JsonView {
	
	private static final Logger log = LoggerFactory.getLogger(DormVistorController.class);

	@Autowired
	private DormVistorService dormVistorService;
	
	
	/**
	 * 添加
	 * @param articleRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String save(@RequestBody DormVistor dormVistor) {
		Timestamp startTime = new Timestamp(System.currentTimeMillis());
		try {
			User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			dormVistor.setCreater(user.getName());
			dormVistor.setStartTime(startTime);
			int count = dormVistorService.save(dormVistor);
			if (count > 0) {
				jsonView.successPack("", "添加成功");
			} else {
				jsonView.failPack("添加失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormVistorController save is error, {dormVistor:" + dormVistor + "}", 
					e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param dormVistor
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listByPage", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listByPage(@RequestParam("page") Integer pageNumber, @RequestParam("rows") Integer pageSize,
			DormVistor dormVistor) {
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("vistorName", dormVistor.getVistorName());
			map.put("vistedName", dormVistor.getVistedName());
			page.setConditions(map);
			page.setPageNumber(pageNumber);
			page.setPageSize(pageSize);
			page = dormVistorService.listByPage(page);
			jsonView.successPack(page);
		} catch (ServiceException e) {
			log.error("DormVistorController listByPage is error,{pageNumber:" + pageNumber 
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
			int count = dormVistorService.deleteById(id);
			if (count > 0 ) {
				jsonView.successPack("", "删除成功");
			} else {
				jsonView.failPack("删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormVistorController deleteById is error, {id:" + id + ",dormId:" + "}", e.getMessage(), e);
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
			int count = dormVistorService.batchDelete(idList);
			if (count > 0) {
				jsonView.successPack("", "批量删除成功");
			} else {
				jsonView.failPack("批量删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormVistorController batchDelete is error,{ids:" + ids + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 结束访问
	 * @param dormVistor
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.PUT,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String update(@RequestBody DormVistor dormVistor) {
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		try {
			dormVistor.setEndTime(endTime);
			int count = dormVistorService.update(dormVistor);
			if (count > 0) {
				jsonView.successPack("", "访问已结束");
			} else {
				jsonView.failPack("结束失败");
			}
		} catch (Exception e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormVistorController update is error, {dormVistor:" + dormVistor + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
}
