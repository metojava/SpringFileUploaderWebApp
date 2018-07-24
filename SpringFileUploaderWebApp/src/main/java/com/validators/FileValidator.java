package com.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.Upfile;


public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		
		return Upfile.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		// TODO Auto-generated method stub
		Upfile mpf = (Upfile)arg0;
		if(mpf.getMpf().getSize() == 0)
			errors.rejectValue("mpf", "req.fileup" );
	}

}
