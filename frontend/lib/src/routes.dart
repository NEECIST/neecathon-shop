import 'package:angular_router/angular_router.dart';
import 'package:uniao/src/backend_modulos/admin/Inventory_page/AdminInventory.dart';
import 'package:uniao/src/backend_modulos/admin/Transactions_page/AdminTransactions.dart';

import 'backend_modulos/login/login_component.template.dart' as login_template;
import 'geral.template.dart' as shop_template;
import 'backend_modulos/create_team/create_team_component.template.dart' as register_template;
import 'backend_modulos/admin/Inventory_page/AdminInventory.template.dart' as inventory_template;
import 'backend_modulos/admin/Transactions_page/AdminTransactions.template.dart' as transaction_template;


import 'route_paths.dart';

export 'route_paths.dart';

class Routes{
  static final Login= RouteDefinition(
    routePath: RoutePaths.login,
    component: login_template.LoginComponentNgFactory,
  );

  static final Compras= RouteDefinition(
    routePath: RoutePaths.compras,
    component: shop_template.GeneralViewNgFactory,   //nome da funcao e NgFactory
  );

  static final Register= RouteDefinition(
    routePath: RoutePaths.register,
    component: register_template.CreateTeamComponentNgFactory,   //nome da funcao e NgFactory
  );

  static final AdminInventory= RouteDefinition(
    routePath: RoutePaths.admin_inventory,
    component: inventory_template.AdminInventoryComponentNgFactory,   //nome da funcao e NgFactory
  );

  static final AdminTransaction= RouteDefinition(
    routePath: RoutePaths.admin_transaction,
    component: transaction_template.AdminTransactionComponentNgFactory,   //nome da funcao e NgFactory
  );

  static final all= <RouteDefinition>[
    Login,
    Compras,
    Register,
    AdminInventory,
    AdminTransaction,
    RouteDefinition.redirect(
      path: '',
      redirectTo: RoutePaths.compras.toUrl(),
    ),
  ];
}

