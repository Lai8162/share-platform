import hasPermission from '@/directive/permission/has-permission'
import hasRole from '@/directive/permission/has-role'

function initDirective(app: any) {
  app.directive('hasRole', hasRole)
  app.directive('hasPermission', hasPermission)
}

export default initDirective
