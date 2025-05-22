import request from '@/utils/request'

// 查询流水线模板列表
export function listTemplate(query) {
  return request({
    url: '/system/template/list',
    method: 'get',
    params: query
  })
}

// 查询流水线模板详细
export function getTemplate(templateId) {
  return request({
    url: '/system/template/' + templateId,
    method: 'get'
  })
}

// 新增流水线模板
export function addTemplate(data) {
  return request({
    url: '/system/template',
    method: 'post',
    data: data
  })
}

// 修改流水线模板
export function updateTemplate(data) {
  return request({
    url: '/system/template',
    method: 'put',
    data: data
  })
}

// 删除流水线模板
export function delTemplate(templateId) {
  return request({
    url: '/system/template/' + templateId,
    method: 'delete'
  })
}

// 导出流水线模板
export function exportTemplate(query) {
  return request({
    url: '/system/template/export',
    method: 'get',
    params: query
  })
} 