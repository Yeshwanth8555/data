package com.it.Spring.Zoo.controller;

import com.it.Spring.Zoo.entity.ZooData;
import com.it.Spring.Zoo.repository.ZooDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.it.Spring.Zoo.service.ZooDataService;

import java.util.List;


@Controller
public class ZooDataController {

    @Autowired
    private ZooDataService zooDataService;

    @Autowired
    ZooDataRepository zooDataRepository;

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/addData")
    public String returnHome(){
        return "addData";
    }
    @GetMapping("/user")
    public String returnUser(){
        return "user";
    }

    @PostMapping("/add")
    public String addZooData(@ModelAttribute ZooData zooData, Model model){
        zooDataService.addZooData(zooData);
        model.addAttribute("successMessage","Successfully saved the data!");
        System.out.println(zooData);
         return "redirect:/addData";
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        List<ZooData> list = zooDataService.zooDataListlist();
        model.addAttribute("dataList",list);
        System.out.println(list);
        return "display";
    }

    @GetMapping("/getAllUser")
    public String getAllUser(Model model) {
        List<ZooData> list1 = zooDataService.zooDataListlist();
        model.addAttribute("dataListUser", list1);
        System.out.println(list1);
        return "userDisplay";
    }

    @GetMapping("/getCarnivore/{dietType}")
    public String dietType(@PathVariable("dietType") String dietType ,Model model){
        List<ZooData> listC = zooDataService.getListByDietType(dietType);
        model.addAttribute("carnivoreList",listC);
        System.out.println(listC);
        return "dietType";
    }

    @PostMapping("/updatedata")
    public String updatedData(@ModelAttribute ZooData zooData){
        System.out.println(zooData);
        zooDataService.updateDataByName(zooData);

        return "redirect:/getAll";
    }

    @GetMapping("/delete/{name}")
    public String deleteOne(@PathVariable("name")String name) {
        System.out.println(name);
        this.zooDataService.deleteByName(name);
        return "redirect:/getAll";
    }

    @GetMapping("/edit/{name}")
    public String getData(@PathVariable("name")String name, Model model) {
        System.out.println(name);
        ZooData deletingData=zooDataRepository.findByName(name);
        model.addAttribute("zoodata", deletingData);
        return "update";
    }

}
