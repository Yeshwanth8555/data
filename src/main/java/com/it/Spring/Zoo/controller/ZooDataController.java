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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addZooData(RedirectAttributes redirectAttributes, ZooData zooData) {
        String resultMessage = zooDataService.addZooData(zooData);
        redirectAttributes.addFlashAttribute("message", resultMessage);
        return "redirect:/addData";
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        List<ZooData> list = zooDataService.zooDataList();
        model.addAttribute("dataList",list);
        return "display";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        zooDataRepository.deleteAll();
        return "display";
    }

    @GetMapping("/getAllUser")
    public String getAllUser(Model model) {
        List<ZooData> list1 = zooDataService.zooDataList();
        model.addAttribute("dataListUser", list1);

        return "userDisplay";
    }

    @GetMapping("/getDietType/{dietType}")
    public String dietType(@PathVariable("dietType") String dietType ,Model model){
        List<ZooData> listC = zooDataService.getListByDietType(dietType);
        model.addAttribute("carnivoreList",listC);

        return "dietType";
    }

    @PostMapping("/updatedata")
    public String updatedData(@ModelAttribute ZooData zooData){

        zooDataService.updateDataByName(zooData);

        return "redirect:/getAll";
    }

    @GetMapping("/delete/{name}")
    public String deleteOne(@PathVariable("name") String name) {

        this.zooDataService.deleteByName(name);
        return "redirect:/getAll";
    }

    @GetMapping("/edit/{name}")
    public String getData(@PathVariable("name")String name, Model model) {

        ZooData deletingData=zooDataRepository.findByName(name);
        model.addAttribute("zoodata", deletingData);
        return "update";
    }
    @PostMapping("/deleteSelected")
    public void deleteSelectedData(@RequestBody List<String> dataToDelete) {
        List<ZooData> selectedDataList = zooDataRepository.findAllByNameIn(dataToDelete);
//        System.out.println("Received dataToDelete: " + dataToDelete);
        zooDataRepository.deleteAll(selectedDataList);

    }

}
