package com.suola.project.util;

import com.suola.project.model.*;
import net.sf.mpxj.*;
import net.sf.mpxj.Resource;
import net.sf.mpxj.Task;
import net.sf.mpxj.reader.ProjectReader;
import net.sf.mpxj.reader.UniversalProjectReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName MppUtils
 * @Description TODO project文件读写工具，基于mpxj组件(https://github.com/joniles/mpxj/releases)
 * TODO 部门与负责人信息MPP中没有相关信息，这里写死
 * TODO 2021.2.20，修正读取空任务的错误
 * @Author hewguo
 * @Date 2020-12-16 15:22
 * @Version 1.0
 **/

@Component
public class MppUtils {
    private final Logger logger = LoggerFactory.getLogger(MppUtils.class);

    private List<Department> departments;
    private List<Principal> principals;

    public MppUtils(){
        departments=new ArrayList<>();
        Department department1=new Department();
        department1.setName("软件开发部");
        department1.setUid(1);
        departments.add(department1);
        Department department2=new Department();
        department2.setName("数据服务部");
        department2.setUid(2);
        departments.add(department2);
        Department department3=new Department();
        department3.setName("技术中心");
        department3.setUid(3);
        departments.add(department3);

        principals=new ArrayList<>();
        Principal principal1=new Principal();
        principal1.setDepartment(1);
        principal1.setName("张三");
        principal1.setUid(1);
        principals.add(principal1);
        Principal principal2=new Principal();
        principal2.setDepartment(1);
        principal2.setName("张三1");
        principal2.setUid(2);
        principals.add(principal2);
        Principal principal3=new Principal();
        principal3.setDepartment(2);
        principal3.setName("张三2");
        principal3.setUid(3);
        principals.add(principal3);
    }

    public List<Task> getTasks(File file){

        try {
            ProjectReader mppReader=new UniversalProjectReader();
            ProjectFile pf=mppReader.read(file);
            logger.debug("读取mpp文件：{}",file.getAbsolutePath());
            return pf.getChildTasks();

        } catch (MPXJException e) {
            logger.error("mpp 文件({})读取错误:{}",file.getAbsolutePath(),e.toString());
            return null;
        }
    }

    /**
    * @ClassName
    * @Description //TODO 获取日历信息
    * @Author
    * @Date
    * @Param
    * @return
    **/
    public List<com.suola.project.model.Calendar> getCalendars(ProjectFile pf){
        try{
            List<com.suola.project.model.Calendar> calendars=new ArrayList<com.suola.project.model.Calendar>();
            for(int i=0;i<pf.getCalendars().size();i++){
                ProjectCalendar pc=pf.getCalendars().get(i);
                com.suola.project.model.Calendar calendar=new com.suola.project.model.Calendar();
                calendar.setUid(""+pc.getUniqueID().intValue());
                calendar.setName(pc.getName());

                if(pc.getParent()!=null){
                    calendar.setIsBaseCalendar(0);
                    calendar.setBaseCalendarUID(""+pc.getParent().getUniqueID().intValue());
                }else{
                    calendar.setIsBaseCalendar(1);
                    calendar.setBaseCalendarUID("-1");
                }

                List<WeekDay> weekDays=new ArrayList<WeekDay>();
                //周工作日信息
                for(int j=1;j<=7;j++){
                    WeekDay wd=new WeekDay();
                    wd.setDayType(j);
                    wd.setDayWorking(pc.getDays()[j-1]== DayType.NON_WORKING?0:1);

                    if(wd.getDayWorking()==1) {
                        //每天的工作时间段
                        if(pc.getCalendarHours(Day.getInstance(j))!=null) {
                            int rangCount = pc.getCalendarHours(Day.getInstance(j)).getRangeCount();
                            if (rangCount > 0) {
                                List<WorkingTime> workingTimes = new ArrayList<>();
                                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                                for (int k = 0; k < rangCount; k++) {
                                    WorkingTime workingTime = new WorkingTime();
                                    DateRange dateRange = pc.getCalendarHours(Day.getInstance(j)).getRange(k);
                                    workingTime.setFromTime(dateFormat.format(dateRange.getStart()));
                                    workingTime.setToTime(dateFormat.format(dateRange.getEnd()));
                                    workingTimes.add(workingTime);
                                }
                                wd.setWorkingTimes(workingTimes);
                            }
                        }else{
                            List<WorkingTime> workingTimes = new ArrayList<>();
                            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                            WorkingTime workingTime = new WorkingTime();
                            workingTime.setFromTime(dateFormat.format(ProjectCalendarWeek.DEFAULT_WORKING_MORNING.getStart()));
                            workingTime.setToTime(dateFormat.format(ProjectCalendarWeek.DEFAULT_WORKING_MORNING.getEnd()));
                            workingTimes.add(workingTime);

                            WorkingTime workingTime1 = new WorkingTime();
                            workingTime1.setFromTime(dateFormat.format(ProjectCalendarWeek.DEFAULT_WORKING_AFTERNOON.getStart()));
                            workingTime1.setToTime(dateFormat.format(ProjectCalendarWeek.DEFAULT_WORKING_AFTERNOON.getEnd()));
                            workingTimes.add(workingTime1);

                            wd.setWorkingTimes(workingTimes);
                        }
                    }

                    weekDays.add(wd);
                }
                calendar.setWeekDays(weekDays);
                //例外日期
                List<ProjectCalendarException> projectCalendarExceptions=pc.getCalendarExceptions();
                if(projectCalendarExceptions!=null&&projectCalendarExceptions.size()>0){
                    List<ExceptionDate> exceptionDates=new ArrayList<>();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    for(ProjectCalendarException pce:projectCalendarExceptions){
                        ExceptionDate exceptionDate=new ExceptionDate();
                        exceptionDate.setDayType(0);//例外日期为0
                        exceptionDate.setDayWorking(pce.getWorking()?1:0);
                        TimePeriod timePeriod=new TimePeriod();

                        timePeriod.setFromDate(dateFormat.format(pce.getFromDate()));
                        timePeriod.setToDate(dateFormat.format(pce.getToDate()));
                        exceptionDate.setTimePeriod(timePeriod);

                        exceptionDates.add(exceptionDate);
                    }

                    calendar.setExceptions(exceptionDates);
                }

                calendars.add(calendar);
            }
            return calendars;
        }catch (Exception e) {
            logger.error("mpp 文件(日历)读取错误:{}",e.toString());
            return null;
        }
    }

    /**
    * @ClassName
    * @Description //TODO 获取资源信息
    * @Author
    * @Date
    * @Param
    * @return
    **/
    public List<com.suola.project.model.Resource> getResources(ProjectFile pf){
        try{
            List<com.suola.project.model.Resource> resources=new ArrayList<>();
            ResourceContainer resourceContainer=pf.getResources();
            for(int i=0;i<resourceContainer.size();i++){
                com.suola.project.model.Resource resource=new com.suola.project.model.Resource();

                Resource mxpResource=resourceContainer.get(i);
                resource.setUid(mxpResource.getUniqueID());
                resource.setName(mxpResource.getName());
                resource.setType(mxpResource.getType().getValue());
                resource.setCost(mxpResource.getCost().longValue());
                resource.setMaxUnits(mxpResource.getMaxUnits().intValue()/100);

                resources.add(resource);
            }
            return resources;

        }catch (Exception e) {
            logger.error("mpp 文件(资源)读取错误:{}",e.toString());
            return null;
        }
    }

    /**
    * @ClassName
    * @Description //TODO 部门信息，暂不实现，mpp文件中没有对应的项
    * @Author
    * @Date
    * @Param
    * @return
    **/
    public List<Department> getDepartments(ProjectFile pf){
        try{
            List<Department> departments=new ArrayList<>();
            //TODO

            return departments;

        }catch (Exception e) {
            logger.error("mpp 文件(部门信息)读取错误:{}",e.toString());
            return null;
        }
    }

    /**
    * @ClassName
    * @Description //TODO 负责人信息，暂不实现，mpp文件中没有对应的项
    * @Author
    * @Date
    * @Param
    * @return
    **/
    public List<Principal> getPricipals(ProjectFile pf){
        try{
            List<Principal> principals=new ArrayList<>();
            // TODO

            return principals;
        }catch (Exception e) {
            logger.error("mpp 文件(负责人信息)读取错误:{}",e.toString());
            return null;
        }
    }



    public void printTask(Task task){
        System.out.printf("WBS:%s ID:%d 任务名称:%s 工期:%s 计划开始时间:%s 计划结束时间:%s ",task.getWBS(),
                task.getID().intValue(),task.getName(),task.getDuration().toString(),
                (task.getStart() == null ? "NA" : new SimpleDateFormat("yyyy-MM-dd").format(task.getStart())),
                (task.getFinish() == null ? "NA" : new SimpleDateFormat("yyyy-MM-dd").format(task.getFinish())));
        StringBuilder resourceName = new StringBuilder();
        for (ResourceAssignment assignment : task.getResourceAssignments()){
            Resource resource = assignment.getResource();
            if (resource != null){
                resourceName.append(resource.getName()+" - " + resource.getPeakUnits() + ",");
            }
        }
//        System.out.println("资源名称: " + resourceName.toString());
    }

    public void printTaskTree(Task task){
        if(task==null){
            return;
        }else{
            printTask(task);
            List<Task> childTasks=task.getChildTasks();
            for(Task childTask:childTasks){
                printTaskTree(childTask);
            }
        }
    }

    /**
    * @ClassName
    * @Description //TODO 获取任务信息
    * @Author
    * @Date
    * @Param
    * @return
    **/
    public com.suola.project.model.Task getTask(Task task){
        if(task!=null){
            try{
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                com.suola.project.model.Task prjTask=new  com.suola.project.model.Task();
                BaseTask baseTask=new BaseTask();

                //=======
//                logger.info("uid:{} -- id:{}",task.getUniqueID(),task.getID());

                baseTask.setId(task.getID());//Number。序号
                baseTask.setName(task.getName());//任务名称
                if(task.getStart()!=null) {
                    baseTask.setStart(dateFormat.format(task.getStart()));//DateTime。开始日期
                }
                if(task.getFinish()!=null) {
                    baseTask.setFinish(dateFormat.format(task.getFinish()));//DateTime。完成日期
                }
                if(task.getDuration()!=null) {
                    baseTask.setDuration(new Double(task.getDuration().getDuration()).longValue());//Number。工期
                }
                if(task.getPercentageComplete()!=null) {
                    baseTask.setPercentComplete(task.getPercentageComplete().longValue());//Number。进度
                }
                baseTask.setManual(task.getTaskMode().getValue());//手动模式。0是自动，1是手动。
                baseTask.setConstraintType(task.getConstraintType().getValue());//限制类型：0越早越好；1越晚越好；2必须开始于；3必须完成于；
                //4不得早于...开始；5不得晚于...开始；6不得早于...完成；7不得晚于...完成
                baseTask.setConstraintDate(task.getConstraintDate()!=null?dateFormat.format(task.getConstraintDate()):null);//DateTime。限制日期
                baseTask.setFixedDate(0);//1或0。是否固定日期(仅限于摘要任务使用),TODO 这里写死了
                baseTask.setUid(task.getUniqueID());//任务UID(唯一性标识符)
                baseTask.setOutlineNumber(task.getOutlineNumber());//体现树形层次和顺序
                if(task.getOutlineLevel()!=null) {
                    baseTask.setOutlineLevel(task.getOutlineLevel());//层次
                }
                if(task.getWork()!=null) {
                    baseTask.setWork(new Double(task.getWork().getDuration()).longValue());//Number。工时
                }

                baseTask.setWeight(100);//Number。权重	TODO 这里写死了
                baseTask.setMilestone(task.getMilestone()?1:0);//1或0。里程碑
                baseTask.setSummary(task.getSummary()?1:0);//1或0。摘要任务
                baseTask.setCritical(task.getCritical()?1:0);//1或0。关键任务
                if(task.getPriority()!=null) {
                    baseTask.setPriority(task.getPriority().getValue());//Number。重要级别
                }
                baseTask.setNotes(task.getNotes());//任务备注

                baseTask.setWbs(task.getWBS());//WBS
                if(task.getActualDuration()!=null) {
                    baseTask.setActualDuration(new Double(task.getActualDuration().getDuration()).longValue());
                }
                baseTask.setProjectUID("100");//TODO 写死了，项目ID
                baseTask.setActualFinish(task.getActualFinish()!=null?dateFormat.format(task.getActualFinish()):null);


                //TODO 部门/负责人信息暂时空缺
                //baseTask.setDepartment(null);
                //baseTask.setPrincipal(null);



                //TODO 处理前置任务
                if(task.getPredecessors()!=null){
                    List<PredecessorLink> predecessorLinks=new ArrayList<>();
                    for(Relation relation:task.getPredecessors()){
                        PredecessorLink predecessorLink=new PredecessorLink();
                        predecessorLink.setTaskid(relation.getTargetTask().getUniqueID());

                        predecessorLink.setPredecessorUID(relation.getTargetTask().getUniqueID());
                        predecessorLink.setType(relation.getType().getValue());
                        predecessorLink.setLinkLag(new Double(relation.getLag().getDuration()).longValue());
                        predecessorLink.setLagFormat(7);//TODO 写死7????

                        predecessorLinks.add(predecessorLink);
                    }
                    baseTask.setPredecessorLinks(predecessorLinks);
                }

                //TODO 处理资源分配关系
                if(task.getResourceAssignments()!=null){
                    List<Assignment> assignments=new ArrayList<>();

                    for(ResourceAssignment resourceAssignment:task.getResourceAssignments()){
                        Assignment assignment=new Assignment();
                        assignment.setResourceUID(resourceAssignment.getResourceUniqueID());
                        assignment.setUnits(resourceAssignment.getUnits().longValue()/100);
                        assignment.setTaskUID(resourceAssignment.getTaskUniqueID());
                        assignments.add(assignment);
                    }
                    baseTask.setAssignments(assignments);
                }

                prjTask.setBaseTask(baseTask);

                return prjTask;
            }catch (Exception e) {
                logger.error("mpp 文件(任务信息)读取错误:{}",e.toString());
                return null;
            }
        }
        return null;
    }

    //TODO 递归调用生成任务树，效率比较低
    public com.suola.project.model.Task getTaskTree(Task task){
        if(task==null){
            return null;
        }else{
            com.suola.project.model.Task prjTask=getTask(task);

            List<com.suola.project.model.Task> prjChileTasks=new ArrayList<>();
            List<Task> childTasks=task.getChildTasks();
            for(Task childTask:childTasks){
                prjChileTasks.add(getTaskTree(childTask));
             }

            prjTask.setChildren(prjChileTasks);

            return prjTask;
        }
    }


    public ProjectModel readFile(String filename){
        ProjectModel projectModel=new ProjectModel();
        try{
            //1.打开MPP文件
            File file=new File(filename);
            ProjectReader mppReader=new UniversalProjectReader();
            ProjectFile pf=mppReader.read(file);

            //2.获取项目信息
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            projectModel.setUid(pf.getProjectProperties().getUniqueID()!=null?pf.getProjectProperties().getUniqueID():"100");//如果保存数据库，需要生成唯一的id
            projectModel.setName(pf.getProjectProperties().getName()!=null?pf.getProjectProperties().getName():"未命名");

            String strDate=dateFormat.format(pf.getProjectProperties().getStartDate());
            projectModel.setStartDate(strDate);
            strDate=dateFormat.format(pf.getProjectProperties().getFinishDate());
            projectModel.setFinishDate(strDate);
            projectModel.setCalendarUID(pf.getDefaultCalendar().getUniqueID());

            //3. 获取日历信息
            projectModel.setCalendars(getCalendars(pf));

            //4.获取资源信息
            projectModel.setResources(getResources(pf));


            //5.获取部门信息
            projectModel.setDepartments(getDepartments(pf));

            //6.获取负责人信息
            projectModel.setPrincipals(getPricipals(pf));


            //7.获取任务信息
            List<com.suola.project.model.Task> tasks =new ArrayList<>();
            for(Task pfTask:pf.getChildTasks()){
                tasks.add(getTaskTree(pfTask));
            }

            projectModel.setTasks(tasks.get(0).getChildren());

            projectModel.setDepartments(departments);
            projectModel.setPrincipals(principals);

            //读取文件信息

            //logger.info(projectModel.toString());
            return projectModel;
        }catch (Exception ex){
            return null;
        }
    }

    public boolean writeFile(String fileName,String jsonBody){
        boolean ret=false;
        logger.info(jsonBody);
        return ret;
    }

//    public static void main(String[] args){
//        String filename="/Users/hewguo/Downloads/kingdee/苍穹初始化工作20191027.mpp";
//        //File file=new File(filename);
//        try {
//            MppUtils mppUtils = new MppUtils();
//            ProjectModel pm= mppUtils.readFile(filename);
//
//            System.out.println(pm.toString());
////            List<Task> tasks = mppUtils.getTasks(file);
////
////            for (Task task : tasks) {
////                mppUtils.printTaskTree(task);
////
////            }
//        }finally {
//
//        }
//    }
}
