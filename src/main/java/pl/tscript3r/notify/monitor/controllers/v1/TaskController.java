package pl.tscript3r.notify.monitor.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.tscript3r.notify.monitor.api.v1.model.TaskDTO;
import pl.tscript3r.notify.monitor.api.v1.model.TaskListDTO;
import pl.tscript3r.notify.monitor.consts.v1.Paths;
import pl.tscript3r.notify.monitor.parsers.ParserFactory;
import pl.tscript3r.notify.monitor.services.TaskService;

import javax.validation.Valid;

@Api(description = "CRUD operations for tasks")
@Slf4j
@RestController
@RequestMapping(Paths.BASE_PATH + Paths.TASK_PATH)
public class TaskController {

    private final TaskService taskService;
    private final ParserFactory parserFactory;

    public TaskController(TaskService taskService, ParserFactory parserFactory) {
        this.taskService = taskService;
        this.parserFactory = parserFactory;
    }

    @ApiOperation(value = "Returns all currently added tasks")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TaskListDTO getAllTasks() {
        log.debug("Viewing all tasks");
        return new TaskListDTO(taskService.getAllTasks());
    }

    @ApiOperation(value = "Returns task by given ID",
            notes = "When ID is not added returns 404 error")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO getTaskById(@PathVariable Long id) {
        log.debug("Getting task id=" + id);
        return taskService.getTaskById(id);
    }

    @ApiOperation(value = "Adds new task", notes = "user_id is required, " +
            "url has to be a valid URL string, or it will be rejected")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO addTask(@Valid @RequestBody TaskDTO taskDTO) {
        log.debug("Adding new task for users id=" + taskDTO.getUsersId());
        return taskService.addTask(taskDTO);
    }

    @ApiOperation(value = "Updates task by given ID", notes = "task_id can not be changed & " +
            "if task does not exists returns 404 error")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        log.debug("Updating task id=" + id);
        return taskService.updateTask(id, taskDTO);
    }

    @ApiOperation(value = "Deletes task by given ID",
            notes = "when task does not exists returns 404 error")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable Long id) {
        log.debug("Deleting task id=" + id);
        taskService.deleteTaskById(id);
    }

    @ApiOperation(value = "Returns current status of the given task ID",
            notes = "When ID is not added returns 404 error")
    @GetMapping("{id}" + Paths.STATUS_TASK_PATH)
    public String getStatus(@PathVariable String test) {
        log.debug("Viewing task status for id: ");
        return "soon";
    }

}
