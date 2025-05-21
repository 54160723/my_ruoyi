<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="Jenkins名称" prop="jenkinsName">
        <el-input
          v-model="queryParams.jenkinsName"
          placeholder="请输入Jenkins名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Jenkins地址" prop="jenkinsUrl">
        <el-input
          v-model="queryParams.jenkinsUrl"
          placeholder="请输入Jenkins地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="Jenkins状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:devops:jenkins:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:devops:jenkins:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:devops:jenkins:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:devops:jenkins:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="jenkinsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Jenkins配置ID" align="center" prop="jenkinsId" />
      <el-table-column label="Jenkins名称" align="center" prop="jenkinsName" />
      <el-table-column label="Jenkins地址" align="center" prop="jenkinsUrl" />
      <el-table-column label="Jenkins用户名" align="center" prop="jenkinsUser" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:devops:jenkins:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:devops:jenkins:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改Jenkins配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="Jenkins名称" prop="jenkinsName">
          <el-input v-model="form.jenkinsName" placeholder="请输入Jenkins名称" />
        </el-form-item>
        <el-form-item label="Jenkins地址" prop="jenkinsUrl">
          <el-input v-model="form.jenkinsUrl" placeholder="请输入Jenkins地址" />
        </el-form-item>
        <el-form-item label="Jenkins用户名" prop="jenkinsUser">
          <el-input v-model="form.jenkinsUser" placeholder="请输入Jenkins用户名" />
        </el-form-item>
        <el-form-item label="Jenkins Token" prop="jenkinsToken">
          <el-input v-model="form.jenkinsToken" placeholder="请输入Jenkins Token" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listJenkins, getJenkins, delJenkins, addJenkins, updateJenkins } from "@/api/system/devops/jenkins";

export default {
  name: "Jenkins",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // Jenkins配置表格数据
      jenkinsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        jenkinsName: null,
        jenkinsUrl: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        jenkinsName: [
          { required: true, message: "Jenkins名称不能为空", trigger: "blur" }
        ],
        jenkinsUrl: [
          { required: true, message: "Jenkins地址不能为空", trigger: "blur" }
        ],
        jenkinsUser: [
          { required: true, message: "Jenkins用户名不能为空", trigger: "blur" }
        ],
        jenkinsToken: [
          { required: true, message: "Jenkins Token不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询Jenkins配置列表 */
    getList() {
      this.loading = true;
      listJenkins(this.queryParams).then(response => {
        this.jenkinsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        jenkinsId: null,
        jenkinsName: null,
        jenkinsUrl: null,
        jenkinsUser: null,
        jenkinsToken: null,
        status: "0",
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.jenkinsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加Jenkins配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const jenkinsId = row.jenkinsId || this.ids
      getJenkins(jenkinsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改Jenkins配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.jenkinsId != null) {
            updateJenkins(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addJenkins(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const jenkinsIds = row.jenkinsId || this.ids;
      this.$modal.confirm('是否确认删除Jenkins配置编号为"' + jenkinsIds + '"的数据项？').then(function() {
        return delJenkins(jenkinsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/devops/jenkins/export', {
        ...this.queryParams
      }, `jenkins_${new Date().getTime()}.xlsx`)
    }
  }
};
</script> 