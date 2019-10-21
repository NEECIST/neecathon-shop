import 'dart:html';

import 'package:uniao/src/backend_modulos/Classes/user.dart';
import 'product.dart';

class Purchase{
  int id;
  String teamName;
  Product product;
  int quantity;
  int amount;
  String user;
  int sourceTeamCash;
  String timestamp;
  Purchase(this.id,this.teamName, this.product, this.quantity, this.amount,this.user,this.sourceTeamCash,this.timestamp);
  String toString() => '$id:  => $teamName  |  Product = $product|  quantity = $quantity| amount= $amount|user= $user|sourceTeamCash=$sourceTeamCash|timestamp=$timestamp';
  Map toJson() => {'id': id, 'teamName':teamName, 'product': product.name, 'quantity': quantity, 'totalAmount': quantity*product.price, 'user':user,'sourceTeamCash':sourceTeamCash,'timestamp':timestamp};

  factory Purchase.fromJson(Map<String,dynamic> purchase){
    Purchase model_purchase = Purchase(0, "", null, 0, 0, "", 0, "");

    model_purchase.id=purchase["id"];
    model_purchase.teamName=purchase["teamName"];
    model_purchase.product= Product(purchase["product"]["id"], purchase["product"]["name"], purchase["product"]["price"], purchase["product"]["description"], purchase["product"]["quantity"], purchase["product"]["image_path"]);
    model_purchase.quantity=purchase["quantity"];
    model_purchase.amount=purchase["totalAmount"];
    model_purchase.user=purchase["user"];
    model_purchase.sourceTeamCash=purchase["sourceTeamCash"];
    model_purchase.timestamp=purchase["timestamp"];

    return model_purchase;
  }
}
