<template>
  <div class="app-container">
    <!-- 输入表单 -->
    <el-form label-width="120px">
      <el-form-item label="借款额度">
        <el-input-number v-model="integralGrade.borrowAmount" :min="0" />
      </el-form-item>
      <el-form-item label="积分区间开始">
        <el-input-number v-model="integralGrade.integralStart" :min="0" />
      </el-form-item>
      <el-form-item label="积分区间结束">
        <el-input-number v-model="integralGrade.integralEnd" :min="0" />
      </el-form-item>
      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate()"
        >
          保存
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
//引入api模块
import integralGradeApi from '@/api/core/integral-grade'
export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用，防止表单重复提交
      integralGrade: {}, // 初始化数据  积分等级对象
    }
  },
  created() {
    //当路由中存在id属性时 回显表单数据
    if (this.$route.params.id) {
      this.fetchById(this.$route.params.id)
    }
  },
  methods: {
    // 根据id查询记录
    fetchById(id) {
      integralGradeApi.getById(id).then((response) => {
        this.integralGrade = response.data.record
      })
    },

    //保存或者更新
    saveOrUpdate() {
      // 禁用保存按钮
      this.saveBtnDisabled = true
      if (!this.integralGrade.id) {
        //调用更新
        this.saveData()
      } else {
        //调用新增
        this.updateData()
      }
    },

    // 新增数据
    saveData() {
      integralGradeApi.save(this.integralGrade).then((response) => {
        this.$message.success(response.message)
        //路由跳转
        this.$router.push('/core/integral-grade/list')
      })
    },
    // 根据id更新记录
    updateData() {
      // 数据的获取
      integralGradeApi.updateById(this.integralGrade).then((response) => {
        this.$message.success(response.message)
        this.$router.push('/core/integral-grade/list')
      })
    },
  },
}
</script>

<style scoped>
</style>