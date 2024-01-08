package com.it.Spring.Zoo.controller;

import com.it.Spring.Zoo.entity.DeletedZooData;
import com.it.Spring.Zoo.entity.Transaction;
import com.it.Spring.Zoo.entity.ZooData;
import com.it.Spring.Zoo.repository.DeletedZooDataRepository;
import com.it.Spring.Zoo.repository.TransactionRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class ZooDataController {

    @Autowired
    private ZooDataService zooDataService;

    @Autowired
    ZooDataRepository zooDataRepository;

    @Autowired
    DeletedZooDataRepository deletedZooDataRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/signUp")
    public String signup() {
        return "signUp";
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

    @PostMapping("/signUpReload")
    public String addTransaction(Transaction transaction) {

        zooDataService.addData(transaction);
        return "redirect:/signUp";
    }
    @PostMapping("/add")
    public String addZooData(RedirectAttributes redirectAttributes, ZooData zooData) {

        String resultMessage = zooDataService.addZooData(zooData);
        redirectAttributes.addFlashAttribute("message", resultMessage);
        return "redirect:/addData";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {

        System.out.println("Login request received. Username: " + username + ", Password: " + password);
        Optional<Transaction> transaction = zooDataService.findByUsernameAndPassword(username, password);

        if (transaction.isPresent()) {
            System.out.println("Login successful");
            return "addData";
        } else {
            System.out.println("Login failed");
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/getAll")
    public String getAll(Model model){

        List<ZooData> list = zooDataService.zooDataList();
        model.addAttribute("dataList",list);
        return "display";
    }

    @PostMapping("/userAdd")
    public String addUserZooData(RedirectAttributes redirectAttributes, ZooData zooData) {

        String resultMessage = zooDataService.addZooData(zooData);
        redirectAttributes.addFlashAttribute("message", resultMessage);
        return "redirect:/user";
    }

    @GetMapping("/deleteAll")
    public ResponseEntity<String> deleteAllAndMoveToAnotherCollection() {

        zooDataService.deleteAllAndMoveToAnotherCollection();
        return ResponseEntity.ok("All data deleted and moved to another collection successfully");
    }
    @PostMapping("/deleteSelected")
    public ResponseEntity<String> deleteSelectedData(@RequestBody List<String> dataToDelete) {

        zooDataService.deleteAndMoveToAnotherCollection(dataToDelete);
        return ResponseEntity.ok("Selected data deleted and moved to another collection successfully");
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


    @GetMapping("/zoo")
    public String getZooData(@RequestParam(required = false) String dietType, Model model) {
        List<ZooData> dataListUser = zooDataService.getListByDietType(dietType);
        model.addAttribute("dataListUser", dataListUser);
        return "userDisplay";
    }

}
