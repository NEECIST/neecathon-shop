import 'dart:html';
import 'package:angular/angular.dart';
import 'package:angular/core.dart'; //coreDirectives
import 'package:angular_forms/angular_forms.dart';  //formDirectives
import 'package:uniao/src/backend_modulos/services/purchase_service.dart';

//backend info
import '../../backend_modulos/Classes/team.dart';
import '../../backend_modulos/services/team_service.dart';
import '../../backend_modulos/Classes/product.dart';
import '../../backend_modulos/services/product_service.dart';
import '../../backend_modulos/Classes/purchase.dart';
import '../../backend_modulos/services/purchase_service.dart';

@Component(
  selector:'Shopping-section',
  templateUrl: 'Shopping_component.html',
  styleUrls: ['Shopping_component.css'],
  directives: [formDirectives,coreDirectives],
)

class Shop implements OnInit{
  Team team= new Team(0,null,null,null,null,"",0,null,null);
  Product product= new Product(0, "", 0, "", 0, "");
  Purchase purchase= new Purchase(0, "", null, 0, 0, "", 0, "");

  List<Product> products;

  bool submitted = false;
  bool success = false;
  String out;
  bool loggedin=false;
  bool confirmed_purchase = false;
  String token = window.localStorage['neec_id'];

  @override
  Future<void> ngOnInit() async {
    loggedin= window.localStorage.containsKey('neec_id');
    if(loggedin==true){
      products= await _productService.getAllProducts(token);
      team = await _teamService.getMyTeam(token);
      purchase.teamName=team.name;
    }
    else if(team==null){
      team=Team(0,null,null,null,null,"",0,null,null);
      purchase.teamName="";
    }
  }

  PurchaseService _purchaseService;
  ProductService _productService;
  TeamService _teamService;

  Shop(this._teamService,this._productService,this._purchaseService);

  Future<void> onSubmit() async {
    bool confirm = window.confirm("Pretendes comprar ${purchase.quantity} deste componente?");
    if(confirm==true){
      out= await _purchaseService.CreatePurchase(purchase, token);
      if(out=="Purchase Successful!"){
        success=true;
        team = await _teamService.getMyTeam(token);
        products= await _productService.getAllProducts(token);
      }
      else{
        success=false;
      }
    }
  }

  void PurchaseSetup(Product product){
    out="";
    purchase.product=product;
    purchase.sourceTeamCash=team.cash;
    submitted=true;
  }

 /* void Buy(String Item){
    if(Item=="Led")
      {
        transaction.amount=10*item_amount;
        transaction.description="${item_amount} Led purchase";

        bool confirm = window.confirm("Pretendes comprar ${item_amount} deste componente?");
        if(confirm==true)
          {
            confirmed_purchase=true;
          }
      }
    else if(Item=="ECG")
      {
        transaction.amount=10*item_amount;
        transaction.description="${item_amount} ECG purchase";

        bool confirm = window.confirm("Pretendes comprar ${item_amount} este componente?");
        if(confirm==true)
        {
          confirmed_purchase=true;
        }
      }
    else if(Item=="EMG")
    {
      transaction.amount=20*item_amount;
      transaction.description="${item_amount} EMG purchase";

      bool confirm = window.confirm("Pretendes comprar este ${item_amount} componente?");
      if(confirm==true)
      {
        confirmed_purchase=true;
      }
    }
    else{
      transaction.amount=0;
      item_amount=1;
      transaction.description="no purchase (needs debugging?)";
    }
  }
*/
}
