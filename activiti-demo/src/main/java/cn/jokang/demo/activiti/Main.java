package cn.jokang.demo.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import java.util.List;

/**
 * @author zhoukang
 * @date 2019-12-21
 */
public class Main {
    /**
     * 1.通过代码形式创建
     * - 取得ProcessEngineConfiguration对象
     * - 设置数据库连接属性
     * - 设置创建表的策略 （当没有表时，自动创建表）
     * - 通过ProcessEngineConfiguration对象创建 ProcessEngine 对象
     */
    static ProcessEngine createActivitiEngine() {
        //取得ProcessEngineConfiguration对象
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.
            createStandaloneProcessEngineConfiguration();
        //设置数据库连接属性
        engineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        engineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_demo?createDatabaseIfNotExist=true"
            + "&useUnicode=true&characterEncoding=utf8");
        engineConfiguration.setJdbcUsername("root");
        engineConfiguration.setJdbcPassword("zhoukang123");


        // 设置创建表的策略 （当没有表时，自动创建表）
        //          public static final java.lang.String DB_SCHEMA_UPDATE_FALSE = "false";//不会自动创建表，没有表，则抛异常
        //          public static final java.lang.String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";//先删除，再创建表
        //          public static final java.lang.String DB_SCHEMA_UPDATE_TRUE = "true";//假如没有表，则自动创建
        engineConfiguration.setDatabaseSchemaUpdate("true");
        //通过ProcessEngineConfiguration对象创建 ProcessEngine 对象
        ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
        System.out.println("流程引擎创建成功!");
        return processEngine;
    }

    public static void deploy(ProcessEngine processEngine) {

        //获取仓库服务 ：管理流程定义
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()//创建一个部署的构建器
            .addClasspathResource("LeaveActiviti.bpmn")//从类路径中添加资源,一次只能添加一个资源
            .name("请求单流程")//设置部署的名称
            .category("办公类别")//设置部署的类别
            .deploy();

        System.out.println("部署的id" + deploy.getId());
        System.out.println("部署的名称" + deploy.getName());
    }

    public static void startProcess(ProcessEngine processEngine) {

        //指定执行我们刚才部署的工作流程
        String processDefiKey = "leaveBillProcess";
        //取运行时服务
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //取得流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefiKey);//通过流程定义的key 来执行流程
        System.out.println("流程实例id:" + pi.getId());//流程实例id
        System.out.println("流程定义id:" + pi.getProcessDefinitionId());//输出流程定义的id
    }

    public static void queryTask(ProcessEngine processEngine) {
        //任务的办理人
        //取得任务服务
        TaskService taskService = processEngine.getTaskService();
        //创建一个任务查询对象
        TaskQuery taskQuery = taskService.createTaskQuery();
        //办理人的任务列表
//        List<Task> list = taskQuery.taskAssignee(assignee)//指定办理人
        List<Task> list = taskQuery.active()

            .list();
        //遍历任务列表
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                System.out.println("任务的办理人：" + task.getAssignee());
                System.out.println("任务的id：" + task.getId());
                System.out.println("任务的名称：" + task.getName());
            }
        }
    }

    public static void completeTask(ProcessEngine processEngine) {
        String taskId = "15002";
        //taskId：任务id
        processEngine.getTaskService().complete(taskId);
        System.out.println("当前任务执行完毕");
    }

    public static void main(String[] args) {
        ProcessEngine processEngine = createActivitiEngine();
        // id 5001
//        deploy(processEngine);

//        流程实例id:7501
//        流程定义id:leaveBillProcess:2:5004
//        startProcess(processEngine);
        queryTask(processEngine);
//        completeTask(processEngine);
    }
}
