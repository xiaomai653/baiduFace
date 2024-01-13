package com.jni;

import com.jni.face.FaceManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaiduFaceApplicationTests {

	@Autowired
	private FaceManager manager;

	@Test
	void contextLoads() {
		manager.getUserImage();
	}

}
