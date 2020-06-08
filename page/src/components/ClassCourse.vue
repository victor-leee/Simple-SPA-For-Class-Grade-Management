<template>
  <div id="classCourse">
    <el-row class="small-margin">
      <el-select v-model="val" placeholder="请选择教学班级" @change="onCourseClassSelectionChange">
        <el-option-group v-for="group in options" :key="group.courseName" :label="group.courseName">
          <el-option v-for="courseClass in group.subGroup" :key="courseClass.courseClassId"
                     :label="courseClass.courseClassId" :value="courseClass.courseClassId">

          </el-option>
        </el-option-group>
      </el-select>
    </el-row>

    <div v-show="showTeacherColumn">
      <el-row class="small-margin">
        教师姓名:{{teacherName}}
      </el-row>
      <el-row class="small-margin">
        教师性别:{{teacherSex}}
      </el-row>
      <el-row class="small-margin">
        <el-input placeholder="按照学生ID搜索学生信息" v-model="stuIdSearchModel" spellcheck="false">
          <el-button slot="append" icon="el-icon-search" @click="searchById">

          </el-button>
        </el-input>
      </el-row>
    </div>

    <el-row>
      <el-table :data="courseClassTableData.studentList"
                border
                style="width: 100%"
                height="400"
      :default-sort="{prop:'id', order:'ascending'}">
        <el-table-column prop="id" label="学生ID" width="266" sortable>

        </el-table-column>
        <el-table-column prop="name" label="学生姓名" width="266">

        </el-table-column>
        <el-table-column prop="courseClassGrade.generalGrade" label="平时成绩" width="125" sortable>

        </el-table-column>
        <el-table-column prop="courseClassGrade.midTermGrade" label="期中成绩" width="125" sortable>

        </el-table-column>
        <el-table-column prop="courseClassGrade.experimentGrade" label="实验成绩" width="125" sortable>

        </el-table-column>
        <el-table-column prop="courseClassGrade.finalTestGrade" label="期末测试成绩" width="130" sortable>

        </el-table-column>
        <el-table-column prop="courseClassGrade.finalGrade" label="期末总成绩" width="125" sortable>

        </el-table-column>
<!--        <el-table-column label="查看详情" width="145">-->
<!--          <template slot-scope="scope">-->
<!--            <el-button type="primary" icon="el-icon-user-solid" @click="fetchDetail(scope.row)">-->
<!--              查看详情-->
<!--            </el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
      </el-table>
    </el-row>

<!--    修改用户信息部分-->
    <el-dialog title="用户信息" :visible.sync="userInfoDialogVisible" width="30%">
      <el-form :model="form">
        <el-form-item label="修改学生名字" :label-width="labelWidth">
          <el-input v-model="form.name" autocomplete="off" spellcheck="false">

          </el-input>
        </el-form-item>
        <el-form-item label="修改学生ID" :label-width="labelWidth">
          <el-input v-model="form.id" autocomplete="off" spellcheck="false">
            <template slot="prepend">
              CQU_STUDENT_
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="userInfoDialogVisible = false">取 消</el-button>
          <el-button @click="submitForm" type="primary">提 交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
    export default {
      name: "ClassCourse",
      data(){
        return {
          stuIdSearchModel:'',
          teacherName:'u',
          teacherSex:'M',
          labelWidth:"30",
          userInfoDialogVisible:false,
          showTeacherColumn:false,
          form:{
            name:null,
            oldId:null,
            id:null
          },
          options:[
            {
              courseName:'x',
              subGroup:[
                {
                  courseName:'x',
                  courseClassId:'x'
                }
              ]
            }
          ],
          val:null,
          courseClassTableData:[
            {
              courseClassId:'x',
              courseId:'x',
              startTime:'t',
              studentList:[
                {
                  courseClassGrade:{
                    experimentGrade:1,
                    finalGrade:2,
                    finalTestGrade:3,
                    generalGrade:4,
                    midTermGrade:5
                  }
                }
              ],
              teacher:{
                id:'tid',
                name:'tname',
                sex:'M'
              }
            }
          ]
        }
      },
      methods:{
        onCourseClassSelectionChange(after){
          let that = this;
          this.$axios.get("/course/" + after).then(res => {
            let retCode = res.data.retCode;
            if(retCode !== undefined && retCode === 0){
              that.courseClassTableData = res.data.data;
              that.teacherName = res.data.data.teacher.name;
              that.teacherSex = res.data.data.teacher.sex === 'M' ? "女" : "男";
              that.showTeacherColumn = true;
              for(const student of res.data.data.studentList){
                let idReg = new RegExp(/\D+(\d+)/);
                if(idReg.test(student.id)){
                  student.id = parseInt(RegExp.$1);
                }
              }
            }
          })
        },
        searchById(){
          let id = parseInt(this.stuIdSearchModel);
          let stuList = this.courseClassTableData.studentList;
          let isFound = false;
          for(const student of stuList){
            console.log(student);
            if(student.id === id){
              isFound = true;
              this.$notify({
                title:'学生信息',
                dangerouslyUseHTMLString:true,
                message:"<p>学生姓名:" + student.name + "</p>"
                + "<p class='small-margin'>学生ID:" + student.id + "</p>" +
                  "<p class='small-margin'>学生在此班级平时成绩:" + student.courseClassGrade.generalGrade + "分</p>" +
                  "<p class='small-margin'>学生在此班级期中成绩:" + student.courseClassGrade.midTermGrade + "分</p>" +
                  "<p class='small-margin'>学生在此班级实验成绩:" + student.courseClassGrade.experimentGrade + "分</p>" +
                  "<p class='small-margin'>学生在此班级期末考试成绩:" + student.courseClassGrade.finalTestGrade + "分</p>" +
                  "<p>学生在此班级总成绩:" + student.courseClassGrade.finalGrade + "分</p>",
                duration:10000,
                type:'success'
              })
            }
          }
          if(!isFound){
            this.$message.warning("未能找到id为" + id + "的学生信息");
          }
        },
        fetchDetail(row){
          this.userInfoDialogVisible = true;
        },
        submitForm(){

        }
      },
      mounted() {
        let that = this;
        this.$axios.get("/course/find-all").then(res => {
          if(res.data.retCode !== undefined && res.data.retCode === 0){
            let minCourseClassList = res.data.data;
            let groupArray = [];
            for(const courseClass of minCourseClassList){
              let sub = [];
              let flag = true;
              let courseName = courseClass.courseName;
              for(const subGroup of groupArray){
                if(subGroup.courseName === courseName){
                  flag = false;
                  break;
                }
              }
              if(flag) {
                sub.courseName = courseName;
                groupArray.push(sub);
              }
            }
            for(let i = 0; i < groupArray.length; i++){
              let subGroupClassCourseList = [];
              let groupKey = groupArray[i].courseName;
              for(const courseClass of minCourseClassList){
                if(groupKey === courseClass.courseName){
                  subGroupClassCourseList.push(courseClass);
                }
              }
              groupArray[i].subGroup = subGroupClassCourseList;
            }
            that.options = groupArray;
          }else{
            that.$message.info("无法获取数据");
          }
        }).catch(res => {
          that.$message.warning("网络发生错误");
        })
      }
    }
</script>

<style scoped>
  .small-margin{
    margin-bottom: 10px;
  }
</style>
