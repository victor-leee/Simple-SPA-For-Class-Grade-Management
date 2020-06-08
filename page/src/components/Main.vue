<template>
  <el-container class="bigContainer">
    <el-aside>
      <el-row class="verticalGap">
        <el-button type="primary" @click="classCourseStat">
          班级统计信息
        </el-button>
      </el-row>
      <el-row>
        <el-button type="primary" @click="subjectStat">
          学科统计信息
        </el-button>
      </el-row>
    </el-aside>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    export default {
      name: "Main",
      methods:{
        classCourseStat(){
          this.$router.push("/main/class");
        },
        subjectStat(){
          this.$router.push("/main/stat");
        }
      }
    }
</script>

<style scoped>
  .bigContainer {
    height: 660px;
  }
  .verticalGap {
    margin-bottom: 88px;
  }
</style>
