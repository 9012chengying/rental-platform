package com.rain;

import java.io.IOException;

import com.rain.dao.BookDao;
import com.rain.spiders.Spider;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Spider spider = new Spider();
		for (int i = 1;i <= 1;i++){
			//spider.startLj(i);
			spider.startFtx(i);
		}
		
		
	}

}
