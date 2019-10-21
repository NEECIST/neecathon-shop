import 'package:angular/angular.dart';
import 'package:angular/core.dart'; //coreDirectives
import 'package:angular_forms/angular_forms.dart';  //formDirectives

//Routing
import 'routes.dart';
import 'route_paths.dart';
import 'package:angular_router/angular_router.dart';

//Frontend modules
import 'modulos/carousel/Carousel.dart';
import 'modulos/Shopping/Shopping_component.dart';
import 'modulos/Transactions/Purchase/Transaction_request.dart';
import 'modulos/Transactions/Buy_log/B_Log.dart';
import 'modulos/Footer/Footer.dart';

@Component(
  selector:'General',
  templateUrl: 'Geral_view_component.html',
  directives: [formDirectives,coreDirectives,routerDirectives,CarouselForm,Shop,TransactionsForm,PurchaseLog,PageFooter],
  exports: [RoutePaths,Routes],
)

class GeneralView{

}

