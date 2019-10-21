import 'package:angular/angular.dart';
import 'package:angular_forms/angular_forms.dart';  //formDirectives
import 'package:uniao/src/backend_modulos/services/product_service.dart';
import 'package:uniao/src/backend_modulos/services/purchase_service.dart';
import 'src/todo_list/todo_list_component.dart';  //TodoListComponent

//Routing
import 'src/routes.dart';
import 'src/route_paths.dart';
import 'package:angular_router/angular_router.dart';
import 'package:uniao/src/backend_modulos/services/login_service.dart';
import 'package:uniao/src/backend_modulos/services/transaction_service.dart';
import 'package:uniao/src/backend_modulos/services/user_service.dart';
import 'package:uniao/src/backend_modulos/services/team_service.dart';
import 'src/backend_modulos/teams/get_teams_component.dart';


//Frontend modules
import 'src/modulos/carousel/Carousel.dart';
import 'src/modulos/Navbar/Navbar.dart';
import 'src/modulos/Shopping/Shopping_component.dart';
import 'src/modulos/Transactions/Purchase/Transaction_request.dart';
import 'src/modulos/Transactions/Buy_log/B_Log.dart';
import 'src/modulos/Footer/Footer.dart';

// AngularDart info: https://webdev.dartlang.org/angular
// Components info: https://webdev.dartlang.org/components

@Component(
  selector: 'my-app',
  styleUrls: ['app_component.css'],
  templateUrl: 'app_component.html',
  providers: [ClassProvider(UserService), ClassProvider(TeamService), ClassProvider(LoginService), ClassProvider(TransactionService), ClassProvider(GetTeamsComponent),ClassProvider(ProductService),ClassProvider(PurchaseService)],
  directives: [TodoListComponent,formDirectives,CarouselForm,NavBarform,Shop,TransactionsForm,PurchaseLog,PageFooter,routerDirectives],
  exports: [RoutePaths,Routes],
)
class AppComponent {
  // Nothing here yet. All logic is in TodoListComponent.
}
