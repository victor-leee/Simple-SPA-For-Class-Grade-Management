package cn.leetechweb.pj4.controller;

import cn.leetechweb.pj4.DTO.RankDTO;
import cn.leetechweb.pj4.domain.Result;
import cn.leetechweb.pj4.service.StudentServiceImpl;
import cn.leetechweb.pj4.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.leetechweb.pj4.constant.GlobalConstant.*;

/**
 * Project : school.project4
 * Package : cn.leetechweb.pj4.controller
 * Time : 2020/6/5 11:26
 *
 * @author : Victor
 */
@RestController
@RequestMapping(value = "/student", produces = JSON_RESULT)
public class StudentController {
    private StudentServiceImpl studentService;
    @Autowired
    private void setStudentService(StudentServiceImpl studentService){
        this.studentService = studentService;
    }

    @RequestMapping(value = "/{studentId}/{courseId}/rank")
    public String studentRankingStatus(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId){
        if(StringUtils.isBlank(courseId)){
            courseId = null;
        }
        RankDTO rankDTO = studentService.findStudentRanking(studentId, courseId);
        if(rankDTO != null){
            return JSON.toJSONString(Result.success(rankDTO));
        }
        return JSON.toJSONString(Result.fail("Params error"));
    }

    @RequestMapping(value = "/rank")
    public String studentRankList(){
        List<RankDTO> rankDTOList = studentService.findStudentRankingList();
        if(rankDTOList == null || rankDTOList.size() == 0){
            return JSON.toJSONString(Result.fail("No stat available"));
        }
        return JSON.toJSONString(Result.success(rankDTOList));
    }
}
