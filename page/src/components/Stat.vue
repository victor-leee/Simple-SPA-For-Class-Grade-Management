<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="总成绩排名" @tab-click="doClick()">
        <el-row>
          <el-button type="primary" icon="el-icon-search" @click="queryGrade">查询</el-button>
        </el-row>

        <el-table :data="rankingList" style="width: 100%">
          <el-table-column prop="rank" label="排名" width="220">

          </el-table-column>
          <el-table-column prop="totalGrade" label="总成绩" width="220">

          </el-table-column>
          <el-table-column prop="studentId" label="学生ID" width="300">

          </el-table-column>
          <el-table-column prop="student.name" label="学生姓名" width="220">

          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="学科成绩分布">
        <el-form>
          <el-form-item>
            <el-input autocomplete="off" placeholder="课程ID" v-model="coursePhaseForm.courseId" spellcheck="false">

            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-search-icon" type="primary" @click="submitCoursePhaseQuery">
              查询
            </el-button>
          </el-form-item>
        </el-form>

        <div v-show="canShowProgress">
          <el-card class="box-card small-margin">
            <div class="text item small-margin">
              课程ID:{{courseDistributionList.course.id}}
            </div>
            <div class="text item">
              课程名称:{{courseDistributionList.course.name}}
            </div>
          </el-card>
          <div v-for="(val,index) in courseDistributionList.distributionList" class="small-margin">
            <el-tag>
              {{index * 10 + " - " + (index * 10 + 10) + " 分 "}}
            </el-tag>
            <el-tag>
              {{val + " 人 "}}
            </el-tag>
          </div>
        </div>

      </el-tab-pane>
      <el-tab-pane label="学科成绩排名">
        <el-form>
          <el-form-item>
            <el-input placeholder="课程ID" autocomplete="off" v-model="courseGradeForm.courseId" spellcheck="false">

            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="findCourseGradeStat">
              搜索
            </el-button>
          </el-form-item>
        </el-form>
        <el-table :data="courseGradeStat" style="width: 100%"
        :default-sort="{prop:'courseClassGrade.finalGrade',order:'descending'}">
          <el-table-column label="学生ID" width="240">
            <template slot-scope="scope">
              <el-tag>CQU_STUDENT_</el-tag>
              {{scope.row.id}}
            </template>
          </el-table-column>
          <el-table-column prop="name" label="学生姓名" width="180">

          </el-table-column>
          <el-table-column prop="courseClassGrade.generalGrade" label="平时成绩" width="130" sortable>

          </el-table-column>
          <el-table-column prop="courseClassGrade.midTermGrade" label="中期成绩" width="130" sortable>

          </el-table-column>
          <el-table-column prop="courseClassGrade.experimentGrade" label="实验成绩" width="130" sortable>

          </el-table-column>
          <el-table-column prop="courseClassGrade.finalTestGrade" label="期末测试成绩" width="130" sortable>

          </el-table-column>
          <el-table-column prop="courseClassGrade.finalGrade" label="最终成绩" width="130" sortable>

          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="学生成绩查询">
        <p>
          <el-tag type="primary">
            TIPS
          </el-tag>
          课程ID不输入任何内容默认查询总成绩
        </p>
        <el-form v-model="studentGradeQueryForm">
          <el-form-item>
            <el-input placeholder="学生ID" v-model="studentGradeQueryForm.studentId" spellcheck="false">
              <template slot="prepend">
                CQU_STUDENT_
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-row>
              <el-input placeholder="课程ID" v-model="studentGradeQueryForm.courseId" spellcheck="false">

              </el-input>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="queryStudentGrade">
              查询
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
    export default {
      name: "Stat",
      data(){
        return {
          canShowProgress:false,
          studentGradeQueryForm:{
            studentId:'',
            courseId:''
          },
          rankingList:[

          ],
          courseGradeStat:[

          ],
          coursePhaseForm:{
            courseId:''
          },
          courseGradeForm:{
            courseId:''
          },
          courseDistributionList:{
            course:{
              id:'a',
              name:'q'
            },
            distributionList:[]
          }
        }
      },
      methods:{
        queryStudentGrade(){
          let studentId = this.studentGradeQueryForm.studentId;
          let courseId = this.studentGradeQueryForm.courseId;
          let that = this;
          let idReg = new RegExp(/CQU_STUDENT_(\d+)/);
          let globalData = false;
          if(!idReg.test(studentId)){
            //ID没有添加CQU_STUDENT_
            if(/\d+/.test(studentId)){
              studentId = "CQU_STUDENT_" + studentId;
            }else{
              this.$message.warning("无法解析ID");
              return;
            }
          }
          if(courseId.trim() === ''){
            courseId = "null";
            globalData = true;
          }
          let url = "/student/" + studentId + "/" + courseId + "/rank";
          this.$axios.get(url).then(res => {
            let retCode = res.data.retCode;
            if(retCode === 0) {
              let data = res.data.data;
              if(!globalData) {
                that.$notify({
                  dangerouslyUseHTMLString: true,
                  title: "以下是" + data.student.name + "在" + courseId + "的信息",
                  message: "<p>总成绩:" + data.totalGrade + "</p>" +
                    "<p>课程排名:" + data.rank + "</p>",
                  type: "success",
                  duration:10000
                });
              }else{
                that.$notify({
                  dangerouslyUseHTMLString: true,
                  title: "以下是" + data.student.name + "的总成绩信息",
                  message: "<p>总成绩:" + data.totalGrade + "</p>" +
                    "<p>总排名:" + data.rank + "</p>",
                  type: "success",
                  duration:10000
                });
              }
            }else{
              that.$message.warning("查询发生错误,错误信息 : " + res.data.errMsg);
            }
          }).catch(_ => {
            that.$message.error("网络连接错误");
          })
        },
        findCourseGradeStat(){
          let url = "/course/grade-stat/" + this.courseGradeForm.courseId;
          let that = this;
          this.$axios.get(url).then(res => {
            let retCode = res.data.retCode;
            if(retCode === 0){
              that.$message.success("查询成功");
              let list = res.data.data;
              let filter = new RegExp(/\D+(\d+)/);
              for(const stu of list){
                if(filter.test(stu.id)){
                  stu.id = RegExp.$1;
                }
              }
              that.courseGradeStat = list;
            }
          }).catch(_ => {
            that.$message.error("网络无法连接");
          })
        },
        submitCoursePhaseQuery(){
          let courseId = this.coursePhaseForm.courseId;
          let url = "/course/phase-stat/" + courseId;
          let that = this;
          this.$axios.get(url).then(res => {
            let data = res.data.data;
            if(res.data.retCode === 0){
              that.$message.success("查询成功");
              that.courseDistributionList = data;
              that.canShowProgress = true;
            }else{
              that.$message.warning("无法查询 , 错误信息 : " + res.data.errMsg);
            }
          }).catch(_ => {
            that.$message.warning("网络有问题");
          })
        },
        queryGrade(){
          let that = this;
          this.$axios.get("/student/rank").then(res => {
            that.$message.success("查询成功");
            that.rankingList = res.data.data;
          }).catch(res => {
            that.$message.error("查询失败");
          })
        }
      }
    }
</script>

<style scoped>
  .small-margin {
    margin-bottom: 10px;
  }
</style>
