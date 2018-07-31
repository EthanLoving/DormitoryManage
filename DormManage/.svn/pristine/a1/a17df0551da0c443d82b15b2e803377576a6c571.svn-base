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
import per.crystal.dormmanage.entity.Dorm;
import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.service.DormService;
import per.crystal.dormmanage.util.Constants;

/**
 * 2018/02/17
 * @author Crystal
 *
 */
@Controller
@RequestMapping("dorm")
public class DormController extends JsonView {

	private static final Logger log = LoggerFactory.getLogger(DormController.class);
	
	@Autowired
	private DormService dormService;
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param dorm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listByPage", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listByPage(@RequestParam("page") Integer pageNumber, @RequestParam("rows") Integer pageSize,
			Dorm dorm) {
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("dormNo", dorm.getDormNo());
			map.put("dormCapacity", dorm.getDormCapacity());
			map.put("buildName", dorm.getBuildName());
			page.setConditions(map);
			page.setPageNumber(pageNumber);
			page.setPageSize(pageSize);
			page = dormService.listByPage(page);
			jsonView.successPack(page);
		} catch (ServiceException e) {
			log.error("DormController listByPage is error,{pageNumber:" + pageNumber 
					+ ",pageSize:" + pageSize + "}");
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd");
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listAll", method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listAll() {
		try {
			List<Dorm> buildList = dormService.listAll();
			jsonView.successPack(buildList);
		} catch (ServiceException e) {
			log.error("DormController listAll is error", e.getMessage(), e);
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 停用宿舍
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logicDeleteById/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String logicDeleteById(@PathVariable String id) {
		try {
			int count = dormService.logicDeleteById(id);
			if (count > 0) {
				jsonView.successPack("", "已停用");
			} else {
				jsonView.failPack("停用失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormController logicDeleteById is error,{id:" + id + "}");
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 启用宿舍
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "recoverById/{id}", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String recoverById(@PathVariable String id) {
		try {
			int count = dormService.recoverById(id);
			if (count > 0) {
				jsonView.successPack("", "已启用");
			} else {
				jsonView.failPack("启用失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormController recoverById is error, {id:" + id + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 批量停用
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logicBatchDelete", method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String logicBatchDelete(String ids) {
		List<String> idList = JSON.parseArray(ids, String.class) ;
		try {
			int count = dormService.logicBatchDelete(idList);
			if (count > 0) {
				jsonView.successPack("", "批量停用成功");
			} else {
				jsonView.failPack("批量停用失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormController logicBatchDelete is error, {ids:" + ids + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 批量启用
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "batchRecover", method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String batchRecover(String ids) {
		List<String> idList = JSON.parseArray(ids, String.class) ;
		try {
			int count = dormService.batchRecover(idList);
			if (count > 0) {
				jsonView.successPack("", "批量启用成功");
			} else {
				jsonView.failPack("批量启用失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormController batchRecover is error, {ids:" + ids + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 添加宿舍
	 * @param dorm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String save(@RequestBody Dorm dorm) {
		try {
			User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			dorm.setCreater(user.getName());
			int exists = dormService.findExists(dorm.getDormNo());
			if (exists > 0) {
				jsonView.failPack("宿舍号已存在");
				return JSON.toJSONString(jsonView);
			}
			int count = dormService.save(dorm);
			if (count > 0) {
				jsonView.successPack("", "添加成功");
			} else {
				jsonView.failPack("添加失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormController save is error, {dorm:" + dorm + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 修改宿舍
	 * @param dorm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String update(@RequestBody Dorm dorm) {
		try {
			int count = dormService.update(dorm);
			if (count > 0) {
				jsonView.successPack("", "修改成功");
			} else {
				jsonView.failPack("修改失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormController update is error, {dorm:" + dorm + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}

}
