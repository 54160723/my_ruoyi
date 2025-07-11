<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务名称" prop="serviceName">
        <el-input
          v-model="queryParams.serviceName"
          placeholder="请输入服务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="流水线名称" prop="pipelineName">
        <el-input
          v-model="queryParams.pipelineName"
          placeholder="请输入流水线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="流水线类型" prop="pipelineType">
        <el-select v-model="queryParams.pipelineType" placeholder="请选择流水线类型" clearable>
          <el-option
            v-for="dict in dict.type.pipeline_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
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
          v-hasPermi="['system:pipeline:add']"
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
          v-hasPermi="['system:pipeline:edit']"
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
          v-hasPermi="['system:pipeline:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:pipeline:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pipelineList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="流水线ID" align="center" prop="pipelineId" />
      <el-table-column label="服务名称" align="center" prop="serviceName" />
      <el-table-column label="流水线名称" align="center" prop="pipelineName" />
      <el-table-column label="Jenkins名称" align="center" prop="jenkinsName" />
      <el-table-column label="Jenkins任务" align="center" prop="jenkinsJob" />
      <el-table-column label="流水线类型" align="center" prop="pipelineType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pipeline_type" :value="scope.row.pipelineType"/>
        </template>
      </el-table-column>
      <el-table-column label="代码仓库类型" align="center" prop="scmType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.scm_type" :value="scope.row.scmType"/>
        </template>
      </el-table-column>
      <el-table-column label="代码分支" align="center" prop="scmBranch" />
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
            v-hasPermi="['system:pipeline:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:pipeline:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-video-play"
            @click="handleExecute(scope.row)"
            v-hasPermi="['system:pipeline:execute']"
          >执行</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-time"
            @click="handleViewHistory(scope.row)"
            v-hasPermi="['system:devops:pipeline:list']"
          >历史</el-button>
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

    <!-- 添加或修改流水线配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="服务" prop="serviceId">
          <el-select v-model="form.serviceId" placeholder="请选择服务">
            <el-option
              v-for="service in serviceOptions"
              :key="service.serviceId"
              :label="service.serviceName"
              :value="service.serviceId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="流水线名称" prop="pipelineName">
          <el-input v-model="form.pipelineName" placeholder="请输入流水线名称" />
        </el-form-item>
        <el-form-item label="Jenkins配置" prop="jenkinsId">
          <el-select v-model="form.jenkinsId" placeholder="请选择Jenkins配置">
            <el-option
              v-for="jenkins in jenkinsOptions"
              :key="jenkins.jenkinsId"
              :label="jenkins.jenkinsName"
              :value="jenkins.jenkinsId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Jenkins任务" prop="jenkinsJob">
          <el-input v-model="form.jenkinsJob" placeholder="请输入Jenkins任务名称" />
        </el-form-item>
        <el-form-item label="流水线类型" prop="pipelineType">
          <el-select v-model="form.pipelineType" placeholder="请选择流水线类型">
            <el-option
              v-for="dict in dict.type.pipeline_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="流水线脚本" prop="pipelineScript" v-if="form.pipelineType === '1'">
          <el-input v-model="form.pipelineScript" type="textarea" placeholder="请输入流水线脚本" />
        </el-form-item>
        <el-form-item label="代码仓库类型" prop="scmType">
          <el-select v-model="form.scmType" placeholder="请选择代码仓库类型">
            <el-option
              v-for="dict in dict.type.scm_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="代码仓库地址" prop="scmUrl">
          <el-input v-model="form.scmUrl" placeholder="请输入代码仓库地址" />
        </el-form-item>
        <el-form-item label="代码分支" prop="scmBranch">
          <el-input v-model="form.scmBranch" placeholder="请输入代码分支" />
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

    <!-- 执行历史对话框 -->
    <el-dialog title="执行历史" :visible.sync="historyOpen" width="800px" append-to-body>
      <el-table v-loading="historyLoading" :data="historyList">
        <el-table-column label="构建编号" align="center" prop="buildNumber" />
        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <el-tag :type="getStatusType(scope.row.status)">{{ getStatusLabel(scope.row.status) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="开始时间" align="center" prop="startTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.startTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="结束时间" align="center" prop="endTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.endTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="持续时间" align="center" prop="duration">
          <template slot-scope="scope">
            <span>{{ formatDuration(scope.row.duration) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="触发用户" align="center" prop="triggerUser" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-view"
              @click="handleViewLog(scope.row)"
              v-hasPermi="['system:devops:pipeline:view']"
            >查看日志</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-refresh"
              @click="handleRefreshStatus(scope.row)"
              v-if="scope.row.status === '0' || scope.row.status === '1'"
            >刷新状态</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="historyTotal>0"
        :total="historyTotal"
        :page.sync="historyQueryParams.pageNum"
        :limit.sync="historyQueryParams.pageSize"
        @pagination="getHistoryList"
      />
    </el-dialog>

    <!-- 构建日志对话框 -->
    <el-dialog title="构建日志" :visible.sync="logOpen" width="800px" append-to-body>
      <div v-loading="logLoading" class="log-container">
        <pre>{{ buildLog }}</pre>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="refreshLog">刷新日志</el-button>
        <el-button @click="logOpen = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPipeline, getPipeline, delPipeline, addPipeline, updatePipeline, executePipeline, getPipelineHistory, getBuildLog, refreshBuildStatus } from "@/api/system/devops/pipeline";
import { listService } from "@/api/system/devops/service";
import { listJenkins } from "@/api/system/devops/jenkins";

export default {
  name: "Pipeline",
  dicts: ['pipeline_type', 'scm_type', 'sys_normal_disable'],
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
      // 流水线配置表格数据
      pipelineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 服务选项
      serviceOptions: [],
      // Jenkins选项
      jenkinsOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        serviceName: null,
        pipelineName: null,
        pipelineType: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        serviceId: [
          { required: true, message: "服务不能为空", trigger: "change" }
        ],
        pipelineName: [
          { required: true, message: "流水线名称不能为空", trigger: "blur" }
        ],
        jenkinsId: [
          { required: true, message: "Jenkins配置不能为空", trigger: "change" }
        ],
        jenkinsJob: [
          { required: true, message: "Jenkins任务不能为空", trigger: "blur" }
        ],
        pipelineType: [
          { required: true, message: "流水线类型不能为空", trigger: "change" }
        ],
        scmType: [
          { required: true, message: "代码仓库类型不能为空", trigger: "change" }
        ],
        scmUrl: [
          { required: true, message: "代码仓库地址不能为空", trigger: "blur" }
        ],
        scmBranch: [
          { required: true, message: "代码分支不能为空", trigger: "blur" }
        ]
      },
      // 历史记录相关
      historyOpen: false,
      historyLoading: false,
      historyList: [],
      historyTotal: 0,
      historyQueryParams: {
        pageNum: 1,
        pageSize: 10,
        pipelineId: null
      },
      // 日志相关
      logOpen: false,
      logLoading: false,
      buildLog: "",
      currentHistory: null,
      logTimer: null
    };
  },
  created() {
    this.getList();
    this.getServiceOptions();
    this.getJenkinsOptions();
  },
  beforeDestroy() {
    if (this.logTimer) {
      clearInterval(this.logTimer);
    }
  },
  methods: {
    /** 查询流水线配置列表 */
    getList() {
      this.loading = true;
      listPipeline(this.queryParams).then(response => {
        this.pipelineList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询服务选项 */
    getServiceOptions() {
      listService().then(response => {
        this.serviceOptions = response.rows;
      });
    },
    /** 查询Jenkins选项 */
    getJenkinsOptions() {
      listJenkins().then(response => {
        this.jenkinsOptions = response.rows;
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
        pipelineId: null,
        serviceId: null,
        pipelineName: null,
        jenkinsId: null,
        jenkinsJob: null,
        pipelineType: "0",
        pipelineScript: null,
        scmType: null,
        scmUrl: null,
        scmBranch: null,
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
      this.ids = selection.map(item => item.pipelineId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加流水线配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pipelineId = row.pipelineId || this.ids
      getPipeline(pipelineId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改流水线配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pipelineId != null) {
            updatePipeline(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPipeline(this.form).then(response => {
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
      const pipelineIds = row.pipelineId || this.ids;
      this.$modal.confirm('是否确认删除流水线配置编号为"' + pipelineIds + '"的数据项？').then(function() {
        return delPipeline(pipelineIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/pipeline/export', {
        ...this.queryParams
      }, `pipeline_${new Date().getTime()}.xlsx`)
    },
    /** 执行按钮操作 */
    handleExecute(row) {
      this.$modal.confirm('是否确认执行流水线"' + row.pipelineName + '"？').then(() => {
        return executePipeline(row.pipelineId);
      }).then(() => {
        this.$modal.msgSuccess("执行成功");
      }).catch(() => {});
    },
    /** 查看历史按钮操作 */
    handleViewHistory(row) {
      this.historyQueryParams.pipelineId = row.pipelineId;
      this.historyOpen = true;
      this.getHistoryList();
    },
    /** 查询历史记录列表 */
    getHistoryList() {
      this.historyLoading = true;
      getPipelineHistory(this.historyQueryParams).then(response => {
        this.historyList = response.rows;
        this.historyTotal = response.total;
        this.historyLoading = false;
      });
    },
    /** 查看日志按钮操作 */
    handleViewLog(row) {
      this.currentHistory = row;
      this.logOpen = true;
      this.getBuildLog();
      // 如果构建还在进行中，定时刷新日志
      if (row.status === '0' || row.status === '1') {
        this.logTimer = setInterval(() => {
          this.getBuildLog();
        }, 5000);
      }
    },
    /** 获取构建日志 */
    getBuildLog() {
      if (!this.currentHistory) return;
      this.logLoading = true;
      getBuildLog(this.currentHistory.historyId).then(response => {
        this.buildLog = response.data;
        this.logLoading = false;
      });
    },
    /** 刷新日志 */
    refreshLog() {
      this.getBuildLog();
    },
    /** 刷新状态按钮操作 */
    handleRefreshStatus(row) {
      refreshBuildStatus(row.historyId).then(response => {
        this.$modal.msgSuccess("刷新成功");
        this.getHistoryList();
      });
    },
    /** 格式化持续时间 */
    formatDuration(duration) {
      if (!duration) return '-';
      const seconds = Math.floor(duration / 1000);
      const minutes = Math.floor(seconds / 60);
      const hours = Math.floor(minutes / 60);
      if (hours > 0) {
        return `${hours}小时${minutes % 60}分钟`;
      } else if (minutes > 0) {
        return `${minutes}分钟${seconds % 60}秒`;
      } else {
        return `${seconds}秒`;
      }
    },
    /** 获取状态标签类型 */
    getStatusType(status) {
      switch (status) {
        case '0': return 'info';    // 排队中
        case '1': return 'warning'; // 运行中
        case '2': return 'success'; // 成功
        case '3': return 'danger';  // 失败
        default: return 'info';
      }
    },
    /** 获取状态标签文本 */
    getStatusLabel(status) {
      switch (status) {
        case '0': return '排队中';
        case '1': return '运行中';
        case '2': return '成功';
        case '3': return '失败';
        default: return '未知';
      }
    }
  }
};
</script>

<style scoped>
.log-container {
  height: 500px;
  overflow-y: auto;
  background-color: #1e1e1e;
  padding: 10px;
  border-radius: 4px;
}
.log-container pre {
  color: #d4d4d4;
  margin: 0;
  white-space: pre-wrap;
  word-wrap: break-word;
}
</style> 