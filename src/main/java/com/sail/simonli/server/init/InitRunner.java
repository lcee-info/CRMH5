package com.sail.simonli.server.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class InitRunner  implements ApplicationRunner {

	/**
	 * 初始化数据
	 */
	public void run(ApplicationArguments arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
