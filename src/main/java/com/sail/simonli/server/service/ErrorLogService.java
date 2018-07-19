package com.sail.simonli.server.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sail.simonli.server.dao.ErrorLogMapper;
import com.sail.simonli.server.model.ErrorLog;

@Service
@Transactional
public class ErrorLogService {
	
	   private Logger logger=Logger.getLogger(ErrorLogService.class);
	 
	   @Autowired
	   ErrorLogMapper mapper;
	   
	   
	   public void insertErrorLog(ErrorLog record){
		   		   
		   record.setOccurDate(new Date());
		   mapper.insert(record);
	   }

}
