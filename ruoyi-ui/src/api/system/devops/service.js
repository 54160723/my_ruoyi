import request from '@/utils/request'

// 查询服务配置列表
export function listService(query) {
  return request({
    url: '/system/devops/service/list',
    method: 'get',
    params: query
  })
}

// 查询服务配置详细
export function getService(serviceId) {
  return request({
    url: '/system/devops/service/' + serviceId,
    method: 'get'
  })
}

// 新增服务配置
export function addService(data) {
  return request({
    url: '/system/devops/service',
    method: 'post',
    data: data
  })
}

// 修改服务配置
export function updateService(data) {
  return request({
    url: '/system/devops/service',
    method: 'put',
    data: data
  })
}

// 删除服务配置
export function delService(serviceId) {
  return request({
    url: '/system/devops/service/' + serviceId,
    method: 'delete'
  })
}

// 导出服务配置
export function exportService(query) {
  return request({
    url: '/system/devops/service/export',
    method: 'post',
    params: query
  })
} 