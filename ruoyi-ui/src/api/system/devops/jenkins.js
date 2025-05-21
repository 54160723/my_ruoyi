import request from '@/utils/request'

// 查询Jenkins配置列表
export function listJenkins(query) {
  return request({
    url: '/system/devops/jenkins/list',
    method: 'get',
    params: query
  })
}

// 查询Jenkins配置详细
export function getJenkins(jenkinsId) {
  return request({
    url: '/system/devops/jenkins/' + jenkinsId,
    method: 'get'
  })
}

// 新增Jenkins配置
export function addJenkins(data) {
  return request({
    url: '/system/devops/jenkins',
    method: 'post',
    data: data
  })
}

// 修改Jenkins配置
export function updateJenkins(data) {
  return request({
    url: '/system/devops/jenkins',
    method: 'put',
    data: data
  })
}

// 删除Jenkins配置
export function delJenkins(jenkinsId) {
  return request({
    url: '/system/devops/jenkins/' + jenkinsId,
    method: 'delete'
  })
}

// 导出Jenkins配置
export function exportJenkins(query) {
  return request({
    url: '/system/devops/jenkins/export',
    method: 'get',
    params: query
  })
} 