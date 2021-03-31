package com.cloud.impolator.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.impolator.entity.NotaNegociacao;
import com.cloud.impolator.model.NotaNegociacaoService;


// Isso é a mesma coia que resource
@RestController
@RequestMapping("/notanegociacao")
public class NotaNegociacaoController {

	@Autowired
	private NotaNegociacaoService notaNegociacaoService;

	// begin 
	
	@PostMapping("/salvar")
	@ResponseBody
	public String salvar(@RequestParam("file") MultipartFile file){

		NotaNegociacao notaNog = new NotaNegociacao();
		notaNog.setNumNota(6497158);
		notaNog.setFolha(1);
		notaNog.setDataPregao(new Date());

		notaNegociacaoService.salvar(notaNog);
		
		return "oiiiii";
	}
	// end


	@PostMapping("/importar")
	@ResponseBody
	public String importar(@RequestParam("file") MultipartFile file){

		System.out.println(file);
		System.out.println("oi");


		return "oiiiii";
	}

}
