import 'dart:html';
import 'package:angular/angular.dart';
import 'package:angular/core.dart'; //coreDirectives
import 'package:angular_forms/angular_forms.dart';  //formDirectives
import 'package:angular_modern_charts/angular_modern_charts.dart';
import 'package:angular_router/angular_router.dart';
import 'package:angular_modern_charts/angular_modern_charts.dart';

//backend info
import 'backend_modulos/Classes/transaction.dart';
import 'backend_modulos/services/transaction_service.dart';



@Component(
  selector:'line-chart',
  templateUrl: 'line_chart.html',
  directives: [BarChartComponent, formDirectives,coreDirectives,routerDirectives],
)

class LineChart{
  // Create some data (required)
  final barData = BarChartData([
    'Expected',
    'Result'
  ], [
    BarChartColumnData('2016', [350, 415]),
    BarChartColumnData('2017', [450, 489]),
    BarChartColumnData('2018', [500, 581])
  ]);

  // Set some properties (optional)
  final barProperties = BarChartProperties()
    ..height = '600px'
    ..xAxis.title.text = 'Year'
    ..yAxis.title.text = 'Million dollars'
    ..yAxis.minValue = 0;
}
