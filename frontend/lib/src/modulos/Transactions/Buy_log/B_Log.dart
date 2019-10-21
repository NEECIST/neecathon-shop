import 'dart:html';
import 'package:angular/angular.dart';
import 'package:angular/core.dart'; //coreDirectives
import 'package:angular_forms/angular_forms.dart';  //formDirectives
import 'package:angular_router/angular_router.dart';

//backend info
import '../../../backend_modulos/Classes/transaction.dart';
import '../../../backend_modulos/services/transaction_service.dart';
import '../../../backend_modulos/Classes/purchase.dart';
import '../../../backend_modulos/services/purchase_service.dart';




@Component(
  selector:'Purchase_log',
  templateUrl: 'Purchases_component.html',
  directives: [formDirectives,coreDirectives,routerDirectives],
)

class PurchaseLog implements OnInit{
  Transaction transaction = new Transaction(0,"keks","ok","none", 0,null,0,"");

  Transaction saver;

  Storage Log = window.localStorage;

  TransactionService _transactionService;
  PurchaseService _purchaseService;

  PurchaseLog(this._transactionService,this._purchaseService);

  List<Transaction> transactions;
  List<Purchase> purchases;

  @override
  Future<void> ngOnInit() async {
    transactions = await _transactionService.getMyTransactions(Log['neec_id']);
    purchases = await _purchaseService.getMyPurchases(Log['neec_id']);
  }

  refresh_list_transactions() async{
    transactions = await _transactionService.getMyTransactions(Log['neec_id']);
  }

  refresh_list_purchases() async{
    purchases = await _purchaseService.getMyPurchases(Log['neec_id']);
  }

}
