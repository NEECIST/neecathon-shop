import 'dart:html';
import 'package:angular/angular.dart';
import 'package:angular_forms/angular_forms.dart';
import 'package:angular_router/angular_router.dart';
import 'package:http/http.dart';
import 'package:uniao/src/backend_modulos/Classes/team.dart';
import 'package:uniao/src/backend_modulos/services/team_service.dart';

import '../../Classes/product.dart';
import '../../services/product_service.dart';


@Component(
  selector:'AdminInventory',
  templateUrl: 'AdminInventory_component.html',
  directives: [formDirectives,coreDirectives,routerDirectives],
)

class AdminInventoryComponent implements OnInit{
  Product product= new Product(0, "", 0, "", 0, "");
  String out;

  bool submitted=false;
  bool success=false;
  bool increase;
  bool changeVal = false;

  String cash;
  String selected;

  List<Team> teams;
  List<Product> products;
  ProductService _productService;
  TeamService _teamService;

  AdminInventoryComponent(this._productService, this._teamService);

  Future<void> ConfirmChange() async{
    success=false;
    bool confirm = window.confirm("Adicionar este novo Produto?");
    if(confirm==true)
    {
      out= await _productService.CreateProduct(product,window.localStorage['neec_id']);
      submitted=true;
      if(out== 'Product created with success'){
        success=true;
        products= await _productService.getAllProducts(window.localStorage['neec_id']);
      }
      else{
        success=false;
      }
    }
  }

  void update(String name) {
    changeVal = true;
    selected = name;
  }
  void updateCash(Team team){
    _teamService.updateCash(team.name, int.parse(cash));
    updateTeam();
    changeVal = false;
  }

  void updateTeam() async{
    teams = await _teamService.getAllTeams();
  }

  Future<void> UpdateChange(bool state,Product product) async{
    bool confirm = window.confirm("Alterar ${product.name}?");

    increase=state;

    if(confirm==true)
    {
      out= await _productService.UpdateProduct(product,window.localStorage['neec_id'],increase);
      if(out=="Product updated")
        {
          products= await _productService.getAllProducts(window.localStorage['neec_id']);
        }
      else{
        window.alert(out);
      }
    }
  }

  @override
  Future<void> ngOnInit() async{
    products= await _productService.getAllProducts(window.localStorage['neec_id']);
    teams = await _teamService.getAllTeams();
  }

}
