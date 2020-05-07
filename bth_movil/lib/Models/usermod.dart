import 'package:bth_movil/Models/serializers.dart';

import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'usermod.g.dart';

abstract class Usermod implements Built<Usermod, UsermodBuilder> {
  @nullable
  String get id;
  @nullable
  String get nombre;
  @nullable
  String get aPaterno;
  @nullable
  String get aMaterno;
  @nullable
  String get carrera;
  @nullable
  String get deColegio;
  @nullable
  String get aColegio;
  @nullable
  String get docente;
  @nullable
  String get idGestion;
  @nullable
  String get idEG;

  Usermod._();
  factory Usermod([void Function(UsermodBuilder) updates]) = _$Usermod;

  Map<String, dynamic> toJson() {
    return serializers.serializeWith(Usermod.serializer, this);
  }

  static Usermod fromJson(Map<String, dynamic> json) {
    return serializers.deserializeWith(Usermod.serializer, json);
  }

  static Serializer<Usermod> get serializer => _$usermodSerializer;
}