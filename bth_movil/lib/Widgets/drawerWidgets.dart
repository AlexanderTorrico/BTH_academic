import 'package:bth_movil/Page/asistenciaPage.dart';
import 'package:bth_movil/Page/pagosPage.dart';

import 'package:flutter/material.dart';


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
              onTap: () {
                print("Informaciones");
                
                
                // Update the state of the app
                // ...
                // Then close the drawer
                //Navigator.pop(context);
              },
            ),
            ListTile(
              leading: const Icon(Icons.note),
              title: Text('Notas'),
              onTap: () {
                print("Notas");
                
              },
            ),
            ListTile(
              leading: const Icon(Icons.person_add),
              title: Text('Asistencia'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => AsistenciaPage()),
                );
                
              },
            ),
            ListTile(
              leading: const Icon(Icons.attach_money),
              title: Text('Pagos'),
              onTap: () => Navigator.of(context).push(
                new MaterialPageRoute(builder: (context) => PagosPage()),
              )
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