import 'user.dart';

class Transaction{
  int id;   //tirei o final
  String sourceTeam;
  String destTeam;
  String description;
  int amount;
  User user;
  int sourceTeamCash;
  String timestamp;
  Transaction(this.id,this.sourceTeam, this.destTeam, this.description, this.amount,this.user,this.sourceTeamCash,this.timestamp);
  String toString() => '$id:  => $destTeam  |  value = $amount|  description = $description| sourceTeam = $sourceTeam|user = $user| sourceTeamCash=$sourceTeamCash|timestamp=$timestamp';
  Map toJson() => {'id': id, 'sourceTeam':sourceTeam, 'destTeam': destTeam, 'description': description, 'value': amount,'user':user,'sourceTeamCash':sourceTeamCash,'timestamp':timestamp};

  factory Transaction.fromJson(Map<String,dynamic> transaction){
    Transaction model_transaction= new Transaction(0,"","","", 0,User.empty(),0,"");

    // model_transaction.id=transaction["id"];
    model_transaction.sourceTeam=transaction["sourceTeam"];
    model_transaction.destTeam=transaction["destTeam"];
    model_transaction.description=transaction['description'];
    model_transaction.amount=transaction["amount"];
    model_transaction.user.name=transaction["user"];
    model_transaction.sourceTeamCash=transaction["sourceTeamCash"];
    model_transaction.timestamp=transaction["timestamp"];
    return model_transaction;
  }
}
