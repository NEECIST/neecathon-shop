import 'dart:html';
import 'package:angular/angular.dart';
import 'package:angular/core.dart'; //coreDirectives
import 'package:angular_forms/angular_forms.dart';  //formDirectives

import 'package:angular_router/angular_router.dart';
import '../../backend_modulos/Classes/team.dart';
import '../../backend_modulos/services/team_service.dart';
import 'package:uniao/src/backend_modulos/create_team/create_team_component.dart';
import 'package:uniao/src/backend_modulos/services/login_service.dart';
import '../../routes.dart';
import '../../route_paths.dart';

@Component(
  selector:'NavBar',
  templateUrl: 'NavBar_component.html',
  providers: [ClassProvider(LoginService),ClassProvider(CreateTeamComponent)],
  directives: [formDirectives,coreDirectives,routerDirectives],
  exports: [RoutePaths,Routes],
)

class NavBarform implements OnInit, AfterViewInit{
  bool loggedin=false;
  Storage Log = window.localStorage;
  Team team= new Team(0,null,null,null,null,"",0,null,null);

  TeamService _teamService;

  NavBarform(this._teamService);

  @override
  Future<void> ngOnInit() async {
    loggedin= Log.containsKey('neec_id');
    if(loggedin==true){
      team = await _teamService.getMyTeam(window.localStorage['neec_id']);
      if(team.name=="neec")
        {
          team.isAdmin=true;
        }
    }
  }

  ClearLog(){
    Log.remove('neec_id');
    window.location.reload();
    loggedin=false;
  }

  @override
  void ngAfterViewInit() {
    loggedin= Log.containsKey('neec_id');
  }

}
