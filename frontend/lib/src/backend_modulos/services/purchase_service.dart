import 'package:uniao/src/backend_modulos/Classes/product.dart';

import '../Classes/purchase.dart';
import 'package:http/http.dart';
import 'dart:convert';

class PurchaseService{

  List<Map<String,dynamic>> purchases;

  final Client _http;
  PurchaseService(this._http);

  static final _headers = {'Content-Type': 'application/json'};

  Future<List<Purchase>> getAllPurchases(String token) async {
    try {
      _headers['Authorization'] = 'Bearer ' + token;

      final response = await _http.get("http://51.140.123.139:1995/" + "purchases/", headers: _headers,);

      final purchases=(_extractData(response) as List)
          .map((value) => Purchase.fromJson(value))
          .toList();

      return purchases;
    }
    catch(e){
      print(e.toString());
      return null;
    }
  }

  Future<List<Purchase>> getPurchasesFromTeamName(String teamName) async{
    try{
      final response = await _http.get("http://51.140.123.139:1995/" + "purchases/" + teamName, headers: _headers);
      final purchases =
        (_extractData(response) as List)
          .map((value) => Purchase.fromJson(value))   //Transaction.fromJson(json.decode(response.body)))??? ja vem com o extract data?
          .toList();
      return purchases;
    }
    catch(e){
      print(e.toString());
      return null;
    }
  }

  Future<List<Purchase>> getMyPurchases(String token) async{
    try{
      _headers['Authorization'] = 'Bearer ' + token;

      final response = await _http.get("http://51.140.123.139:1995/" + "purchases/me/", headers: _headers);
      
      final purchases=(_extractData(response) as List)
          .map((value) => Purchase.fromJson(value))
          .toList();

      return purchases;
    }
    catch(e){
      print(e.toString());
      return null;
    }
  }

  Future<String> CreatePurchase(Purchase purchase, String token) async{
    try{
      _headers['Authorization'] = 'Bearer ' + token;
      final response = await _http.post("http://51.140.123.139:1995/" + "purchases/create/", body: json.encode(purchase.toJson()), headers: _headers);
      return (response.body);
    }
    catch(e){
      print(e.toString());
      return null;
    }
  }

  dynamic _extractData(Response resp) => json.decode(resp.body);

}
