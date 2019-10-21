import 'dart:html';
import 'package:angular/angular.dart';
import 'package:angular/core.dart'; //coreDirectives
import 'package:angular_forms/angular_forms.dart';
import 'package:angular_router/angular_router.dart';

//Frontend module
import '../../../line_chart.dart';

//backend modules
import '../../Classes/transaction.dart';
import '../../services/transaction_service.dart';
import '../../Classes/team.dart';
import '../../services/team_service.dart';
import '../../Classes/purchase.dart';
import '../../services/purchase_service.dart';
import '../../Classes/product.dart';

@Component(
  selector:'AdminTransactions',
  templateUrl: 'AdminTransaction_component.html',
  directives: [formDirectives,coreDirectives,routerDirectives,LineChart],
)

class AdminTransactionComponent implements OnInit{
  Transaction transaction = new Transaction(0,"","","", 0,null,0,"");
  Team team= new Team(0,null,null,null,null,"",0,null,null);
  Purchase purchase= new Purchase(0, "", null, 0, 0, "null", 0, "");

  String team_analyse;
  String out;

  bool show_all_transactions=false;
  bool show_all_buys=false;
  bool show_all_team_transactions=false;
  bool show_all_team_buys=false;

  List<Team> teams;
  List<Transaction> transactions;
  List<Purchase> purchases;

  TeamService _teamService;
  TransactionService _transactionService;
  PurchaseService _purchaseService;
  AdminTransactionComponent(this._transactionService,this._teamService,this._purchaseService);

  @override
  void ngOnInit() async{
    teams = await _teamService.getAllTeams();
  }

  GetAllTransactions() async{
    if(show_all_transactions==false)
      {
        transactions = await _transactionService.getAllTransactions(window.localStorage['neec_id']);
        SingleTable();
        show_all_transactions = BoolChanger(show_all_transactions);
      }
    else{
      show_all_transactions = BoolChanger(show_all_transactions);
      SingleTable();
    }
  }

  GetAllBuys() async{
    if(show_all_buys==false)
    {
      purchases = await _purchaseService.getAllPurchases(window.localStorage['neec_id']);
      SingleTable();
      show_all_buys = BoolChanger(show_all_buys);
    }
    else{
      show_all_buys = BoolChanger(show_all_buys);
      SingleTable();
    }
  }

  GetAllTeamTransactions() async{
    if(show_all_team_transactions==false)
    {
      transactions = await _transactionService.getTransactionsFromTeamName(team_analyse);
      SingleTable();
      show_all_team_transactions = BoolChanger(show_all_team_transactions);
    }
    else{
      show_all_team_transactions = BoolChanger(show_all_team_transactions);
      SingleTable();
    }
  }

  GetAllTeamBuys() async{
    if(show_all_team_buys==false)
    {
      purchases = await _purchaseService.getPurchasesFromTeamName(team_analyse);
      SingleTable();
      show_all_team_buys = BoolChanger(show_all_team_buys);
    }
    else{
      show_all_team_buys = BoolChanger(show_all_team_buys);
      SingleTable();
    }
  }

  bool BoolChanger(bool toChange){
    toChange=!toChange;
    return toChange;
  }
  void SingleTable(){
    if(show_all_team_buys==true || show_all_team_transactions==true || show_all_buys==true || show_all_transactions==true){
      show_all_transactions=false;
      show_all_buys=false;
      show_all_team_transactions=false;
      show_all_team_buys=false;
    }
  }

  void ConfirmRemove(Purchase purchase) async{
    bool confirm = window.confirm("Pretendes mesmo mudar a compra da equipa ${purchase.teamName} sobre o produto ${purchase.product.name}?");  //segundo .amount mudar para timestamp
    if(confirm==true)
    {
     // out= await _purchaseService.UpdateProduct(product,window.localStorage['neec_id'],increase);
    }
  }

}
