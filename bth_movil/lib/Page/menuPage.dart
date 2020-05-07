

import 'dart:io';

import 'package:bth_movil/Page/asistenciaPage.dart';
import 'package:bth_movil/Page/infoPage.dart';
import 'package:bth_movil/Page/notaPage.dart';
import 'package:bth_movil/Page/pagosPage.dart';
import 'package:bth_movil/Widgets/CustomAppBar.dart';
import 'package:flutter/material.dart';

//import 'package:flutter_icons/flutter_icons.dart';
//import 'package:google_fonts/google_fonts.dart';

class MenuPage extends StatefulWidget {
  @override
  _MenuPageState createState() => _MenuPageState();
}

class _MenuPageState extends State<MenuPage> {
  
  int _iPage=0;
  @override
  Widget build(BuildContext context) {
    
   
    return Scaffold(
      
      drawer: Drawer(
        // Add a ListView to the drawer. This ensures the user can scroll
        // through the options in the drawer if there isn't enough vertical
        // space to fit everything.
        
        child: ListView(
          // Important: Remove any padding from the ListView.
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              child: Text(''),
              decoration: BoxDecoration(
                
                image: DecorationImage(
                  image: AssetImage("assets/loginBth.png"),
                     fit: BoxFit.cover)
              ),
            ),
            ListTile(
              leading: const Icon(Icons.info),
              title: Text('Informacion'),
              onTap: (){
                _changeStatePage(0);
              } 
            ),
            ListTile(
              leading: const Icon(Icons.note),
              title: Text('Notas'),
              
              onTap: () {
                _changeStatePage(3);
                
              },
            ),
            ListTile(
              leading: const Icon(Icons.person_add),
              title: Text('Asistencia'),
              onTap: (){
                _changeStatePage(1);
              } 
            ),
            ListTile(
              leading: const Icon(Icons.attach_money),
              title: Text('Pagos'),
              onTap: (){
                _changeStatePage(2);
              } 
            ),
            ListTile(
              leading: const Icon(Icons.history),
              title: Text('Historial'),
              onTap: () {
                // Update the state of the app
                // ...
                // Then close the drawer
                //Navigator.pop(context);
              },
            ),
            ListTile(
              leading: const Icon(Icons.flight_land),
              title: const Text('Carreras BTH'),
              
              onTap: () { /* react to the tile being tapped */ }
            )
          ],
        ),
    ),
      body: _selectPage(_iPage),
    );
  }
  Widget getDrawerWidget(context){
    return Drawer(
        // Add a ListView to the drawer. This ensures the user can scroll
        // through the options in the drawer if there isn't enough vertical
        // space to fit everything.

        child: ListView(
          // Important: Remove any padding from the ListView.
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              child: Text(''),
              decoration: BoxDecoration(
                
                image: DecorationImage(
                  image: AssetImage("assets/loginBth.png"),
                     fit: BoxFit.cover)
              ),
            ),
            ListTile(
              leading: const Icon(Icons.info),
              title: Text('Informacion'),
              onTap: (){
                _changeStatePage(0);
                exit(1);
              } 
            ),
            ListTile(
              leading: const Icon(Icons.note),
              title: Text('Notas'),
              
              onTap: () {
                _changeStatePage(3);
                
              },
            ),
            ListTile(
              leading: const Icon(Icons.person_add),
              title: Text('Asistencia'),
              onTap: (){
                _changeStatePage(1);
              } 
            ),
            ListTile(
              leading: const Icon(Icons.attach_money),
              title: Text('Pagos'),
              onTap: (){
                _changeStatePage(2);
              } 
            ),
            ListTile(
              leading: const Icon(Icons.history),
              title: Text('Historial'),
              onTap: () {
                // Update the state of the app
                // ...
                // Then close the drawer
                //Navigator.pop(context);
              },
            ),
            ListTile(
              leading: const Icon(Icons.flight_land),
              title: const Text('Carreras BTH'),
              
              onTap: () { /* react to the tile being tapped */ }
            )
          ],
        ),
    );
}

  _selectPage(int i){
    switch(i){
      case 0 : return InfoPage();
      case 1 : return AsistenciaPage();
      case 2 : return PagosPage();
      case 3 : return NotaPage();
    }
  }

  _changeStatePage(int i){
    setState(() {
      _iPage = i;
    });
  }



}