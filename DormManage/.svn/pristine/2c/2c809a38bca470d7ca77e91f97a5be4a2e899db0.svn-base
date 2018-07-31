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
import per.crystal.dormmanage.entity.DormBuild;
import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.service.DormBuildService;
import per.crystal.dormmanage.service.DormService;
import per.crystal.dormmanage.util.Constants;

/**
 * 2018/02/10
 * @author Crystal
 *
 */
@Controller
@RequestMapping("dormBuild")
public class DormBuildController extends JsonView {

	private static final Logger log = LoggerFactory.getLogger(DormBuildController.class);
	
	@Autowired
	private DormBuildService dormBuildService;
	
	@Autowired DormService dormService;
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param dormBuild
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listByPage", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listByPage(@RequestParam("page") Integer pageNumber, @RequestParam("rows") Integer pageSize,
			DormBuild dormBuild) {
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("buildNo", dormBuild.getBuildNo());
			map.put("buildType", dormBuild.getBuildType());
			page.setConditions(map);
			page.setPageNumber(pageNumber);
			page.setPageSize(pageSize);
			page = dormBuildService.listByPage(page);
			jsonView.successPack(page);
		} catch (ServiceException e) {
			log.error("DormBuildController listByPage is error,{pageNumber:" + pageNumber 
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
			List<DormBuild> buildList = dormBuildService.listAll();
			jsonView.successPack(buildList);
		} catch (ServiceException e) {
			log.error("DormBuildController listAll is error", e.getMessage(), e);
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 停用楼层
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logicDeleteById/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String deleteById(@PathVariable String id) {
		try {
			// 查看楼层中是否还有学生
			int num = dormBuildService.getCountByBuildId(id);
			if (num > 0) {
				jsonView.failPack("楼层中尚有学生,不能停用");
				return JSON.toJSONString(jsonView);
			}
			
			int count = dormBuildService.deleteByBuildId(id);
			if (count > 0) {
				jsonView.successPack("", "已停用");
			} else {
				jsonView.failPack("停用失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormBuildController logicDeleteById is error,{id:" + id + "}");
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 启用楼层
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "recoverById/{id}", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String recoverById(@PathVariable String id) {
		try {
			int count = dormBuildService.recoverByBuildId(id);
			if (count > 0) {
				jsonView.successPack("", "已启用");
			} else {
				jsonView.failPack("启用失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormBuildController recoverById is error, {id:" + id + "}", e.getMessage(), e);
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
			// 查看楼层中是否还有学生
			int num = dormBuildService.getCountByBuildIdList(idList);
			if (num > 0) {
				jsonView.failPack("批量停用的楼层中尚有学生,不能停用");
				return JSON.toJSONString(jsonView);
			}
			
			int count = dormBuildService.batchDeleteByBuildId(idList);
			if (count > 0) {
				jsonView.successPack("", "批量停用成功");
			} else {
				jsonView.failPack("批量停用失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormBuildController logicBatchDelete is error, {ids:" + ids + "}", e.getMessage(), e);
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
			int count = dormBuildService.recoverByBuildIdList(idList);
			if (count > 0) {
				jsonView.successPack("", "批量启用成功");
			} else {
				jsonView.failPack("批量启用失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormBuildController batchRecover is error, {ids:" + ids + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 添加楼层
	 * @param dormBuild
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveBuild", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String saveBuild(@RequestBody DormBuild dormBuild) {
		try {
			User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			dormBuild.setCreater(user.getName());
			int exists = dormBuildService.findExists(dormBuild.getBuildNo());
			if (exists > 0) {
				jsonView.failPack("楼层号已存在");
				return JSON.toJSONString(jsonView);
			}
			int count = dormBuildService.save(dormBuild);
			if (count > 0) {
				jsonView.successPack("", "添加成功");
			} else {
				jsonView.failPack("添加失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormBuildController saveBuild is error, {dormBuild:" + dormBuild + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String update(@RequestBody DormBuild dormBuild) {
		try {
			int count = dormBuildService.update(dormBuild);
			if (count > 0) {
				jsonView.successPack("", "修改成功");
			} else {
				jsonView.failPack("修改失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormBuildController update is error, {dormBuild:" + dormBuild + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}

}
