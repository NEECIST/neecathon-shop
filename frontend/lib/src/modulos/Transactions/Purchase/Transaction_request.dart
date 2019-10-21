import 'dart:html';
import 'package:angular/angular.dart';
import 'package:angular/core.dart'; //coreDirectives
import 'package:angular_forms/angular_forms.dart';  //formDirectives
import 'package:angular_router/angular_router.dart';

//backend info
import '../../../backend_modulos/Classes/team.dart';
import '../../../backend_modulos/Classes/transaction.dart';
import '../../../backend_modulos/services/team_service.dart';
import '../../../backend_modulos/services/transaction_service.dart';

@Component(
  selector:'Transactions-form',
  templateUrl: 'Transactions_request_component.html',
  directives: [formDirectives,coreDirectives,routerDirectives],
)

class TransactionsForm implements OnInit{
  Transaction transaction = new Transaction(0,"","","", 0,null,0,"");
  bool submitted = false;
  bool success = false;
  String out;
  Team team = new Team(1, null, null, null, null, null,200, null,null);

  List<Team> teams;
  TeamService _teamService;
  TransactionService _transactionService;

  TransactionsForm(this._teamService,this._transactionService);


  @override
  Future<void> ngOnInit() async {
    teams = await _teamService.getAllTeams();
    team = await _teamService.getMyTeam(window.localStorage['neec_id']);
    transaction.sourceTeam=team.name;
  }

  Future<void> onSubmit() async {
    submitted = true;
    out = await _transactionService.create(transaction, window.localStorage['neec_id']);
    if(out == 'Transaction successful'){
      success = true;
    }else{
      success = false;
    }
  }

}
