// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'usermod.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

Serializer<Usermod> _$usermodSerializer = new _$UsermodSerializer();

class _$UsermodSerializer implements StructuredSerializer<Usermod> {
  @override
  final Iterable<Type> types = const [Usermod, _$Usermod];
  @override
  final String wireName = 'Usermod';

  @override
  Iterable<Object> serialize(Serializers serializers, Usermod object,
      {FullType specifiedType = FullType.unspecified}) {
    final result = <Object>[];
    if (object.id != null) {
      result
        ..add('id')
        ..add(serializers.serialize(object.id,
            specifiedType: const FullType(String)));
    }
    if (object.nombre != null) {
      result
        ..add('nombre')
        ..add(serializers.serialize(object.nombre,
            specifiedType: const FullType(String)));
    }
    if (object.aPaterno != null) {
      result
        ..add('aPaterno')
        ..add(serializers.serialize(object.aPaterno,
            specifiedType: const FullType(String)));
    }
    if (object.aMaterno != null) {
      result
        ..add('aMaterno')
        ..add(serializers.serialize(object.aMaterno,
            specifiedType: const FullType(String)));
    }
    if (object.carrera != null) {
      result
        ..add('carrera')
        ..add(serializers.serialize(object.carrera,
            specifiedType: const FullType(String)));
    }
    if (object.deColegio != null) {
      result
        ..add('deColegio')
        ..add(serializers.serialize(object.deColegio,
            specifiedType: const FullType(String)));
    }
    if (object.aColegio != null) {
      result
        ..add('aColegio')
        ..add(serializers.serialize(object.aColegio,
            specifiedType: const FullType(String)));
    }
    if (object.docente != null) {
      result
        ..add('docente')
        ..add(serializers.serialize(object.docente,
            specifiedType: const FullType(String)));
    }
    if (object.idGestion != null) {
      result
        ..add('idGestion')
        ..add(serializers.serialize(object.idGestion,
            specifiedType: const FullType(String)));
    }
    if (object.idEG != null) {
      result
        ..add('idEG')
        ..add(serializers.serialize(object.idEG,
            specifiedType: const FullType(String)));
    }
    return result;
  }

  @override
  Usermod deserialize(Serializers serializers, Iterable<Object> serialized,
      {FullType specifiedType = FullType.unspecified}) {
    final result = new UsermodBuilder();

    final iterator = serialized.iterator;
    while (iterator.moveNext()) {
      final key = iterator.current as String;
      iterator.moveNext();
      final dynamic value = iterator.current;
      switch (key) {
        case 'id':
          result.id = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'nombre':
          result.nombre = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'aPaterno':
          result.aPaterno = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'aMaterno':
          result.aMaterno = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'carrera':
          result.carrera = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'deColegio':
          result.deColegio = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'aColegio':
          result.aColegio = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'docente':
          result.docente = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'idGestion':
          result.idGestion = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
        case 'idEG':
          result.idEG = serializers.deserialize(value,
              specifiedType: const FullType(String)) as String;
          break;
      }
    }

    return result.build();
  }
}

class _$Usermod extends Usermod {
  @override
  final String id;
  @override
  final String nombre;
  @override
  final String aPaterno;
  @override
  final String aMaterno;
  @override
  final String carrera;
  @override
  final String deColegio;
  @override
  final String aColegio;
  @override
  final String docente;
  @override
  final String idGestion;
  @override
  final String idEG;

  factory _$Usermod([void Function(UsermodBuilder) updates]) =>
      (new UsermodBuilder()..update(updates)).build();

  _$Usermod._(
      {this.id,
      this.nombre,
      this.aPaterno,
      this.aMaterno,
      this.carrera,
      this.deColegio,
      this.aColegio,
      this.docente,
      this.idGestion,
      this.idEG})
      : super._();

  @override
  Usermod rebuild(void Function(UsermodBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  UsermodBuilder toBuilder() => new UsermodBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is Usermod &&
        id == other.id &&
        nombre == other.nombre &&
        aPaterno == other.aPaterno &&
        aMaterno == other.aMaterno &&
        carrera == other.carrera &&
        deColegio == other.deColegio &&
        aColegio == other.aColegio &&
        docente == other.docente &&
        idGestion == other.idGestion &&
        idEG == other.idEG;
  }

  @override
  int get hashCode {
    return $jf($jc(
        $jc(
            $jc(
                $jc(
                    $jc(
                        $jc(
                            $jc(
                                $jc($jc($jc(0, id.hashCode), nombre.hashCode),
                                    aPaterno.hashCode),
                                aMaterno.hashCode),
                            carrera.hashCode),
                        deColegio.hashCode),
                    aColegio.hashCode),
                docente.hashCode),
            idGestion.hashCode),
        idEG.hashCode));
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper('Usermod')
          ..add('id', id)
          ..add('nombre', nombre)
          ..add('aPaterno', aPaterno)
          ..add('aMaterno', aMaterno)
          ..add('carrera', carrera)
          ..add('deColegio', deColegio)
          ..add('aColegio', aColegio)
          ..add('docente', docente)
          ..add('idGestion', idGestion)
          ..add('idEG', idEG))
        .toString();
  }
}

class UsermodBuilder implements Builder<Usermod, UsermodBuilder> {
  _$Usermod _$v;

  String _id;
  String get id => _$this._id;
  set id(String id) => _$this._id = id;

  String _nombre;
  String get nombre => _$this._nombre;
  set nombre(String nombre) => _$this._nombre = nombre;

  String _aPaterno;
  String get aPaterno => _$this._aPaterno;
  set aPaterno(String aPaterno) => _$this._aPaterno = aPaterno;

  String _aMaterno;
  String get aMaterno => _$this._aMaterno;
  set aMaterno(String aMaterno) => _$this._aMaterno = aMaterno;

  String _carrera;
  String get carrera => _$this._carrera;
  set carrera(String carrera) => _$this._carrera = carrera;

  String _deColegio;
  String get deColegio => _$this._deColegio;
  set deColegio(String deColegio) => _$this._deColegio = deColegio;

  String _aColegio;
  String get aColegio => _$this._aColegio;
  set aColegio(String aColegio) => _$this._aColegio = aColegio;

  String _docente;
  String get docente => _$this._docente;
  set docente(String docente) => _$this._docente = docente;

  String _idGestion;
  String get idGestion => _$this._idGestion;
  set idGestion(String idGestion) => _$this._idGestion = idGestion;

  String _idEG;
  String get idEG => _$this._idEG;
  set idEG(String idEG) => _$this._idEG = idEG;

  UsermodBuilder();

  UsermodBuilder get _$this {
    if (_$v != null) {
      _id = _$v.id;
      _nombre = _$v.nombre;
      _aPaterno = _$v.aPaterno;
      _aMaterno = _$v.aMaterno;
      _carrera = _$v.carrera;
      _deColegio = _$v.deColegio;
      _aColegio = _$v.aColegio;
      _docente = _$v.docente;
      _idGestion = _$v.idGestion;
      _idEG = _$v.idEG;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(Usermod other) {
    if (other == null) {
      throw new ArgumentError.notNull('other');
    }
    _$v = other as _$Usermod;
  }

  @override
  void update(void Function(UsermodBuilder) updates) {
    if (updates != null) updates(this);
  }

  @override
  _$Usermod build() {
    final _$result = _$v ??
        new _$Usermod._(
            id: id,
            nombre: nombre,
            aPaterno: aPaterno,
            aMaterno: aMaterno,
            carrera: carrera,
            deColegio: deColegio,
            aColegio: aColegio,
            docente: docente,
            idGestion: idGestion,
            idEG: idEG);
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: always_put_control_body_on_new_line,always_specify_types,annotate_overrides,avoid_annotating_with_dynamic,avoid_as,avoid_catches_without_on_clauses,avoid_returning_this,lines_longer_than_80_chars,omit_local_variable_types,prefer_expression_function_bodies,sort_constructors_first,test_types_in_equals,unnecessary_const,unnecessary_new
