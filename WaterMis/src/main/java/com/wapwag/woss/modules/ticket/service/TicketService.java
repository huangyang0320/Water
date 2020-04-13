package com.wapwag.woss.modules.ticket.service;

import com.alibaba.fastjson.JSONObject;
import com.wapwag.woss.common.persistence.Page;
import com.wapwag.woss.common.service.CrudService;
import com.wapwag.woss.common.service.ServiceException;
import com.wapwag.woss.modules.sys.entity.User;
import com.wapwag.woss.modules.ticket.Entity.TicketComDto;
import com.wapwag.woss.modules.ticket.Entity.TicketDto;
import com.wapwag.woss.modules.ticket.Entity.TicketLogDto;
import com.wapwag.woss.modules.ticket.Entity.TicketToDoDto;
import com.wapwag.woss.modules.ticket.dao.TicketDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class TicketService  extends CrudService<TicketDao, TicketDto> {
    @Autowired
    private TicketDao ticketDao;

    /**
     * 获取所有部门组织
     * @return
     */
    public List<TicketDto> getDeptList(){
        return ticketDao.getDeptList();
    }

    public List<String> getUserIdByDeptId(String deptId){
        return ticketDao.getUserIdByDeptId(deptId);
    }

    /**
     * 创建工单
     * @param ticketDto
     * @return
     */
    @Transactional(readOnly = false)
    public JSONObject createWorkOrder(TicketDto ticketDto) throws Exception {
        JSONObject result=new JSONObject();
        String ticketId= UUID.randomUUID().toString();
        ticketDto.setTicketId(ticketId);
        ticketDto.setValidFlag("1");
        this.insertTicket(ticketDto);
        //日志
        TicketLogDto log=new TicketLogDto();
        log.setId(UUID.randomUUID().toString());
        log.setTicketId(ticketId);
        log.setStatus(ticketDto.getStatus());
        log.setCreateBy(ticketDto.getCreateBy());
        log.setCreateDate(new Date());
        log.setUpdateBy(ticketDto.getUpdateBy());
        log.setUpdateDate(new Date());
        this.insertTicketLog(log);
        //关联告警表（特殊处理）
        if("告警工单".equals(ticketDto.getTicketType())){
            this.updateAlarmTicketByDeviceIdAndStartTime(ticketDto);
        }

        //需求变更，发给部门负责人
        //根据部门ID获取负责人
        List<String> userList= this.getUserIdByDeptId(ticketDto.getDeptId());
        //代办（部门下的人多能看到，状态为在签收）
        //1.先获取部门下有效所有人
           // List<User> userList = this.getUserListByDeptId(ticketDto.getDeptId());
        //2.把人员插入代办表
           if(userList!=null && userList.size()>0){
                TicketToDoDto ticketToDoDto=null;
                for(String userId:userList){
                    //status :0待分发  1签收
                    ticketToDoDto =  new TicketToDoDto(UUID.randomUUID().toString(),ticketId,"","0",userId,"1",new Date(),new Date(),ticketDto.getUpdateBy(),ticketDto.getCreateBy());
                    this.insertTicketToDo(ticketToDoDto);
                    result.put("code","201");
                    result.put("status","success");
                    result.put("message","工单创建成功!");
                }
            }else{
                result.put("code","3001");
                result.put("message","该部门下没有配置负责人员，请联系管理员!");
                throw new ServiceException(result.toJSONString());
            }

        return result;
    }

    /**
     * 处理工单
     * @param ticketDto
     * @return
     */
    @Transactional(readOnly = false)
    public JSONObject handleWorkOrder(TicketDto ticketDto){
        JSONObject result=new JSONObject();
        try{
            //日志
            TicketLogDto log=new TicketLogDto();
            log.setId(UUID.randomUUID().toString());
            log.setTicketId(ticketDto.getTicketId());

            log.setApproveOpinion(ticketDto.getApproveOpinion());//处理结果 及其意见
            log.setApproveOperation(ticketDto.getApproveOperation());//同意不同意
            log.setCreateBy(ticketDto.getCreateBy());
            log.setCreateDate(new Date());
            log.setUpdateBy(ticketDto.getUpdateBy());
            log.setUpdateDate(new Date());


            if(StringUtils.isBlank(ticketDto.getApproveOperation())){//处理完成  发给  发起人 审核
                //工单为待审核 4  获取工单创建人的UserId
                String createUserId = this.getTicketCreateUserIdByTicketId(ticketDto.getTicketId());
                this.signIn(ticketDto.getTicketId(),createUserId,"4");
                log.setStatus("4");
            }else{
                //创建人审批 同意（工单为完成 5）
                if("Y".equals(ticketDto.getApproveOperation())){
                    ticketDto.setStatus("5");//完成
                    this.updateTicketInfo(ticketDto);
                    //清空历史待处理人
                    boolean a=this.delTicketToDoByTicketId(ticketDto.getTicketId());
                    log.setStatus("5");

                }else{
                    //不同意（工单未处理中 3）
                    //获取工单最近处理人UserId
                    String lastHandleUserId = this.getTicketLogLastUserIdByTicketId(ticketDto.getTicketId());
                    this.signIn(ticketDto.getTicketId(),lastHandleUserId,"3");
                    log.setStatus("3");
                }
            }
            this.insertTicketLog(log);

            ticketDto.setStatus(log.getStatus());
            this.updateTicketInfo(ticketDto);
            result.put("code","201");
            result.put("status","success");
            result.put("message","工单处理成功!");
        }catch (Exception e){
            result.put("code","500");
            result.put("message","工单处理失败，请核实!");
            throw new ServiceException(result.toJSONString());
        }
        return result;

    }

    /**
     * 单用户签收
     * @param ticketId
     * @param userId
     * @return
     */
    @Transactional(readOnly = false)
    public boolean signIn(String ticketId,String userId,String status){
        //必须是待签收状态 0,才能签收（否者已被他人签收）
        // to  do  ...

        //清空历史待处理人
        boolean a=this.delTicketToDoByTicketId(ticketId);
        User u=new User();
        u.setId(userId);
        //待插入当前  处理人
        TicketToDoDto ticketToDoDto =  new TicketToDoDto(UUID.randomUUID().toString(),ticketId,"","1",userId,"1",new Date(),new Date(),u,u);
        boolean b= this.insertTicketToDo(ticketToDoDto);
        //修改工单状态
        TicketDto ticketDto=new TicketDto();
        ticketDto.setTicketId(ticketId);
        ticketDto.setStatus(status);//处理中
        boolean c=this.updateTicketInfo(ticketDto);
        return a && b && c;
    }

    @Transactional(readOnly = false)
    public List<User> getUserListByDeptId(String deptId){
        return ticketDao.getUserListByDeptId(deptId);
    }
    /**
     * 新增工单
     * @param ticket
     * @return
     */
    @Transactional(readOnly = false)
    public boolean insertTicket(TicketDto ticket){
        int i=ticketDao.insertTicket(ticket);
        return i>0;
    }

    /**
     * 新增工单日子
     * @param ticketLogDto
     * @return
     */
    @Transactional(readOnly = false)
    public boolean insertTicketLog(TicketLogDto ticketLogDto){
        int i=ticketDao.insertTicketLog(ticketLogDto);
        return i>0;
    }

    /**
     * 修改告警业务表的工单ID（建立联系）
     * @param ticket
     * @return
     */
    @Transactional(readOnly = false)
    public boolean updateAlarmTicketByDeviceIdAndStartTime(TicketDto ticket){
        int i=ticketDao.updateAlarmTicketByDeviceIdAndStartTime(ticket);
        return i>0;
    }

    /**
     * 修改工单信息
     * @param ticket
     * @return
     */
    @Transactional(readOnly = false)
    public boolean updateTicketInfo(TicketDto ticket){
        int i=ticketDao.updateTicketInfo(ticket);
        return i>0;
    }

    /**
     * 新增代办
     * @param ticketToDoDto
     * @return
     */
    @Transactional(readOnly = false)
    public boolean insertTicketToDo(TicketToDoDto ticketToDoDto){
        int i=ticketDao.insertTicketToDo(ticketToDoDto);
        return i>0;
    }

    /**
     * 修改代办
     * @param ticketToDoDto
     * @return
     */
    @Transactional(readOnly = false)
    public boolean updateTicketToDoById(TicketToDoDto ticketToDoDto){
        int i=ticketDao.updateTicketToDoById(ticketToDoDto);
        return i>0;
    }

    /**
     * 删除工单对应的所有代办
     * @param ticketId
     * @return
     */
    @Transactional(readOnly = false)
   public boolean delTicketToDoByTicketId(String ticketId){
        int i=ticketDao.delTicketToDoByTicketId(ticketId);
        return i>0;
    }

    /**
     * 获取工单列表信息
     * @param ticket
     * @return
     */
    public List<TicketDto> getTicketList(TicketDto ticket){
        return ticketDao.getTicketList(ticket);
    }

    /**
     *
     * @param page 分页对象
     * @param ticketDto
     * @return
     */
    public Page<TicketDto> findPage(Page<TicketDto> page, TicketDto ticketDto) {
        return super.findPage(page, ticketDto);
    }

    /**
     *
     * @param page 分页对象
     * @param ticketDto
     * @return
     */
    public Page<TicketDto> findAllListPage(Page<TicketDto> page, TicketDto ticketDto) {
        return super.findListAllPage(page, ticketDto);
    }



    /**
     *获取对应工单的最后处理人
     * @param ticketId
     * @return
     */
    public  String getTicketLogLastUserIdByTicketId(String ticketId){
       return ticketDao.getTicketLogLastUserIdByTicketId(ticketId);
    }
    /**
     *获取对应工单的创建人
     * @param ticketId
     * @return
     */
    public  String getTicketCreateUserIdByTicketId(String ticketId){
        return ticketDao.getTicketCreateUserIdByTicketId(ticketId);
    }

    /**
     *获取对应工单
     * @param ticketId
     * @return
     */
    public  TicketDto getTicketInfo(String ticketId){
        return ticketDao.getTicketInfo(ticketId);
    }

    /**
     *获取对应工单的 处理日志
     * @param ticketId
     * @return
     */
    public  List<TicketLogDto> getTicketLogList(String ticketId){
        return ticketDao.getTicketLogList(ticketId);
    }

    public List<TicketComDto> getPumpList( String projectId){
        return ticketDao.getPumpList(projectId);
    }

   public  List<TicketComDto> getDeviceList(String id){
       return ticketDao.getDeviceList(id);
   }

}
