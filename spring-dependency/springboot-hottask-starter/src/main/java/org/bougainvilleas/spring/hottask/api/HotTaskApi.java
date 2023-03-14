package org.bougainvilleas.spring.hottask.api;

import org.bougainvilleas.spring.hottask.config.HotTaskRegistrar;
import org.bougainvilleas.spring.hottask.utils.HotTaskUtils;
import org.bougainvilleas.spring.hottask.xml.TaskBody;
import org.bougainvilleas.spring.hottask.xml.XMLRoot;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("hotTask")
public class HotTaskApi
{
    private final HotTaskSVC hotTaskSVC;
    private final HotTaskRegistrar hotTaskRegistrar;

    public HotTaskApi(HotTaskRegistrar hotTaskRegistrar,HotTaskSVC hotTaskSVC)
    {
        Assert.notNull(hotTaskRegistrar, "HotTaskRegistrar must not be null");
        this.hotTaskRegistrar = hotTaskRegistrar;
        Assert.notNull(hotTaskSVC, "HotTaskSVC must not be null");
        this.hotTaskSVC = hotTaskSVC;
    }

    @GetMapping("/getAll")
    public List<TaskInfoVO> getAll()
    {
        List<TaskInfoVO> result = new ArrayList<>();
        Optional.ofNullable(hotTaskSVC.getAll())
                .map(XMLRoot::getTaskSet)
                .orElse(Collections.emptySet())
                .forEach(po -> result.add(HotTaskUtils.po2vo.apply(po)));
        return result;
    }

    @PostMapping("/add")
    public TaskInfoVO add(@RequestBody TaskInfoVO vo)
    {
        TaskBody task = HotTaskUtils.vo2po.apply(vo);
        final XMLRoot root = hotTaskSVC.getAll();
        if (null != root)
        {
            String id = String.valueOf(Integer.parseInt(root.getId()) + 1);
            root.setId(id);

            task.setId(id);
            task.setCreateTime(Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));
            task.setUpdateTime(Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));

            if (CollectionUtils.isEmpty(root.getTaskSet()))
            {
                final HashSet<TaskBody> addSet = new HashSet<>();
                addSet.add(task);
                root.setTaskSet(addSet);
            }
            else
            {
                for (TaskBody po : root.getTaskSet())
                {
                    if (HotTaskUtils.REGISTERED(po, task))
                    {
                        po.setOpen(false);
                        hotTaskRegistrar.RM_TASK.accept(po);
                        break;
                    }
                }
                hotTaskRegistrar.ADD_TASK.accept(task);
                root.getTaskSet().add(task);

            }
            hotTaskSVC.setHotTask(root);
        }
        return HotTaskUtils.po2vo.apply(task);
    }

    @PostMapping("/del")
    public TaskInfoVO del(@RequestBody TaskInfoVO vo)
    {
        if(StringUtils.hasText(vo.getId()))
        {
            final XMLRoot root = hotTaskSVC.getAll();
            if (null != root)
            {
                Optional.ofNullable(root.getTaskSet())
                        .orElse(Collections.emptySet())
                        .stream()
                        .filter(t -> vo.getId().equals(t.getId()))
                        .findFirst()
                        .ifPresent(taskBody ->
                        {
                            taskBody.setOpen(false);
                            taskBody.setDel(true);
                            taskBody.setUpdateTime(Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));
                            hotTaskRegistrar.RM_TASK.accept(taskBody);
                        });
                hotTaskSVC.setHotTask(root);
            }
        }
        return vo;

    }

    @PutMapping("/put")
    public TaskInfoVO put(@RequestBody TaskInfoVO vo)
    {
        TaskBody task = HotTaskUtils.vo2po.apply(vo);
        final XMLRoot root = hotTaskSVC.getAll();
        if (null != root)
        {
            if (!CollectionUtils.isEmpty(root.getTaskSet()))
            {
                for (TaskBody po : root.getTaskSet())
                {
                    //已注册 的 任务关闭，启用新任务
                    if (HotTaskUtils.REGISTERED(po, task))
                    {
                        hotTaskRegistrar.RM_TASK.accept(po);
                        po.setOpen(false);
                    }
                }
            }
            Optional.ofNullable(root.getTaskSet())
                    .orElse(Collections.emptySet())
                    .stream()
                    .filter(t -> task.getId().equals(t.getId()))
                    .findFirst()
                    .ifPresent(taskBody ->
                    {
                        taskBody.setDel(task.isDel());
                        taskBody.setOpen(task.isOpen());
                        taskBody.setClassName(task.getClassName());
                        taskBody.setBeanName(task.getBeanName());
                        taskBody.setMethodName(task.getMethodName());
                        if (null != task.getCronTask())
                            taskBody.setCronTask(task.getCronTask());
                        if (null != task.getFixedDelayTask())
                            taskBody.setFixedDelayTask(task.getFixedDelayTask());
                        if (null != task.getFixedRateTask())
                            taskBody.setFixedRateTask(task.getFixedRateTask());
                        taskBody.setUpdateTime(Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));
                        if(HotTaskUtils.OPEN.and(HotTaskUtils.DEL.negate()).test(taskBody))
                        {
                            hotTaskRegistrar.ADD_TASK.accept(taskBody);
                        }else
                        {
                            hotTaskRegistrar.RM_TASK.accept(taskBody);
                        }
                    });
            hotTaskSVC.setHotTask(root);
        }
        return HotTaskUtils.po2vo.apply(task);
    }
}
