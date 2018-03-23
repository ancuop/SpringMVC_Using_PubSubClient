package com.example.simple_resful.controllers;

import com.example.simple_resful.form.FeedFishForm;
import com.example.simple_resful.models.FishControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fish")
public class FishController {

    private final static Logger logger = LoggerFactory.getLogger(FishController.class);

    private List<FishControl> fishControls = new ArrayList<>();

    @Value("${message.welcome}")
    private String welcomeMessage;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("welcomeMessage", welcomeMessage);
        model.addAttribute("fishControls", fishControls);
        return "fish_home";
    }

    @RequestMapping(value = "/fish_control", method = RequestMethod.GET)
    public String openFishControlPage(Model model) {
        logger.info("### open fish control page");
        FeedFishForm feedFishForm = new FeedFishForm();
        model.addAttribute("feedFishForm", feedFishForm);
        return "fish_control";
    }

    @RequestMapping(value = "/feedNow", method = RequestMethod.GET)
    public String feedNow(Model model) {
        logger.info("### feed now");
        model.addAttribute("infoMessage", "feeded fish");
        return "fish_control";
    }

    @RequestMapping(value = "feedAfterTime", method = RequestMethod.POST)
    public String feedAfterTime(Model model, @ModelAttribute(value = "FeedFishForm")FeedFishForm feedFishForm) {
        String milligram = feedFishForm.getMilligram();
        String time = feedFishForm.getTime();
        logger.info("milligram: " + milligram + ", time: " + time);
        /* ham nay phai dung redirect thi moi duoc*/
        // model.addAttribute("infoMessage", "milligram: " + milligram + ", time: " + time);
        // return "redirect:/fish/fish_control";

        fishControls.add(new FishControl(milligram, time));

        return "redirect:/fish/home";
    }
}
