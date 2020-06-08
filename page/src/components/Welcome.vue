<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-button type="primary" @click="doInit">
          立即初始化
        </el-button>
      </el-col>
    </el-row>

    <el-dialog title="警告" :visible.sync="dialogVisible" width="30%">
      <span>似乎您已经初始化过数据</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="useBeforeData">使用以前的数据</el-button>
        <el-button @click="refreshData">刷新数据</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
    export default {
      name: "Welcome",
      data(){
        return {
          dialogVisible:false
        }
      },
      methods:{
        doInit(){
          let that = this;
          this.$axios.get("/config/init").then(res => {
            let retCode = res.data.retCode;
            //code == 1的情况下重新询问
            if(retCode === 1){
              that.dialogVisible = true;
            }else if(retCode === 0){
              //跳转主页
              that.$router.push("/main");
            }else{
              that.$message.warning("服务器发生错误");
            }
          }).catch(res => {
            that.$message.warning("发生未知网络错误!");
          })
        },
        useBeforeData(){
          this.$router.push("/main");
        },
        refreshData(){
          let that = this;
          this.$axios.post("/config/init", null, {
            params:{
              refresh:'true'
            }
          }).then(res => {
            let retCode = res.data.retCode;
            if(retCode === 0){
              that.$message.success("成功更新" + res.data.data + "条数据!");
              that.$router.push("/main");
            }else if(retCode === 1){
              that.$message.error("NO AUTH");
            }else{
              that.$message.error("发生未知错误");
            }
          }).catch(res => {
            that.$message.error("网络错误");
          })
        }
      }
    }
</script>

<style scoped>

</style>
