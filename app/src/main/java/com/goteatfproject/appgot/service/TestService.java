package com.goteatfproject.appgot.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goteatfproject.appgot.dao.TestDao;
import com.goteatfproject.appgot.vo.Test;

// IOC 컨테이너에서 서비스를 관리할 수 있도록 어노테이션을 선언해주어야한다
@Service
public class TestService {

  @Autowired
  TestDao testDao;

  public void insert(Test test) throws Exception {
    System.out.println("tService:" + test);

    // 실무에서는 sql 사용
    Date nowDate = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHmmss"); 
    String date = simpleDateFormat.format(nowDate); 
    System.out.println("포맷 지정 후 : " + date);

    test.setDate(date);
    System.out.println("포맷 지정 후: " + test);

    // insert, delete, 'update = 결과값 int형
    System.out.println("db:"+ testDao.insert(test));
    System.out.println("insert db:"+ testDao.insert(test));
  }

  public List<Test> findAll() throws Exception {
    return testDao.findAll();
  }

  public Test detail(int no) {
    System.out.println("ts datail 확인:" + no);
    return testDao.detail(no);
  }

  // insert/ delete / update = int형
  public int update(Test test) {
    System.out.println("ts update 확인:" + test);
    return testDao.update(test);
  }

  public int remove(int no) {
    System.out.println("ts remove 확인:" + no);
    return testDao.remove(no);
  }

}
