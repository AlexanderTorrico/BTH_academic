import 'dart:convert';

import 'package:bth_movil/Models/usermod.dart';
import 'package:bth_movil/Utils/constants.dart';
import 'package:bth_movil/Page/menuPage.dart';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:toast/toast.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Home(),
      ),
      debugShowCheckedModeBanner: false,
    );
  }
}

class Home extends StatefulWidget {
  @override
  SignInOne createState() {
    return SignInOne();
  }
}

class SignInOne extends State<Home> {
  final TextEditingController edtId = new TextEditingController();
  final TextEditingController edtPass = new TextEditingController();

  final logger = Logger(
    printer: PrettyPrinter(
      methodCount: 0,
      colors: false,
    ),
  );
  final _formKey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
    setState(() {
      _save();
      edtId.text = "116";
      edtPass.text = "0";
    });
    return Stack(
      children: <Widget>[
        Container(
          decoration: BoxDecoration(
              image: DecorationImage(
                  image: AssetImage('assets/loginBth.png'),
                  fit: BoxFit.fitWidth,
                  alignment: Alignment.topCenter)),
        ),
        Container(
          width: MediaQuery.of(context).size.width,
          margin: EdgeInsets.only(top: 210),
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(20),
            color: Colors.white,
          ),
          child: Padding(
            padding: EdgeInsets.all(23),
            child: Form(
              key: _formKey,
              child: ListView(
                children: <Widget>[
                  Padding(
                    padding: EdgeInsets.fromLTRB(0, 30, 0, 20),
                    child: Container(
                      child: TextFormField(
                        validator: (value) {
                          if (value.isEmpty) {
                            return 'Please enter some text';
                          }
                          return null;
                        },
                        controller: edtId,
                        cursorColor: Color(0XFFFFCC00),
                        style: TextStyle(
                            color: Colors.black, fontFamily: 'SFUIDisplay'),
                        decoration: InputDecoration(
                            border: OutlineInputBorder(),
                            labelText: 'Email',
                            prefixIcon: Icon(Icons.person_outline),
                            labelStyle: TextStyle(fontSize: 15)),
                      ),
                    ),
                  ),
                  Container(
                    color: Color(0xfff5f5f5),
                    child: TextFormField(
                      validator: (value) {
                        if (value.isEmpty) {
                          return 'Please enter some text';
                        }
                        return null;
                      },
                      controller: edtPass,
                      obscureText: true,
                      style: TextStyle(
                          color: Colors.black, fontFamily: 'SFUIDisplay'),
                      decoration: InputDecoration(
                          border: OutlineInputBorder(),
                          labelText: 'Contraseña',
                          prefixIcon: Icon(Icons.lock_outline),
                          labelStyle: TextStyle(fontSize: 15)),
                    ),
                  ),
                  Padding(
                    padding: EdgeInsets.only(top: 20),
                    child: RaisedButton(
                      onPressed: () {
                        getUser(context);
                     
                      if (_formKey.currentState.validate()) {
                        Scaffold.of(context)
                            .showSnackBar(SnackBar(content: Text('Datos Incorreptos')));
                      }
                      
                        /*Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => MenuPage()),
                        );*/
                      }, //since this is only a UI app
                      child: Text(
                        'Ingresar',
                        style: TextStyle(
                          fontSize: 15,
                          fontFamily: 'SFUIDisplay',
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      color: Constantes.colorBtn,
                      elevation: 0,
                      //minWidth: 400,
                      //height: 50,
                      textColor: Colors.white,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10)),
                    ),
                  ),
                  Padding(
                    padding: EdgeInsets.only(top: 50),
                    child: Center(
                      child: Text(
                        'Bachillerato Tecnico Humanistico Warnes - Satelite Norte',
                        style: TextStyle(
                            fontFamily: 'SFUIDisplay',
                            fontSize: 10,
                            color: Color(0xff808285),
                            fontWeight: FontWeight.bold),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        )
      ],
    );
  }

  Future<String> getUser(context) async {
    if (edtId.text == "") {
      String msg = "Deves insertar el numero de tu carnet de indentidad";
      Toast.show(msg, context,
          duration: Toast.LENGTH_LONG, gravity: Toast.BOTTOM);
    } else if (edtPass.text == "") {
      String msg = "Deves insertar una contraseña";
      Toast.show(msg, context,
          duration: Toast.LENGTH_LONG, gravity: Toast.BOTTOM);
    } else {
      try {
        final url = "http://192.168.43.150:36129/bth/api/movil/info";
        final response = await http.post(url, 
            headers: {"Content-Type": "application/json"},
            body: jsonEncode({'ci': edtId.text, 'password': edtPass.text}));

        final _json = json.decode(response.body);
        final convert = Usermod.fromJson(_json);
        
        
        print(response.body);
        final prefs = await SharedPreferences.getInstance();

        
        prefs.setString('id', convert.id);
        prefs.setString('idEG', convert.idEG);
        prefs.setString('infoUser', response.body);
         
        print("okk");
        if (response.body == 'false') {
          print("Bueno esta entrando a false");
        } else {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => MenuPage()),
          );
        }
        //Toast.show(response.body, context, duration: Toast.LENGTH_LONG, gravity:  Toast.BOTTOM);

      } catch (e) {
        print(e.toString()+"  <=");
      }
    }

    return null;
  }

  _save() async {
    final prefs = await SharedPreferences.getInstance();
    final key = 'cod';

    prefs.setInt(key, 1212313);
  }
}
