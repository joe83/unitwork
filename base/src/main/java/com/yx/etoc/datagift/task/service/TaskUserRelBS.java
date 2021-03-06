

package com.yx.etoc.datagift.task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yx.baseframe.service.BaseBS;
import com.yx.baseframe.util.DateTools;
import com.yx.etoc.datagift.task.entity.DgTaskUserRel;


/** 
* @ClassName: TaskUserRelBS 
* @Description: TODO(用户任务关系) 
* @author yuxuan
* @date 2014-3-16 上午3:35:17 
*  
*/
@Service
@Transactional(readOnly=true)
public class TaskUserRelBS extends BaseBS<DgTaskUserRel>{
	/** 
	* @Title: initTask 
	* @Description: TODO(初始化每个用户对应的任务关系) 
	* @param @param userid    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@Transactional(readOnly=false)
	public void initTask(String userid){
		StringBuffer tmp = new StringBuffer("INSERT INTO dg_task_user_rel (TASK_ID,USER_ID,TASK_STATUS,TARGET_COUNT,COMPLETE_COUNT,UPDATE_DATE)");
		tmp.append("select t.task_id,?0,?1,t.TARGET_COUNT,'0',?2 from dg_task_info t");
		this.baseDAO.createNativeQueryWithIndexParam(tmp.toString(), userid,"0",DateTools.getCurrentDateString()).executeUpdate();
		
	}
	/** 
	* @Title: getTaskByUser 
	* @Description: TODO(根据用户和任务类型，得到唯一的用户任务) 
	* @param @param userid
	* @param @param type
	* @param @param status  1- 启用  0-停用
	* @param @return    设定文件 
	* @return DgTaskUserRel    返回类型 
	* @throws 
	*/
	public DgTaskUserRel getTaskByUser(String userid,String type,String status){
		String sql ="select obj from DgTaskUserRel obj,DgTaskInfo info where obj.id.taskId = info.taskId and obj.id.userId = ?0 and info.taskType= ?1 and info.taskStatus= ?2 ";
		DgTaskUserRel taskRel = (DgTaskUserRel) this.baseDAO.createQueryWithIndexParam(sql, userid,type,status).getSingleResult();
		return taskRel;
	}
	
	
	/** 
	* @Title: redoDayTask 
	* @Description: TODO(对任务状态的修改) 
	* @param @param taskType
	* @param @param flag    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@Transactional(propagation = Propagation.REQUIRED)
	public void redoDayTask(String taskType,String flag){
		String jql = "update DgTaskUserRel task set task.taskStatus = ?0 ,task.completeCount = 0 where task.id.taskId in ( select info.taskId from DgTaskInfo info where info.taskType = ?1 )";
		this.baseDAO.createQueryWithIndexParam(jql, flag,taskType).executeUpdate();
	}
	
	/** 
	 * @Title: checkDaySign 
	 * @Description: TODO(天天签到这种任务类型) 
	 * @param @param DgTaskUserRel
	 * @param @return 设定文件 
	 * @return boolean 返回类型 
	*/
	public boolean checkDayTask(DgTaskUserRel rel) {
		if (rel == null) {
			return false;
		}
		if ("0".equals(rel.getTaskStatus())) {
			return false;
		} else {
			return true;
		}
	}

}
