package com.hlyf.thirdparty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hlyf.thirdparty.dao.miniprogram.CityDao;
import com.hlyf.thirdparty.dao.miniprogram.meituanDao;
import com.hlyf.thirdparty.dao.supermarket.UserDao;
import com.hlyf.thirdparty.domain.City;
import com.hlyf.thirdparty.domain.LoginResult;
import com.hlyf.thirdparty.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ThirdpartyApplicationTests {

	@Autowired
	private UserDao userDao; // 主数据源

	@Autowired
	private CityDao cityDao; // 从数据源

	@Autowired
	private meituanDao mtDao;

	@Test
	public void contextLoads() {
		log.info("123");
		City city = cityDao.findByName("温岭市");
		log.info(city.toString());

		User user = userDao.findByName("泥瓦匠");
		user.setCity(city);
		log.info(user.toString());
	}

	@Test
	public void WxLogin() {
		log.info("123");
		List<LoginResult> loginResult=this.mtDao.ExecProceLogin("{\n" +
				"\t\"sqltext\": \"loginStore\",  \n" +
				"\t\"appId\": \"4115\",\n" +
				"\t\"appSecret\": \"f0b1b7d92d96485e704316604a24bd5a\",\n" +
				"\t\"O2OChannelId\": \"1\",\n" +
				"\t\"openid\": \"\",\n" +
				"\t\"unionid\": \"\",\n" +
				"\t\"phone\": \"\",\n" +
				"\t\"password\": \"\"\n" +
				"}");
		log.info(loginResult.toString());
		log.info(JSON.toJSONString(loginResult, SerializerFeature.WriteMapNullValue));
	}


}
