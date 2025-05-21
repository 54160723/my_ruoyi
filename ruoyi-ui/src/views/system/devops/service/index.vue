<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目" prop="projectId">
        <el-select v-model="queryParams.projectId" placeholder="请选择项目" clearable>
          <el-option
            v-for="project in projectOptions"
            :key="project.projectId"
            :label="project.projectName"
            :value="project.projectId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="服务名称" prop="serviceName">
        <el-input
          v-model="queryParams.serviceName"
          placeholder="请输入服务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务标识" prop="serviceKey">
        <el-input
          v-model="queryParams.serviceKey"
          placeholder="请输入服务标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="构建类型" prop="buildType">
        <el-select v-model="queryParams.buildType" placeholder="请选择构建类型" clearable>
          <el-option label="Maven" value="maven" />
          <el-option label="Gradle" value="gradle" />
          <el-option label="Docker" value="docker" />
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
          v-hasPermi="['system:devops:service:add']"
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
          v-hasPermi="['system:devops:service:edit']"
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
          v-hasPermi="['system:devops:service:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:devops:service:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="serviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务ID" align="center" prop="serviceId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="服务名称" align="center" prop="serviceName" />
      <el-table-column label="服务标识" align="center" prop="serviceKey" />
      <el-table-column label="Git分支" align="center" prop="gitBranch" />
      <el-table-column label="构建类型" align="center" prop="buildType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.build_type" :value="scope.row.buildType"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:devops:service:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:devops:service:remove']"
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

    <!-- 添加或修改DevOps服务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目" prop="projectId">
          <el-select v-model="form.projectId" placeholder="请选择项目">
            <el-option
              v-for="project in projectOptions"
              :key="project.projectId"
              :label="project.projectName"
              :value="project.projectId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="服务名称" prop="serviceName">
          <el-input v-model="form.serviceName" placeholder="请输入服务名称" />
        </el-form-item>
        <el-form-item label="服务标识" prop="serviceKey">
          <el-input v-model="form.serviceKey" placeholder="请输入服务标识" />
        </el-form-item>
        <el-form-item label="Git分支" prop="gitBranch">
          <el-input v-model="form.gitBranch" placeholder="请输入Git分支" />
        </el-form-item>
        <el-form-item label="构建类型" prop="buildType">
          <el-select v-model="form.buildType" placeholder="请选择构建类型">
            <el-option label="Maven" value="maven" />
            <el-option label="Gradle" value="gradle" />
            <el-option label="Docker" value="docker" />
          </el-select>
        </el-form-item>
        <el-form-item label="构建命令" prop="buildCommand">
          <el-input v-model="form.buildCommand" type="textarea" placeholder="请输入构建命令" />
        </el-form-item>
        <el-form-item label="Dockerfile路径" prop="dockerfilePath">
          <el-input v-model="form.dockerfilePath" placeholder="请输入Dockerfile路径" />
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
import { listService, getService, delService, addService, updateService } from "@/api/system/devops/service";
import { listProject } from "@/api/system/devops/project";

export default {
  name: "Service",
  dicts: ['build_type'],
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
      // DevOps服务表格数据
      serviceList: [],
      // 项目选项
      projectOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: null,
        serviceName: null,
        serviceKey: null,
        gitBranch: null,
        buildType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectId: [
          { required: true, message: "项目不能为空", trigger: "change" }
        ],
        serviceName: [
          { required: true, message: "服务名称不能为空", trigger: "blur" }
        ],
        serviceKey: [
          { required: true, message: "服务标识不能为空", trigger: "blur" }
        ],
        gitBranch: [
          { required: true, message: "Git分支不能为空", trigger: "blur" }
        ],
        buildType: [
          { required: true, message: "构建类型不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getProjectOptions();
  },
  methods: {
    /** 查询DevOps服务列表 */
    getList() {
      this.loading = true;
      listService(this.queryParams).then(response => {
        this.serviceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询项目选项 */
    getProjectOptions() {
      listProject().then(response => {
        this.projectOptions = response.rows;
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
        serviceId: null,
        projectId: null,
        serviceName: null,
        serviceKey: null,
        gitBranch: null,
        buildType: null,
        buildCommand: null,
        dockerfilePath: null,
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
      this.ids = selection.map(item => item.serviceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加DevOps服务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const serviceId = row.serviceId || this.ids
      getService(serviceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改DevOps服务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serviceId != null) {
            updateService(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addService(this.form).then(response => {
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
      const serviceIds = row.serviceId || this.ids;
      this.$modal.confirm('是否确认删除DevOps服务编号为"' + serviceIds + '"的数据项？').then(function() {
        return delService(serviceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/devops/service/export', {
        ...this.queryParams
      }, `service_${new Date().getTime()}.xlsx`)
    }
  }
};
</script> 