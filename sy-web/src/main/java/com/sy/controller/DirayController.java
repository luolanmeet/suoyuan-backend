package com.sy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cck.Diray;
import com.cck.User;
import com.object.req.AddDirayReq;
import com.object.resp.BaseResp;
import com.sy.service.IDirayService;
import com.sy.service.IUserService;
import com.sy.util.PDFUtil;
import com.sy.util.RespUtil;

/**
 *
 * @author cck
 */
@RestController
public class DirayController extends BaseController {

    @Reference
    private IDirayService dirayService;
    
    @Reference
    private IUserService userService;
    
    @Autowired
    private RespUtil respUtil;

    @RequestMapping(value = "/writeDiray")
    public BaseResp add(Integer userId, String content) {

        AddDirayReq addDirayReq = AddDirayReq.builder()
                .userId(userId)
                .content(content)
                .build();
        dirayService.add(addDirayReq);
        return success();
    }

    @RequestMapping(value = "/myDirays")
    public BaseResp diray(Integer userId) {

        return success(respUtil.getUserDiraysResq(dirayService.getByUserId(userId)));
    }

    @RequestMapping(value = "/getByWriteTime")
    public BaseResp diray(Integer userId, String dateTime) {

        return success(dirayService.getByWriteTime(userId, dateTime));
    }
    
    @RequestMapping(value = "/getDirayFile")
    public ResponseEntity<byte[]> dirayFile(Integer userId) {
        
        User user = userService.getById(userId);
        List<Diray> dirays = dirayService.getByUserId(userId);
        
        String filename = PDFUtil.getName(user.getNickname());
        String path = PDFUtil.create(dirays, user);
        
        File f = new File(path);
        
        InputStream in;
        ResponseEntity<byte[]> response=null ;
        try {
            in = new FileInputStream(f);
            byte[] b=new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            filename = new String(filename.getBytes("gbk"),"iso8859-1");
            headers.add("Content-Disposition", "attachment;filename="+filename);
            headers.add("Content-Type", "application/pdf;charset=UTF-8");
            HttpStatus statusCode=HttpStatus.OK;
            response = new ResponseEntity<byte[]>(b, headers, statusCode);  
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
