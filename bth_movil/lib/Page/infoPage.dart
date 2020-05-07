import 'dart:convert';

import 'package:bth_movil/Models/usermod.dart';
import 'package:bth_movil/Utils/constants.dart';
import 'package:bth_movil/Widgets/CustomAppBar.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:shared_preferences/shared_preferences.dart';

class InfoPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Home();
  }
}

class Home extends StatefulWidget {
  @override
  _InfoPageState createState() => _InfoPageState();
}

class _InfoPageState extends State<Home> {
  String _fullName = "Xds";
  String _carrera = "Oh no";

  String _gestion = "qwerty";
  String _dias = "L - W";
  String _horaInicio = "14:00";
  String _horaFin = "18:00";
  String _docente = "Oh no";

  @override
  Widget build(BuildContext context) {
    Size screenSize = MediaQuery.of(context).size;
    _read();
    return Stack(
      children: <Widget>[
        
        CustomAppBar(),
        
        _buildCoverImage(screenSize),
        Container(
          margin: const EdgeInsets.only(top: 255.0),
          child: ListView(children: <Widget>[
            _buildFullName(),
            _buildStatus(context),
            _buildComentario(context),
            _buildStatContainerHorario(),
            _buildStatContainerDocente(),
            SizedBox(height: 40.0),
          ]),
        ),
        SafeArea(
          child: SingleChildScrollView(
            child: Column(
              children: <Widget>[
                SizedBox(height: screenSize.height / 4.8),
                _buildProfileImage(),
              ],
            ),
          ),
        ),
      ],
    );
  }

  Widget _buildCoverImage(Size screenSize) {
    return Scaffold(
      body: Container(
        height: screenSize.height / 2.6,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.only(bottomRight: Radius.circular(180.0)),
          image: DecorationImage(
            image: AssetImage('assets/fnd.jpg'),
            fit: BoxFit.cover,
          ),
        ),
      ),
    );
  }

  Widget _buildProfileImage() {
    return Center(
      child: Container(
        width: 120.0,
        height: 120.0,
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage('assets/loginBth.png'),
            fit: BoxFit.cover,
          ),
          borderRadius: BorderRadius.circular(80.0),
          border: Border.all(
            color: Colors.white,
            width: 6.0,
          ),
        ),
      ),
    );
  }

  Widget _buildFullName() {
    TextStyle _nameTextStyle = TextStyle(
      fontFamily: 'Roboto',
      color: Colors.black,
      fontSize: 28.0,
      fontWeight: FontWeight.w700,
    );

    return Text(
      _fullName,
      textAlign: TextAlign.center,
      style: _nameTextStyle,
    );
  }

  Widget _buildStatus(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(vertical: 4.0, horizontal: 6.0),
      decoration: BoxDecoration(
        color: Theme.of(context).scaffoldBackgroundColor,
        borderRadius: BorderRadius.circular(4.0),
      ),
      child: Text(
        _carrera,
        textAlign: TextAlign.center,
        style: TextStyle(
          fontFamily: 'Spectral',
          color: Colors.black,
          fontSize: 20.0,
          fontWeight: FontWeight.w300,
        ),
      ),
    );
  }

  Widget _buildStatItem(String label, String count) {
    TextStyle _statLabelTextStyle = TextStyle(
      fontFamily: 'Roboto',
      color: Colors.black,
      fontSize: 16.0,
      fontWeight: FontWeight.w200,
    );

    TextStyle _statCountTextStyle = TextStyle(
      color: Colors.black54,
      fontSize: 24.0,
      fontWeight: FontWeight.bold,
    );

    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Text(
          count,
          style: _statCountTextStyle,
        ),
        Text(
          label,
          style: _statLabelTextStyle,
        ),
      ],
    );
  }

  Widget _buildStatContainerHorario() {
    return Container(
      height: 60.0,
      margin: EdgeInsets.only(top: 8.0),
      decoration: BoxDecoration(
        color: Color(0xFFEFF4F7),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: <Widget>[
          _buildStatItem("Dias", _dias),
          _buildStatItem("Inicio", _horaInicio),
          _buildStatItem("Fin", _horaFin),
        ],
      ),
    );
  }

  Widget _buildStatContainerDocente() {
    return Container(
      height: 60.0,
      margin: EdgeInsets.only(top: 8.0),
      decoration: BoxDecoration(
        color: Color(0xFFEFF4F7),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: <Widget>[
          _buildStatItem("Docente", _docente),
        ],
      ),
    );
  }

  _read() async {
    final prefs = await SharedPreferences.getInstance();

    final String txt = prefs.get('infoUser');

    Constantes.id = prefs.getString('id');
    Constantes.idEg = prefs.getString('idEG');

    final _json = json.decode(txt);
    final convert = Usermod.fromJson(_json);

    setState(() {
      _fullName =
          convert.nombre + " " + convert.aPaterno + " " + convert.aMaterno;
      _gestion = "Gestion: 20" + convert.idGestion;
      _carrera = convert.carrera;
      _docente = convert.docente;
    });
    /*Toast.show(value.toString(), context, duration: Toast.LENGTH_LONG, gravity:  Toast.BOTTOM);
    _count++;
    print("json Info user "+_count.toString());*/
  }

  //Comentarios
  Widget _buildComentario(BuildContext context) {
    TextStyle bioTextStyle = TextStyle(
      fontFamily: 'Spectral',
      fontWeight: FontWeight.w400, //try changing weight to w500 if not thin
      fontStyle: FontStyle.italic,
      color: Color(0xFF799497),
      fontSize: 16.0,
    );

    return Container(
      color: Theme.of(context).scaffoldBackgroundColor,
      padding: EdgeInsets.all(8.0),
      child: Text(
        _gestion,
        textAlign: TextAlign.center,
        style: bioTextStyle,
      ),
    );
  }
}
