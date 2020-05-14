package com.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleValidator scheduleValidator;

    @Autowired()
    @Qualifier(value = "scheduleService")
    private ScheduleService scheduleService;
    Logger logger = Logger.getLogger(ScheduleController.class.getName());
    public void setScheduleService(ScheduleService ps) {
        this.scheduleService = ps;
    }

    @RequestMapping(value = "/schedules", method = RequestMethod.GET)
    public String listSchedules(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("listSchedule", this.scheduleService.listSchedule());
        return "Schedule";
    }

    @RequestMapping(value = "/schedules/add", method = RequestMethod.POST)
    public String addSched(@ModelAttribute @Valid Schedule schedule, BindingResult result, Model model){
        scheduleValidator.validate(schedule, result);
        if(result.hasErrors()){
            model.addAttribute("listSchedule", this.scheduleService.listSchedule());
            return "Schedule";
        }
        if(schedule.getScheduleNum() == null){
            this.scheduleService.addSched(schedule);
        }else {
            this.scheduleService.updateSched(schedule);
        }
        return "redirect:/schedules";
    }

    @RequestMapping(value = "/sched/edit", method = RequestMethod.POST)
    public String editSched(@ModelAttribute Schedule schedule, Model model){
        schedule = this.scheduleService.getSchedById(schedule.getScheduleNum());
        model.addAttribute("schedule", schedule);
        model.addAttribute("listSchedule", this.scheduleService.listSchedule());

        return "Schedule";
    }

    @RequestMapping(value = "/sched/remove", method = RequestMethod.POST)
    public String deleteSched(@ModelAttribute Schedule schedule)
    {
        this.scheduleService.removeSched(schedule.getScheduleNum());
        return "redirect:/schedule";
    }
}
