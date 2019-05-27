package com.proiect.rovigneta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ClientService service;

    @RequestMapping(value={"","/","/index"})
    public String viewHomePage(){

       return "index";
    }
    @RequestMapping("/despre")
    public String viewDesprePage(){

       return "despre";
    }
    @RequestMapping("/intrebari")
    public String viewIntrebariPage(){

       return "intrebari";
    }

    @RequestMapping("/verificare")
    public String verificareVig(Model model){
        List<Client>listClients = service.listAll();
        model.addAttribute("listClients",listClients);
        return "verificare";
    }

    @RequestMapping("/new")
    public String addNewClient(Model model){
        Client client = new Client();
        model.addAttribute("client",client);
        return "new_client";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("client") Client client){
        service.save(client);
        return "redirect:/verificare";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPage(@PathVariable(name = "id") long id){
        ModelAndView mav = new ModelAndView("edit_client");
        Client client = service.getById(id);
        mav.addObject("client",client);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") long id){
        service.delete(id);
        return "redirect:/verificare";
    }

}
