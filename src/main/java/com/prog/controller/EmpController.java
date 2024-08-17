package com.prog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prog.entity.Employee;
import com.prog.service.EmpService;

@Controller
public class EmpController {
    
    @Autowired
    private EmpService service;
    
    @GetMapping("/")
    public String home(Model m) {
        List<Employee> emp = service.getAllEmp();
        m.addAttribute("emp", emp); // Add the employee list to the model
        return "index";
    }

    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp";
    }
    
    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee e, RedirectAttributes redirectAttributes) {
        System.out.println(e);
        service.addEmp(e);
        redirectAttributes.addFlashAttribute("msg", "Employee Added Successfully..");
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
    	
    	Employee e = service.getEmpById(id);
    	m.addAttribute("emp", e);
    	return "edit";
    }
    
    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee e, RedirectAttributes redirectAttributes)
    {
    	service.addEmp(e);
        redirectAttributes.addFlashAttribute("msg", "Employee Data Updated Successfully..");
    	return "redirect:/";
    }
    
    
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id, RedirectAttributes redirectAttributes)
     {
    	
    	service.deleteEmp(id);
        redirectAttributes.addFlashAttribute("msg", "Employee Data Deleted Successfully..");
    	return "redirect:/";
    	
    }
}
