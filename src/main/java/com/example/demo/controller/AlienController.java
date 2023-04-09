package com.example.demo.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@GetMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping("/alien")
	public Alien addAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@GetMapping("/getAlien")
	public ModelAndView getById(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(id).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	
	@GetMapping("/alien/{id}")
	public Optional<Alien> getBy(@PathVariable("id") int id) {
		return repo.findById(id);
	}
	
	@GetMapping("/alien")
	public List<Alien> findAll() {
		return repo.findAll();
	}
	
	@DeleteMapping("/alien/{id}")
	public void deleteEndPoint(@PathVariable("id") int id) {
		repo.deleteById(id);
	}
	
	@PutMapping("/alien")
	public Alien putEndPoint(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
//	@GetMapping("/GetAll")
//	@ResponseBody
//	public List<Alien> getEndPoint() {
//		return repo.findAll();
//	}
//	
//	@GetMapping("GetById")
//	public Optional<Alien> getById(@PathVariable("id") int id) {
//		return repo.findById(id);
//	}
	
}
