import request from '@/utils/request'

// 查询环境管理列表
export function listEnvironment(query) {
  return request({
    url: '/system/devops/environment/list',
    method: 'get',
    params: query
  })
}

// 查询环境管理详细
export function getEnvironment(envId) {
  return request({
    url: '/system/devops/environment/' + envId,
    method: 'get'
  })
}

// 新增环境管理
export function addEnvironment(data) {
  return request({
    url: '/system/devops/environment',
    method: 'post',
    data: data
  })
}

// 修改环境管理
export function updateEnvironment(data) {
  return request({
    url: '/system/devops/environment',
    method: 'put',
    data: data
  })
}

// 删除环境管理
export function delEnvironment(envId) {
  return request({
    url: '/system/devops/environment/' + envId,
    method: 'delete'
  })
}

// 导出环境管理
export function exportEnvironment(query) {
  return request({
    url: '/system/devops/environment/export',
    method: 'post',
    params: query
  })
} 