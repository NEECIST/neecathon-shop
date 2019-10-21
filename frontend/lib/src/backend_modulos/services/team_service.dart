import 'package:uniao/src/backend_modulos/Classes/transaction.dart';
import 'package:uniao/src/modulos/Transactions/Purchase/Transaction_request.dart';

import '../Classes/team.dart';
import 'package:http/http.dart';
import 'dart:convert';

class TeamService {

  List<Map<String, String>> teams;
  final Client _http;
  TeamService(this._http);

  static final _headers = {'Content-Type': 'application/json'};
  Future<String> create(Team team) async {
    try {
      final reponse = await _http.post("http://51.140.123.139:1995/" + "api/create/", headers: _headers, body: json.encode(team.toJson()));
      return(reponse.body);
    }catch (e){
      return "No internet connection";
      //throw _handleError;
    }
  }

  Future<List<Team>> getAllTeams() async {
    try {
      final response = await _http.get("http://51.140.123.139:1995/" + "teams/simple/", headers: _headers);
      final teams = (_extractData(response) as List)
        .map((value) => Team.fromJson(value))
        .toList();
      return teams;
    }catch (e){
      return null;
      // return "No internet connection";
      //throw _handleError;
    }
  }

  Future<Team> getTeam(String teamName) async {
    try {
      final response = await _http.get("http://51.140.123.139:1995/" + "teams/" + teamName, headers: _headers);
      // Change this fromJson to a more complex one for the team details
      return Team.fromJson(json.decode(response.body));
    }catch (e){
      return null;
      // return "No internet connection";
      //throw _handleError;
    }
  }

  Future<String> updateCash(String teamName, int cash) async {
    try {
      final response = await _http.post("http://51.140.123.139:1995/" + "teams/update/", headers: _headers, body: json.encode({'name': teamName, 'cash': cash}));
      return response.body;
    } catch (e) {
      return "error";
    }
  }

  Future<Team> getMyTeam(String token) async{
    try{
      _headers['Authorization'] = 'Bearer ' + token;

      final response = await _http.get("http://51.140.123.139:1995/" + "teams/me/", headers: _headers);

      return Team.fromJson(json.decode(response.body));
    }
    catch(e){
      print(e.toString());
      return null;
    }
  }

  dynamic _extractData(Response resp) => json.decode(resp.body);

  Exception _handleError(dynamic e){
    print(e);
    return Exception("There was an error: $e");
  }

}
