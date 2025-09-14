<script setup lang="ts"></script>

<template>
  <div class="left-right-layout">
    <el-container>
      <el-header>
        <LeftRightHeader></LeftRightHeader>
      </el-header>
      <el-container>
        <el-aside width="150px">
          <LeftRightAside></LeftRightAside>
        </el-aside>
        <el-container>
          <el-main>
            <LeftRightContent></LeftRightContent>
          </el-main>
          <el-footer>
            <LeftRightFooter></LeftRightFooter>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

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
