import request from '@/utils/request'

// 查询任务配置列表
export function listTask(query) {
  return request({
    url: '/system/devops/task/list',
    method: 'get',
    params: query
  })
}

// 查询任务配置详细
export function getTask(taskId) {
  return request({
    url: '/system/devops/task/' + taskId,
    method: 'get'
  })
}

// 新增任务配置
export function addTask(data) {
  return request({
    url: '/system/devops/task',
    method: 'post',
    data: data
  })
}

// 修改任务配置
export function updateTask(data) {
  return request({
    url: '/system/devops/task',
    method: 'put',
    data: data
  })
}

// 删除任务配置
export function delTask(taskId) {
  return request({
    url: '/system/devops/task/' + taskId,
    method: 'delete'
  })
}

// 导出任务配置
export function exportTask(query) {
  return request({
    url: '/system/devops/task/export',
    method: 'get',
    params: query
  })
} 