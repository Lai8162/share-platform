import hasPermission from '@/directives/permission/has-permission'
import hasRole from '@/directives/permission/has-role'

function initDirective(app: any) {
  app.directive('hasRole', hasRole)
  app.directive('hasPermission', hasPermission)
}

export default initDirective
