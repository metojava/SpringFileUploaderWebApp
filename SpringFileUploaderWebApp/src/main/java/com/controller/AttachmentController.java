package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Upfile;
import com.validators.FileValidator;

@Controller
public class AttachmentController {

	@Autowired 
	private FileValidator fileValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(fileValidator);
		
	}
	@RequestMapping("/uploader")
	public ModelAndView uploader()
	{
		ModelAndView mv = new ModelAndView("uploader");
		mv.addObject("upfile", new Upfile());
		return mv;
	}
	@RequestMapping("/uploadfile")
	public String uploadfile(@ModelAttribute("upfile")  Upfile upfile,BindingResult result)
	{
		
		InputStream ins = null;
		OutputStream os = null;
		
		//if(result.hasErrors()) return "redirect:/uploader.htm";
		
		MultipartFile file = upfile.getMpf();
		
		//fileValidator.validate(upfile, result);
		String fileName = file.getOriginalFilename();
		// change this to some path on your pc
		String directory = "C:\\Users\\User\\Desktop\\files\\";
		try
		{
		ins = file.getInputStream();
		File newFile = new File(directory+fileName);
		os = new FileOutputStream(newFile);
		
		byte[] bs=new byte [1024];
		while(ins.read(bs)!=-1)
		{
			
			os.write(bs);
		}
		
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
//			model.addAttribute("message", "error");
//			return "redirect:/error.htm";
		}
		
		
		return "redirect:/uploader.htm";
		
	}
	
}
