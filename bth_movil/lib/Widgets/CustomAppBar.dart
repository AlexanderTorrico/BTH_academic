import 'package:flutter/material.dart';

class CustomAppBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.transparent,
      width: double.infinity,
      child: Row(
        children: <Widget>[
          IconButton(icon: Icon(Icons.arrow_back), onPressed: () {}),
          Spacer(),
          IconButton(icon: Icon(Icons.search), onPressed: () {}),
          Stack(
            children: <Widget>[
              IconButton(icon: Icon(Icons.shopping_cart), onPressed: () {}),
              Container(
                width: 20,
                height: 20,
                decoration: BoxDecoration(
                  color: Colors.red,
                  borderRadius: BorderRadius.circular(25),
                ),
                child: Center(
                  child: Text(
                    "3",
                    style: TextStyle(color: Colors.white),
                  ),
                ),
              ),
            ],
          ),
          IconButton(icon: Icon(Icons.more_vert), onPressed: () {}),
          Container(
            width: 45,
            height: 45,
            decoration: BoxDecoration(
              color: Colors.red,
              borderRadius: BorderRadius.circular(25),
            ),
          ),
          SizedBox(
            width: 15.0,
          )
        ],
      ),
    );
  }
}
