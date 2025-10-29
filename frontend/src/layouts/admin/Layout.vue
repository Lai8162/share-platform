<template>
  <div class="left-right-layout">
    <el-container>
      <el-header>
        <Header></Header>
      </el-header>
      <el-container>
        <el-aside width="150px">
          <Aside></Aside>
        </el-aside>
        <el-container>
          <el-main>
            <Content></Content>
          </el-main>
          <el-footer>
            <Footer></Footer>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import Content from './Content.vue'
import Footer from './Footer.vue'
import Header from './Header.vue'
import Aside from './Aside.vue';
</script>


<style scoped lang="scss">
.left-right-layout {
  .el-header {
    width: 100%;
    background: black;
  }
  .el-aside {
    height: calc(100vh - 60px);
    background: gray;
  }
}
</style>
