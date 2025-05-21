<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="环境名称" prop="envName">
        <el-input
          v-model="queryParams.envName"
          placeholder="请输入环境名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="环境标识" prop="envKey">
        <el-input
          v-model="queryParams.envKey"
          placeholder="请输入环境标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="环境类型" prop="envType">
        <el-select v-model="queryParams.envType" placeholder="请选择环境类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_env_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="环境状态" clearable>
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
          v-hasPermi="['system:devops:environment:add']"
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
          v-hasPermi="['system:devops:environment:edit']"
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
          v-hasPermi="['system:devops:environment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:devops:environment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="environmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="环境ID" align="center" prop="envId" />
      <el-table-column label="环境名称" align="center" prop="envName" />
      <el-table-column label="环境标识" align="center" prop="envKey" />
      <el-table-column label="环境类型" align="center" prop="envType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_env_type" :value="scope.row.envType"/>
        </template>
      </el-table-column>
      <el-table-column label="Jenkins地址" align="center" prop="jenkinsUrl" :show-overflow-tooltip="true" />
      <el-table-column label="Docker仓库" align="center" prop="dockerRegistry" :show-overflow-tooltip="true" />
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
            v-hasPermi="['system:devops:environment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:devops:environment:remove']"
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

    <!-- 添加或修改环境管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="环境名称" prop="envName">
              <el-input v-model="form.envName" placeholder="请输入环境名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="环境标识" prop="envKey">
              <el-input v-model="form.envKey" placeholder="请输入环境标识" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="环境类型" prop="envType">
              <el-select v-model="form.envType" placeholder="请选择环境类型">
                <el-option
                  v-for="dict in dict.type.sys_env_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in dict.type.sys_normal_disable"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Jenkins地址" prop="jenkinsUrl">
              <el-input v-model="form.jenkinsUrl" placeholder="请输入Jenkins地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Jenkins用户名" prop="jenkinsUser">
              <el-input v-model="form.jenkinsUser" placeholder="请输入Jenkins用户名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Jenkins Token" prop="jenkinsToken">
              <el-input v-model="form.jenkinsToken" placeholder="请输入Jenkins Token" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Docker仓库地址" prop="dockerRegistry">
              <el-input v-model="form.dockerRegistry" placeholder="请输入Docker仓库地址" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Docker用户名" prop="dockerUser">
              <el-input v-model="form.dockerUser" placeholder="请输入Docker用户名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Docker密码" prop="dockerPassword">
              <el-input v-model="form.dockerPassword" type="password" placeholder="请输入Docker密码" />
            </el-form-item>
          </el-col>
        </el-row>
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
import { listEnvironment, getEnvironment, delEnvironment, addEnvironment, updateEnvironment, exportEnvironment } from "@/api/system/devops/environment";

export default {
  name: "Environment",
  dicts: ['sys_normal_disable', 'sys_env_type'],
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
      // 环境管理表格数据
      environmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        envName: null,
        envKey: null,
        envType: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        envName: [
          { required: true, message: "环境名称不能为空", trigger: "blur" }
        ],
        envKey: [
          { required: true, message: "环境标识不能为空", trigger: "blur" }
        ],
        envType: [
          { required: true, message: "环境类型不能为空", trigger: "change" }
        ],
        jenkinsUrl: [
          { required: true, message: "Jenkins地址不能为空", trigger: "blur" }
        ],
        jenkinsUser: [
          { required: true, message: "Jenkins用户名不能为空", trigger: "blur" }
        ],
        jenkinsToken: [
          { required: true, message: "Jenkins Token不能为空", trigger: "blur" }
        ],
        dockerRegistry: [
          { required: true, message: "Docker仓库地址不能为空", trigger: "blur" }
        ],
        dockerUser: [
          { required: true, message: "Docker用户名不能为空", trigger: "blur" }
        ],
        dockerPassword: [
          { required: true, message: "Docker密码不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询环境管理列表 */
    getList() {
      this.loading = true;
      listEnvironment(this.queryParams).then(response => {
        this.environmentList = response.rows;
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
        envId: null,
        envName: null,
        envKey: null,
        envType: null,
        jenkinsUrl: null,
        jenkinsUser: null,
        jenkinsToken: null,
        dockerRegistry: null,
        dockerUser: null,
        dockerPassword: null,
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
      this.ids = selection.map(item => item.envId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加环境管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const envId = row.envId || this.ids
      getEnvironment(envId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改环境管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.envId != null) {
            updateEnvironment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEnvironment(this.form).then(response => {
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
      const envIds = row.envId || this.ids;
      this.$modal.confirm('是否确认删除环境管理编号为"' + envIds + '"的数据项？').then(function() {
        return delEnvironment(envIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.$modal.confirm('是否确认导出所有环境管理数据项？').then(() => {
        this.$modal.loading("正在导出数据，请稍候...");
        return exportEnvironment(this.queryParams);
      }).then(response => {
        this.download(response.msg);
        this.$modal.closeLoading();
      }).catch(() => {});
    }
  }
};
</script> 