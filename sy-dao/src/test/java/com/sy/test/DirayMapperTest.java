package com.sy.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.cck.Diray;
import com.sy.Application;
import com.sy.mapper.DirayMapper;

/**
*
* @author cck
*/
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({ Application.class })
public class DirayMapperTest {
    
    @Autowired
    DirayMapper dirayMapper;
    
	@Test
	public void testAdd() {

		Diray diray = Diray.builder()
				.userId(1)
				.content("hello world")
				.build();
		dirayMapper.save(diray);
	}

	@Test
	public void testGetByUserId() {
		
		List<Diray> list = dirayMapper.getByUserId(1);
		System.out.println(list);
		Date date = list.get(0).getWriteTime();
		
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
		System.out.println(dateTimeFormat.format(date));
		System.out.println(timeFormat.format(date));
	}
	
	@Test
	public void testGetByWriteTime() {
		
		List<Diray> list = dirayMapper.getByWriteTime(1, "2018-3-11");
		System.out.println(list);
	}
	
}
