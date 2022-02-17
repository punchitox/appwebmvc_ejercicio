package com.formacionspring.appwebmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacionspring.appwebmvc.entity.Proyecto;
import com.formacionspring.appwebmvc.service.ProyectoService;

@Controller
public class ProyectoController {
	
	@Autowired
	private ProyectoService servicio;
	
	@GetMapping({"/proyectos"})
	public String listarProyectos(Model model) {
		model.addAttribute("key",servicio.listarTodosLosProyectos());
		return"proyectos";
	}
	
	@GetMapping("/proyecto/nuevo")
	public String formularioProyecto(Model modelo) {
		Proyecto newProyecto = new Proyecto();
		modelo.addAttribute("proyectoKey",newProyecto);
		return "nuevo_proyecto";
	}
	
	@PostMapping("/proyecto")
	public String guardarProyecto(@ModelAttribute("proyectoKey") Proyecto proyecto) {
		servicio.guardarProyecto(proyecto);
		return "redirect:/proyectos";
	}
	
	@GetMapping("/proyecto/editar/{id}")
	public String formularioEdicionProyecto(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("proyectoKey", servicio.obtenerProyectoPorId(id));
		return "editar_proyecto";
		
	}
	
	@PostMapping("/proyecto/editar/{id}")
	public String editarProyecto(@PathVariable Long id, @ModelAttribute("proyectoKey") Proyecto proyecto) {
		Proyecto newProyecto = servicio.obtenerProyectoPorId(id);
		newProyecto.setNombre(proyecto.getNombre());
		newProyecto.setFechaInicio(proyecto.getFechaInicio());
		newProyecto.setFechaFin(proyecto.getFechaFin());
		newProyecto.setActivo(proyecto.getActivo());
		return "redirect:/proyectos";
	}
	
	@GetMapping("proyecto/borrar/{id}")
	public String eliminarProyecto(@PathVariable Long id) {
		servicio.eliminarProyecto(id);
		return "redirect:/proyectos";
	}

}
