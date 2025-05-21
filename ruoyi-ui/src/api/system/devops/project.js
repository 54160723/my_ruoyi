import request from '@/utils/request'

// 查询DevOps项目列表
export function listProject(query) {
  return request({
    url: '/system/devops/project/list',
    method: 'get',
    params: query
  })
}

// 查询DevOps项目详细
export function getProject(projectId) {
  return request({
    url: '/system/devops/project/' + projectId,
    method: 'get'
  })
}

// 新增DevOps项目
export function addProject(data) {
  return request({
    url: '/system/devops/project',
    method: 'post',
    data: data
  })
}

// 修改DevOps项目
export function updateProject(data) {
  return request({
    url: '/system/devops/project',
    method: 'put',
    data: data
  })
}

// 删除DevOps项目
export function delProject(projectId) {
  return request({
    url: '/system/devops/project/' + projectId,
    method: 'delete'
  })
}

// 导出DevOps项目
export function exportProject(query) {
  return request({
    url: '/system/devops/project/export',
    method: 'post',
    params: query
  })
} 