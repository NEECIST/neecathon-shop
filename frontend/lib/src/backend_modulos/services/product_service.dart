import '../Classes/product.dart';
import 'package:http/http.dart';
import 'dart:convert';

class ProductService{

  List<Map<String,dynamic>> products;

  final Client _http;
  ProductService(this._http);

  static final _headers = {'Content-Type': 'application/json'};

  Future<List<Product>> getAllProducts(String token) async {
    try {
      _headers['Authorization'] = 'Bearer ' + token;

      final response = await _http.get("http://51.140.123.139:1995/" + "product/", headers: _headers,);

      final products=(_extractData(response) as List)
          .map((value) => Product.fromJson(value))
          .toList();

      return products;
    }
    catch(e){
      print(e.toString());
      return null;
    }
  }

  Future<String> CreateProduct(Product product,String token) async {
    try {
      _headers['Authorization'] = 'Bearer ' + token;

      final response = await _http.post("http://51.140.123.139:1995/" + "product/create/", headers: _headers, body: json.encode(product.toJson()));
      return(response.body);
    }
    catch(e){
      print(e.toString());
      return null;
    }
  }

  Future<String> UpdateProduct(Product product,String token,bool increase) async {
    try {
      _headers['Authorization'] = 'Bearer ' + token;

      final response = await _http.post("http://51.140.123.139:1995/" + "product/update/", headers: _headers, body: json.encode({'name': product.name, 'increase': increase}));
      return(response.body);
    }
    catch(e){
      print(e.toString());
      return null;
    }
  }


  dynamic _extractData(Response resp) => json.decode(resp.body);

}
