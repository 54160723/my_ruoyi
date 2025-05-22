import request from '@/utils/request'

// 查询流水线配置列表
export function listPipeline(query) {
  return request({
    url: '/system/devops/pipeline/list',
    method: 'get',
    params: query
  })
}

// 查询流水线配置详细
export function getPipeline(pipelineId) {
  return request({
    url: '/system/devops/pipeline/' + pipelineId,
    method: 'get'
  })
}

// 新增流水线配置
export function addPipeline(data) {
  return request({
    url: '/system/devops/pipeline',
    method: 'post',
    data: data
  })
}

// 修改流水线配置
export function updatePipeline(data) {
  return request({
    url: '/system/devops/pipeline',
    method: 'put',
    data: data
  })
}

// 删除流水线配置
export function delPipeline(pipelineId) {
  return request({
    url: '/system/devops/pipeline/' + pipelineId,
    method: 'delete'
  })
}

// 导出流水线配置
export function exportPipeline(query) {
  return request({
    url: '/system/devops/pipeline/export',
    method: 'get',
    params: query
  })
}

// 执行流水线
export function executePipeline(pipelineId) {
  return request({
    url: '/system/devops/pipeline/execute/' + pipelineId,
    method: 'put'
  })
}

// 查询流水线执行历史
export function getPipelineHistory(query) {
  return request({
    url: '/system/devops/pipeline/history',
    method: 'get',
    params: query
  })
}

// 获取构建日志
export function getBuildLog(historyId) {
  return request({
    url: '/system/devops/pipeline/log/' + historyId,
    method: 'get'
  })
}

// 刷新构建状态
export function refreshBuildStatus(historyId) {
  return request({
    url: '/system/devops/pipeline/status/' + historyId,
    method: 'put'
  })
} 