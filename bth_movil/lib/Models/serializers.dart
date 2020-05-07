import 'package:bth_movil/Models/usermod.dart';
import 'package:built_value/serializer.dart';
import 'package:built_value/standard_json_plugin.dart';

part 'serializers.g.dart';

@SerializersFor([
  Usermod,
])
final Serializers serializers = 
    (_$serializers.toBuilder()..addPlugin(StandardJsonPlugin())).build();